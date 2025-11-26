# Testes com curl e PowerShell para o CalculadoraService

## Com curl (Windows, Linux, Mac)

### Somar (3 + 5)
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-examples/somar.xml
```

### Subtrair (10 - 3)
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-examples/subtrair.xml
```

### Multiplicar (4 * 7)
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-examples/multiplicar.xml
```

### Dividir (20 / 4)
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-examples/dividir.xml
```

### Dividir por Zero (erro esperado)
```bash
curl -X POST http://localhost:8080/services/calculadora \
  -H "Content-Type: text/xml; charset=utf-8" \
  -d @soap-examples/dividir-zero.xml
```

---

## Com PowerShell (Windows)

### Somar (3 + 5)
```powershell
$body = Get-Content -Raw soap-examples/somar.xml

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $body
```

### Subtrair (10 - 3)
```powershell
$body = Get-Content -Raw soap-examples/subtrair.xml

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $body
```

### Multiplicar (4 * 7)
```powershell
$body = Get-Content -Raw soap-examples/multiplicar.xml

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $body
```

### Dividir (20 / 4)
```powershell
$body = Get-Content -Raw soap-examples/dividir.xml

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $body
```

### Dividir por Zero (erro esperado)
```powershell
$body = Get-Content -Raw soap-examples/dividir-zero.xml

Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora" `
  -Method POST `
  -ContentType "text/xml; charset=utf-8" `
  -Body $body
```

---

## Acessar o WSDL

```bash
# Com curl
curl http://localhost:8080/services/calculadora?wsdl

# Com PowerShell
Invoke-WebRequest -Uri "http://localhost:8080/services/calculadora?wsdl" | Select-Object -ExpandProperty Content
```

---

## Dicas

1. Certifique-se de que o servidor está rodando:
   ```
   mvnw.cmd spring-boot:run
   ```

2. Se quiser salvar a resposta em um arquivo:
   ```bash
   curl -X POST http://localhost:8080/services/calculadora \
     -H "Content-Type: text/xml; charset=utf-8" \
     -d @soap-examples/somar.xml \
     -o resposta.xml
   ```

3. Para ver headers e mensagens de debug com curl:
   ```bash
   curl -v -X POST http://localhost:8080/services/calculadora \
     -H "Content-Type: text/xml; charset=utf-8" \
     -d @soap-examples/somar.xml
   ```
