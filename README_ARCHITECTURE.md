# 🏗️ System & Network Architecture

This document provides a deep dive into the structural architecture, communication patterns, and Service Mesh implementation of the project.

## 🧩 Cluster Structure (Kubernetes)
The application is deployed on a **Single-Node Minikube Cluster** but allows for logical separation using Namespaces.

### Namespace: `quiz-platform`
All application components reside here to isolate them from system components.
*   **Pods**: Ephemeral execution units containing our application containers and Istio sidecars.
*   **Services**: Stable networking abstraction (ClusterIP) to expose pods internally.
*   **Deployments**: Manages pod replicas and rollout strategies.

---

## ⚡ Inter-Service Communication (gRPC)
Unlike traditional REST/HTTP(S) microservices, this system uses **gRPC (Google Remote Procedure Call)** for internal communication between the Student and Quiz services.

### Why gRPC?
1.  **Performance**: Uses **HTTP/2** for multiplexing and **Protobuf** (binary format) for smaller payload size compared to JSON.
2.  **Strong Typing**: Service contracts are defined strictly in `.proto` files (`common-proto`).
3.  **Code Generation**: Client stubs and Server bases are auto-generated, reducing boilerplate and runtime errors.

### Implementation Details
*   **IDL (Interface Definition Language)**: Defined in `quiz.proto`.
*   **Stub**: The `StudentService` uses a `BlockingStub` to make synchronous calls to `QuizService`.
*   **Flow**:
    `REST Request (Client)` -> `Student Service` --[gRPC/Protobuf]--> `Quiz Service` -> `DB`

---

## 🕸️ Istio Service Mesh
We use **Istio** to decouple network logic (security, routing, observability) from application code.

### 1. Architecture Overview
Istio injects an **Envoy Proxy (Sidecar)** into every application Pod. The application talks to `localhost`, and the Proxy handles the actual network traffic.

```mermaid
graph TD
    subgraph "Control Plane (Istiod)"
        Pilot[Pilot (Discovery)]
        Citadel[Citadel (Security/Certs)]
    end

    subgraph "Data Plane (Envoy Proxies)"
        User --> GW[Ingress Gateway]
        GW --> Proxy1[Envoy]
        subgraph "Student Pod"
            Proxy1 -- localhost --> App1[Student App]
        end
        
        Proxy1 -- mTLS --> Proxy2[Envoy]
        subgraph "Quiz Pod"
            Proxy2 -- localhost --> App2[Quiz App]
        end
    end
    
    Pilot -.-> |Config Push| Proxy1
    Pilot -.-> |Config Push| Proxy2
    Citadel -.-> |Certs| Proxy1
    Citadel -.-> |Certs| Proxy2
```

### 2. Key Components
*   **Istiod (Control Plane)**:
    *   **Pilot**: Converts Kubernetes service definitions into Envoy configuration and propagates specific routing rules.
    *   **Citadel**: Acts as the Certificate Authority (CA). It generates and rotates certificates for mTLS.
    *   **Galley**: Validates Kubernetes configuration files.

*   **Envoy (Data Plane)**:
    *   Performance C++ proxy intercepting all inbound/outbound traffic.
    *   Handles load balancing, retries, and circuit breaking.

### 3. Features Implemented
*   **mTLS (Mutual TLS)**: Traffic between `Student` and `Quiz` services is strictly encrypted. Istio manages the certificate exchange automatically.
*   **Ingress Gateway**: A specialized LoadBalancer pod that acts as the single entry point for external traffic to enter the mesh.
*   **Service Discovery**: Istio uses K8s services to populate its internal service registry.

---

## 💾 Data Persistence
*   **Database**: H2 In-Memory Database.
*   **Isolation**: Each microservice has its own isolated database instance (`quizdb` and `studentdb`), adhering to the "Database per Service" pattern.
*   **Access**: Services communicate with DBs via JDBC.
