# Capítulo 18 — Replicação e Tolerância a Falhas

Exemplo de replicação com Spring Boot.

O projeto mantém uma lista de réplicas disponíveis. Quando uma réplica falha, o sistema tenta executar a operação na próxima réplica disponível, simulando o mecanismo de **failover**.

## Como executar

No PowerShell, dentro da pasta do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação inicia em:

```text
http://localhost:8084
```

## Rotas

| Método | URL | Descrição |
|---|---|---|
| `POST` | `/replicacao/adicionar?replica=...` | Adiciona uma réplica |
| `POST` | `/replicacao/falha?replica=...` | Simula falha em uma réplica |
| `GET` | `/replicacao/status` | Lista o estado das réplicas |
| `GET` | `/replicacao/executar?operacao=...` | Executa uma operação com failover |

## Testes no Postman

### 1. Adicionar a primeira réplica

**POST**

```text
http://localhost:8084/replicacao/adicionar?replica=Replica-1
```

**Resposta esperada**

```json
{
  "mensagem": "Réplica adicionada",
  "replica": "Replica-1"
}
```

### 2. Adicionar a segunda réplica

**POST**

```text
http://localhost:8084/replicacao/adicionar?replica=Replica-2
```

### 3. Adicionar a terceira réplica

**POST**

```text
http://localhost:8084/replicacao/adicionar?replica=Replica-3
```

### 4. Consultar o estado das réplicas

**GET**

```text
http://localhost:8084/replicacao/status
```

**Resposta esperada**

```json
{
  "Replica-1": true,
  "Replica-2": true,
  "Replica-3": true
}
```

O valor `true` indica que a réplica está disponível.

### 5. Simular falha na primeira réplica

**POST**

```text
http://localhost:8084/replicacao/falha?replica=Replica-1
```

**Resposta esperada**

```json
{
  "mensagem": "Falha simulada",
  "replica": "Replica-1"
}
```

### 6. Executar operação com failover

**GET**

```text
http://localhost:8084/replicacao/executar?operacao=CONSULTA
```

**Resposta esperada**

```json
{
  "resultado": "Operação 'CONSULTA' executada pela Replica-2"
}
```

A operação foi executada pela `Replica-2` porque a `Replica-1` estava indisponível. Isso demonstra o failover.

### 7. Simular falha em todas as réplicas

Simule falha também na segunda e na terceira réplica:

**POST**

```text
http://localhost:8084/replicacao/falha?replica=Replica-2
```

**POST**

```text
http://localhost:8084/replicacao/falha?replica=Replica-3
```

Execute novamente:

**GET**

```text
http://localhost:8084/replicacao/executar?operacao=CONSULTA
```

**Resposta esperada**

```json
{
  "erro": "Nenhuma réplica está disponível para executar a operação"
}
```

Nesse caso, a API retorna o status HTTP `503 Service Unavailable`.
```