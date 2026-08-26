
# Capítulos 16 e 17 — Transações com Spring Boot

Exemplo de transferência bancária usando Spring Boot, Spring Data JPA, H2 e `@Transactional`.

O objetivo é demonstrar:

- **Commit:** quando a transferência é válida, débito e crédito são confirmados.
- **Rollback:** quando ocorre erro, como saldo insuficiente, nenhuma alteração é persistida.

## Como executar

No PowerShell, dentro da pasta do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação inicia em:

```text
http://localhost:8083
```

## Rotas

| Método | URL | Descrição |
|---|---|---|
| `POST` | `/transacoes/contas` | Cria uma conta bancária |
| `GET` | `/transacoes/contas` | Lista todas as contas |
| `POST` | `/transacoes/transferir` | Faz uma transferência entre contas |
| `GET` | `/h2-console` | Abre o console do banco H2 |

## Testes no Postman

### 1. Criar conta da Alice

**POST**

```text
http://localhost:8083/transacoes/contas
```

No Postman, selecione **Body → raw → JSON**:

```json
{
  "id": "C001",
  "titular": "Alice",
  "saldo": "1000"
}
```

### 2. Criar conta do Bob

**POST**

```text
http://localhost:8083/transacoes/contas
```

**Body → raw → JSON**

```json
{
  "id": "C002",
  "titular": "Bob",
  "saldo": "500"
}
```

### 3. Listar contas

**GET**

```text
http://localhost:8083/transacoes/contas
```

**Resposta esperada**

```json
[
  {
    "id": "C001",
    "titular": "Alice",
    "saldo": 1000
  },
  {
    "id": "C002",
    "titular": "Bob",
    "saldo": 500
  }
]
```

### 4. Transferência válida — commit

**POST**

```text
http://localhost:8083/transacoes/transferir
```

**Body → raw → JSON**

```json
{
  "origem": "C001",
  "destino": "C002",
  "valor": "200"
}
```

**Resposta esperada**

```json
{
  "mensagem": "Transferência concluída"
}
```

Execute novamente a rota de listar contas. Os saldos devem ser:

```text
Alice: 800
Bob: 700
```

### 5. Transferência inválida — rollback

**POST**

```text
http://localhost:8083/transacoes/transferir
```

**Body → raw → JSON**

```json
{
  "origem": "C002",
  "destino": "C001",
  "valor": "99999"
}
```

**Resposta esperada**

```json
{
  "erro": "Saldo insuficiente",
  "resultado": "rollback"
}
```

Execute novamente:

**GET**

```text
http://localhost:8083/transacoes/contas
```

Os saldos devem continuar os mesmos:

```text
Alice: 800
Bob: 700
```

Isso prova que a transferência inválida sofreu rollback.

## Console do banco H2

Acesse:

```text
http://localhost:8083/h2-console
```

Use estes dados:

```text
JDBC URL: jdbc:h2:mem:transacoesdb
User Name: sa
Password: deixe vazio
```