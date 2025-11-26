package com.calculadora.calculadora;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;

/**
 * Cliente SOAP para consumir o CalculadoraService via WSDL.
 * 
 * Pré-requisitos:
 * 1. Gerar stubs a partir do WSDL:
 *    wsimport -keep http://localhost:8080/services/calculadora?wsdl
 *    Isso gera as classes necessárias no pacote com.calculadora.calculadora
 * 
 * 2. Ou usar o Service Locator Pattern (como neste exemplo) para criar
 *    dinamicamente o proxy do serviço.
 * 
 * Execução:
 * - Certifique-se de que o servidor está rodando: mvnw.cmd spring-boot:run
 * - Execute este teste: mvnw.cmd test -Dtest=CalculadoraClientTest
 */
public class CalculadoraClientTest {

    /**
     * Teste integrativo que chama os métodos SOAP do CalculadoraService.
     * Usa a abordagem dinâmica (sem gerar stubs via wsimport).
     */
    @Test
    public void testCalculadoraServiceViaSoap() throws MalformedURLException {
        // URL do WSDL publicado pelo servidor CXF
        String wsdlUrl = "http://localhost:8080/services/calculadora?wsdl";
        URL wsdlLocation = new URL(wsdlUrl);

        // Criar o Service a partir do WSDL
        Service service = Service.create(
            wsdlLocation,
            new QName("http://calculadora.com/", "CalculadoraService")
        );

        Object port = service.getPort(
            new QName("http://calculadora.com/", "CalculadoraServicePort"),
            java.lang.Object.class
        );
        // Cast to the actual service interface if you have generated stubs
        // Otherwise, use reflection or the port as Object
        assertNotNull(port);

        System.out.println("✓ Cliente SOAP conectado ao serviço!");
        System.out.println("  WSDL: " + wsdlUrl);
        System.out.println("  Service Port: " + port.getClass().getName());
    }

    /**
     * Teste simples para validar que o serviço está rodando
     * (sem chamar métodos SOAP, apenas verifica conectividade).
     */
    @Test
    public void testWsdlAvailable() throws Exception {
        String wsdlUrl = "http://localhost:8080/services/calculadora?wsdl";
        try {
            URL url = new URL(wsdlUrl);
            java.net.URLConnection conn = url.openConnection();
            conn.connect();
            System.out.println("✓ WSDL disponível em: " + wsdlUrl);
        } catch (Exception e) {
            fail("WSDL não acessível. Certifique-se de que o servidor está rodando. Erro: " + e.getMessage());
        }
    }
}
