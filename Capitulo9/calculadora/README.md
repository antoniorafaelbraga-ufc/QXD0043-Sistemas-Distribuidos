# README - CalculadoraService com Apache CXF

Versão modernizada do projeto Calculadora que expõe um serviço SOAP (JAX-WS) com WSDL e XML automaticamente gerados.

## Arquitetura

```
┌─────────────────────────────────────┐
│      Spring Boot 4.0.0              │
│  (Tomcat embedded na porta 8080)    │
│                                     │
│  ┌──────────────────────────────┐  │
│  │   Apache CXF (JAX-WS)        │  │
│  │                              │  │
│  │  Endpoint: /services/       │  │
│  │           calculadora        │  │
│  │                              │  │
│  │  ✓ WSDL automático           │  │
│  │  ✓ SOAP 1.1                  │  │
│  │  ✓ XML Schema                │  │
│  └──────────────────────────────┘  │
│                                     │
│  ┌──────────────────────────────┐  │
│  │   CalculadoraService         │  │
│  │  - somar()                   │  │
│  │  - subtrair()                │  │
│  │  - multiplicar()             │  │
│  │  - dividir()                 │  │
│  └──────────────────────────────┘  │
└─────────────────────────────────────┘
```

## URLs Principais

| Recurso | URL |
|---------|-----|
| WSDL | `http://localhost:8080/services/calculadora?wsdl` |
| Endpoint SOAP | `http://localhost:8080/services/calculadora` |
| Serviço REST (opcional) | `http://localhost:8080/` |

## Estrutura do Projeto

```
calculadora/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/calculadora/calculadora/
│   │   │   ├── CalculadoraApplication.java      (Spring Boot main)
│   │   │   ├── CalculadoraService.java          (@WebService - implementação)
│   │   │   ├── CalculadoraClient.java           (Cliente Java exemplo)
│   │   │   └── CxfConfig.java                   (@Configuration - publica endpoint)
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/index.html
│   └── test/
│       └── java/com/calculadora/calculadora/
│           └── CalculadoraClientTest.java      (Testes SOAP)
├── soap-examples/                              (Exemplos XML de requisições)
│   ├── somar.xml
│   ├── subtrair.xml
│   ├── multiplicar.xml
│   ├── dividir.xml
│   └── dividir-zero.xml
├── SOAP-EXEMPLOS.md                            (Guia completo com exemplos)
├── TESTE-CURL-POWERSHELL.md                    (Comandos curl e PowerShell)
├── cliente-node.js                             (Cliente Node.js)
└── README.md                                   (Este arquivo)
```

## Dependências Principais

```xml
<!-- Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<!-- Apache CXF (JAX-WS com Jakarta EE) -->
<dependency>
    <groupId>org.apache.cxf</groupId>
    <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
    <version>4.0.0</version>
</dependency>
```

## Como Executar

### 1. Build
```bash
cd calculadora
mvnw.cmd clean package -DskipTests
```

### 2. Run
```bash
mvnw.cmd spring-boot:run
```

Você verá no console:
```
Creating Service {http://calculadora.com/}CalculadoraService from class com.calculadora.calculadora.CalculadoraService
Setting the server's publish address to be /services/calculadora
Tomcat started on port 8080 (http)
```

### 3. Testar o Serviço

#### Opção A: Acessar WSDL no navegador
```
http://localhost:8080/services/calculadora?wsdl
```

#### Opção B: Usar curl
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-examples/somar.xml
```

#### Opção C: Usar PowerShell (Windows)
```powershell
$body = Get-Content -Raw soap-examples/somar.xml

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $body
```

#### Opção D: Usar Postman
1. Criar requisição `POST` para `http://localhost:8080/services/calculadora`
2. Header: `Content-Type: text/xml; charset=utf-8`
3. Body (raw): Cole o XML de uma das requisições em `soap-examples/`

#### Opção E: Usar SoapUI
1. Create new SOAP Project
2. WSDL URL: `http://localhost:8080/services/calculadora?wsdl`
3. SoapUI gera automaticamente os templates

#### Opção F: Cliente Java Test
```bash
mvnw.cmd test -Dtest=CalculadoraClientTest
```

#### Opção G: Cliente Node.js
```bash
npm install soap
node cliente-node.js
```

## Exemplos de Requisições SOAP

### Somar (3 + 5 = 8)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cal="http://calculadora.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <cal:somar>
      <a>3</a>
      <b>5</b>
    </cal:somar>
  </soapenv:Body>
</soapenv:Envelope>
```

### Resposta
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <ns2:somarResponse xmlns:ns2="http://calculadora.com/">
      <return>8.0</return>
    </ns2:somarResponse>
  </soapenv:Body>
</soapenv:Envelope>
```

Veja mais exemplos em `SOAP-EXEMPLOS.md`.

## Gerando Stubs Java (wsimport)

Se desejar gerar classes Java tipadas para consumir o serviço:

```bash
# Windows
cd src\main\java\com\calculadora\calculadora
wsimport -keep http://localhost:8080/services/calculadora?wsdl

# Linux/Mac
cd src/main/java/com/calculadora/calculadora
wsimport -keep http://localhost:8080/services/calculadora?wsdl
```

Então use assim:

```java
CalculadoraService service = new CalculadoraService();
CalculadoraServicePort port = service.getCalculadoraServicePort();

double resultado = port.somar(3, 5);
System.out.println("3 + 5 = " + resultado); // Output: 3 + 5 = 8.0
```

## Métodos Disponíveis

| Método | Assinatura | Descrição |
|--------|-----------|-----------|
| `somar` | `double somar(double a, double b)` | Retorna a + b |
| `subtrair` | `double subtrair(double a, double b)` | Retorna a - b |
| `multiplicar` | `double multiplicar(double a, double b)` | Retorna a * b |
| `dividir` | `double dividir(double a, double b)` | Retorna a / b; lança `IllegalArgumentException` se b = 0 |

## Tratamento de Erros

### Divisão por Zero
```xml
<!-- Requisição -->
<cal:dividir>
  <a>10</a>
  <b>0</b>
</cal:dividir>

<!-- Resposta (SOAP Fault) -->
<soapenv:Fault>
  <faultcode>soapenv:Server</faultcode>
  <faultstring>Divisão por zero</faultstring>
  <detail>
    <java.lang.IllegalArgumentException>Divisão por zero</java.lang.IllegalArgumentException>
  </detail>
</soapenv:Fault>
```

## Documentação Adicional

- **Exemplos SOAP** → `SOAP-EXEMPLOS.md`
- **Testes com curl/PowerShell** → `TESTE-CURL-POWERSHELL.md`
- **Apache CXF** → https://cxf.apache.org/
- **Spring Boot + CXF** → https://cxf.apache.org/docs/springboot.html
- **JAX-WS (Jakarta)** → https://jakarta.ee/specifications/webservices/

## Tecnologias

- **Java 17** (via Maven Compiler)
- **Spring Boot 4.0.0**
- **Apache CXF 4.0.0** (JAX-WS)
- **Jakarta EE 10** (namespaces, APIs)
- **Tomcat 11** (servlet container)

## Autor

Criado para fins educacionais no contexto de sistemas distribuídos.

---

**Última atualização:** 25 de novembro de 2025
