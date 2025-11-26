# Exemplos de Requisições SOAP para Consumir o CalculadoraService

Este arquivo contém exemplos de requisições SOAP (XML) que você pode usar para testar o serviço CalculadoraService publicado em `http://localhost:8080/services/calculadora`.

## Como usar:

### Opção 1: com curl (Windows/Linux/Mac)
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-request-somar.xml
```

### Opção 2: com Postman
1. Abra o Postman
2. Crie uma nova requisição `POST`
3. URL: `http://localhost:8080/services/calculadora`
4. Aba "Headers": adicione `Content-Type: text/xml; charset=utf-8`
5. Aba "Body": escolha "raw" e cole um dos exemplos XML abaixo
6. Clique em "Send"

### Opção 3: com SoapUI
1. Abra SoapUI
2. Crie um novo projeto SOAP
3. URL do WSDL: `http://localhost:8080/services/calculadora?wsdl`
4. SoapUI vai gerar automaticamente os templates de requisição

---

## Exemplo 1: Somar (3 + 5)

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

**Resposta esperada:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <ns2:somarResponse xmlns:ns2="http://calculadora.com/">
      <return>8.0</return>
    </ns2:somarResponse>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## Exemplo 2: Subtrair (10 - 3)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cal="http://calculadora.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <cal:subtrair>
      <a>10</a>
      <b>3</b>
    </cal:subtrair>
  </soapenv:Body>
</soapenv:Envelope>
```

**Resposta esperada:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <ns2:subtrairResponse xmlns:ns2="http://calculadora.com/">
      <return>7.0</return>
    </ns2:subtrairResponse>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## Exemplo 3: Multiplicar (4 * 7)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cal="http://calculadora.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <cal:multiplicar>
      <a>4</a>
      <b>7</b>
    </cal:multiplicar>
  </soapenv:Body>
</soapenv:Envelope>
```

**Resposta esperada:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <ns2:multiplicarResponse xmlns:ns2="http://calculadora.com/">
      <return>28.0</return>
    </ns2:multiplicarResponse>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## Exemplo 4: Dividir (20 / 4)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cal="http://calculadora.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <cal:dividir>
      <a>20</a>
      <b>4</b>
    </cal:dividir>
  </soapenv:Body>
</soapenv:Envelope>
```

**Resposta esperada:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <ns2:dividirResponse xmlns:ns2="http://calculadora.com/">
      <return>5.0</return>
    </ns2:dividirResponse>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## Exemplo 5: Dividir por Zero (Erro)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cal="http://calculadora.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <cal:dividir>
      <a>10</a>
      <b>0</b>
    </cal:dividir>
  </soapenv:Body>
</soapenv:Envelope>
```

**Resposta esperada (SOAP Fault):**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <soapenv:Fault>
      <faultcode>soapenv:Server</faultcode>
      <faultstring>Divisão por zero</faultstring>
      <detail>
        <java.lang.IllegalArgumentException>Divisão por zero</java.lang.IllegalArgumentException>
      </detail>
    </soapenv:Fault>
  </soapenv:Body>
</soapenv:Envelope>
```

---

## Dicas Adicionais

### Acessar o WSDL
```
http://localhost:8080/services/calculadora?wsdl
```

### Testar com PowerShell (Windows)
```powershell
$soapRequest = @"
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
"@

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $soapRequest
```

### Testar com JavaScript/Node.js
Veja o arquivo `cliente-node.js` para um exemplo completo.

---

## Geração de Stubs Java com wsimport

Se desejar gerar classes Java tipadas para consumir o serviço (recomendado para clientes production):

```bash
# Windows
cd c:\Users\rafae\git-ufc\QXD0043-Sistemas-Distribuidos\Capitulo9\calculadora\src\main\java\com\calculadora\calculadora
wsimport -keep http://localhost:8080/services/calculadora?wsdl

# Linux/Mac
wsimport -keep http://localhost:8080/services/calculadora?wsdl
```

Isso gera automaticamente:
- `CalculadoraService.java` (Service class)
- `CalculadoraServicePort.java` (Port/Binding interface)
- `Somar.java`, `SomarResponse.java`, `Subtrair.java`, etc. (Request/Response classes)

Então você pode usar assim:

```java
CalculadoraService service = new CalculadoraService();
CalculadoraServicePort port = service.getCalculadoraServicePort();

double resultado = port.somar(3, 5);
System.out.println("3 + 5 = " + resultado);
```
