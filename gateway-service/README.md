# gateway-service

Serviço de gateway do **TrackFlow**, desenvolvido com **Java 21** e **Quarkus**.

Este serviço representa a base da infraestrutura do gateway da plataforma, fornecendo observabilidade, integração com Apache Kafka, documentação da API e endpoints de monitoramento. As funcionalidades de comunicação com rastreadores e processamento de telemetria serão implementadas nas próximas etapas do projeto.

---

# Tecnologias

## Framework

* Java 21
* Quarkus 3.37.4

## Mensageria

* Apache Kafka

## Observabilidade

* OpenTelemetry
* Micrometer
* Prometheus

## Documentação

* SmallRye OpenAPI
* Swagger UI

## Monitoramento

* SmallRye Health

## Serialização

* Jackson

## Testes

* JUnit 5
* REST Assured

---

# Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java 21
* Maven 3.9+
* Docker
* Docker Compose

---

# Estrutura do projeto

```text
gateway-service/
├── src/
│   ├── main/
│   └── test/
├── Dockerfile
├── pom.xml
└── README.md
```

---

# Funcionalidades atuais

Atualmente o serviço disponibiliza:

* Estrutura base em Quarkus;
* Configuração da integração com Apache Kafka;
* Exportação de traces via OpenTelemetry;
* Métricas utilizando Micrometer;
* Health Checks;
* Documentação OpenAPI;
* Swagger UI;
* Configuração por perfis (`dev`, `test` e `prod`);
* Logs estruturados em JSON para produção.

---

# Configuração

A configuração principal da aplicação está em:

```text
src/main/resources/application.yaml
```

Os perfis suportados são:

* `dev`
* `test`
* `prod`

---

# Executando localmente

## Modo desenvolvimento

Dentro da pasta do serviço:

```bash
./mvnw quarkus:dev
```

O Dev UI ficará disponível em:

```text
http://localhost:8080/q/dev
```

---

## Build

Gerar a aplicação:

```bash
./mvnw package
```

Executar os testes:

```bash
./mvnw test
```

Gerar imagem nativa:

```bash
./mvnw package -Dnative
```

Utilizando container para o build nativo:

```bash
./mvnw package \
  -Dnative \
  -Dquarkus.native.container-build=true
```

---

# Executando com Docker Compose

Na raiz do monorepo execute:

```bash
docker compose -f docker-compose.dev.yaml up --build
```

Em background:

```bash
docker compose -f docker-compose.dev.yaml up -d --build
```

Parar os serviços:

```bash
docker compose -f docker-compose.dev.yaml down
```

---

# Infraestrutura local

O ambiente de desenvolvimento utiliza Docker Compose para iniciar os serviços necessários ao ecossistema da aplicação.

São inicializados:

* Apache Kafka
* PostgreSQL + PostGIS
* Redis
* OpenTelemetry Collector
* Gateway Service

---

# Endpoints

## Health

```text
/q/health
/q/health/live
/q/health/ready
```

---

## Métricas

```text
/q/metrics
```

---

## OpenAPI

```text
/q/openapi
```

---

## Swagger UI

```text
/q/swagger-ui
```

---

## Dev UI

Disponível apenas no perfil `dev`.

```text
/q/dev
```

---

# Kafka

O endereço do broker é configurado através da variável:

```text
KAFKA_BOOTSTRAP_SERVERS
```

Valor padrão para desenvolvimento:

```text
localhost:9092
```

Ao executar via Docker Compose, a comunicação interna utiliza:

```text
kafka:9092
```

---

# OpenTelemetry

O serviço está preparado para exportação de traces utilizando OTLP.

Variáveis suportadas:

```text
OTEL_EXPORTER_OTLP_ENDPOINT
OTEL_EXPORTER_OTLP_PROTOCOL
OTEL_TRACES_SAMPLER
```

No ambiente Docker Compose, o endpoint padrão é:

```text
http://otel-collector:4317
```

---

# Observabilidade

Atualmente o serviço oferece:

* Health Checks
* Métricas via Micrometer
* Traces distribuídos com OpenTelemetry
* Logs estruturados em JSON (perfil `prod`)

---

# Perfis

## dev

* Logs em nível `DEBUG`;
* Dev UI habilitada;
* Configuração voltada ao desenvolvimento local.

## test

* Logs em nível `INFO`;
* Utilizado durante a execução dos testes automatizados.

## prod

* Logs em JSON;
* OpenTelemetry habilitado;
* Configuração para execução em ambiente produtivo.

---

# Próximas implementações

As próximas etapas previstas para este serviço incluem:

* Implementação do gateway TCP;
* Recepção de conexões dos rastreadores;
* Decodificação dos protocolos suportados;
* Publicação de eventos no Apache Kafka;
* Integração com os demais microsserviços da plataforma;
* Evolução da observabilidade com métricas e spans de negócio;
* Autenticação e autorização;
* Rate limiting e controle de acesso.

---

# Licença

Este projeto faz parte do ecossistema **TrackFlow** e é destinado ao desenvolvimento da plataforma.
