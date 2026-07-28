# TrackFlow

> Plataforma de rastreamento veicular baseada em microsserviços, desenvolvida com Java, Quarkus e Apache Kafka.

## 📖 Sobre

O **TrackFlow** é um projeto de código aberto que tem como objetivo fornecer uma plataforma moderna para rastreamento veicular em tempo real.

A arquitetura foi projetada utilizando microsserviços orientados a eventos, permitindo escalabilidade horizontal, alta disponibilidade e desacoplamento entre os componentes.

Inicialmente o sistema será responsável por:

- Receber conexões TCP de rastreadores GPS;
- Decodificar diferentes protocolos;
- Persistir posições geográficas;
- Disponibilizar APIs para consulta de veículos e histórico;
- Gerenciar dispositivos e veículos.

Posteriormente serão adicionados recursos como:

- Cálculo de viagens;
- Cercas eletrônicas (Geofences);
- Alertas;
- Infrações de trânsito;
- Análise de comportamento do motorista;
- Integração com dispositivos CAN Bus;
- Relatórios avançados;
- Notificações em tempo real.

---

# Arquitetura

```text
                   Internet
                       │
                 API Gateway
                       │
      ┌────────────────┴──────────────┐
      │                               │
 Gateway TCP                   REST APIs
      │
      ▼
 Apache Kafka
      │
 ┌────┼───────────┬───────────────┐
 │    │           │               │
 ▼    ▼           ▼               ▼
Decoder Persist  Trips         Alerts
 │
 ▼
PostgreSQL + PostGIS
 │
Redis
```

---

# Tecnologias

- Java 21
- Quarkus
- Apache Kafka
- PostgreSQL
- PostGIS
- Redis
- Flyway
- Docker
- Docker Compose
- Kubernetes
- Prometheus
- Grafana
- OpenTelemetry
- JUnit 5
- Testcontainers

---

# Arquitetura

O projeto segue princípios de:

- Domain Driven Design (DDD)
- Event Driven Architecture (EDA)
- Clean Architecture
- SOLID
- Twelve-Factor App
- CQRS (em alguns serviços)
- Microsserviços independentes

---

# Estrutura do repositório

```text
trackflow/

common/
│
├── dto
├── events
├── protobuf
├── utils

gateway-service/

device-session-service/

decoder-service/

persist-service/

api-service/

auth-service/

trip-service/

alert-service/

notification-service/

docker/

kubernetes/

docs/
```

---

# Microsserviços

## Gateway Service

Responsável por receber conexões TCP dos rastreadores.

Funções:

- gerenciamento de conexões
- ACK
- heartbeat
- publicação das mensagens brutas no Kafka

---

## Device Session Service

Gerencia o estado das conexões.

Responsável por:

- dispositivos online
- heartbeat
- comandos pendentes
- sessão TCP

---

## Decoder Service

Converte mensagens binárias para um modelo de dados comum.

Cada protocolo possui seu próprio decoder.

Exemplos:

- Teltonika
- Suntech
- Queclink
- Concox

---

## Persist Service

Responsável exclusivamente pela escrita.

Consome eventos do Kafka e persiste:

- posições
- status
- dispositivos

---

## API Service

Disponibiliza APIs REST para consulta.

Exemplos:

- veículos
- posições
- histórico
- viagens
- relatórios

---

## Auth Service

Responsável por:

- autenticação
- autorização
- JWT
- usuários
- empresas

---

## Trip Service

Calcula automaticamente:

- início da viagem
- fim da viagem
- distância
- tempo
- velocidade média

---

## Alert Service

Processa regras de negócio.

Exemplos:

- excesso de velocidade
- ignição
- bateria
- geofence
- jammer
- GPS inválido

---

## Notification Service

Envia notificações através de:

- Push
- Email
- SMS
- WhatsApp

---

# Fluxo de mensagens

```text
Tracker

↓

Gateway

↓

raw_messages

↓

Decoder

↓

decoded_positions

↓

Persist Service

↓

PostgreSQL
```

Serviços como Trips, Alerts e Analytics consomem os mesmos eventos sem impactar os demais componentes.

---

# Banco de Dados

PostgreSQL + PostGIS

Principais entidades:

- Device
- Vehicle
- Position
- VehicleStatus
- Trip
- Alert
- Company
- User

---

# Apache Kafka

Principais tópicos:

- raw_messages
- decoded_positions
- trip_events
- alert_events
- notification_events
- audit_logs

---

# Escalabilidade

Todos os serviços podem ser escalados horizontalmente.

Exemplo:

```
3 Gateway Services

8 Decoder Services

6 Persist Services

4 API Services
```

O Apache Kafka distribui automaticamente a carga entre os consumidores.

---

# Observabilidade

Todos os microsserviços disponibilizam:

- Health Check
- Métricas
- Logs estruturados
- Tracing distribuído

---

# Roadmap

- [x] Arquitetura inicial
- [ ] Gateway TCP
- [ ] Decoder Teltonika
- [ ] Persistência
- [ ] API REST
- [ ] Autenticação
- [ ] Viagens
- [ ] Alertas
- [ ] Geofences
- [ ] Dashboard
- [ ] WebSocket
- [ ] Infrações de trânsito
- [ ] CAN Bus
- [ ] Analytics
- [ ] Machine Learning

---

# Objetivos

- Alta disponibilidade
- Escalabilidade horizontal
- Arquitetura orientada a eventos
- Fácil manutenção
- Fácil adição de novos protocolos
- Processamento em tempo real
