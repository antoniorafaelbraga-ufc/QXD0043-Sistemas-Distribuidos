package com.calculadora.calculadora;

import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

/**
 * Cliente Java standalone que chama o serviço SOAP CalculadoraService.
 * 
 * Este cliente usa a abordagem dinâmica (sem stubs pré-gerados via wsimport).
 * 
 * Para usar:
 * 1. Certifique-se de que o servidor está rodando:
 *    cd c:\Users\rafae\git-ufc\QXD0043-Sistemas-Distribuidos\Capitulo9\calculadora
 *    mvnw.cmd spring-boot:run
 * 
 * 2. Compile e execute este arquivo:
 *    javac -cp ".:libs/*" CalculadoraClient.java
 *    java -cp ".:libs/*" com.calculadora.calculadora.CalculadoraClient
 * 
 * Ou, se compilado via Maven, execute:
 *    mvnw.cmd exec:java -Dexec.mainClass="com.calculadora.calculadora.CalculadoraClient"
 */
public class CalculadoraClient {

    public static void main(String[] args) {
        System.out.println("===== Cliente SOAP - CalculadoraService =====\n");

        try {
            // URL do WSDL
            String wsdlUrl = "http://localhost:8080/services/calculadora?wsdl";
            System.out.println("Conectando ao serviço SOAP:");
            System.out.println("  WSDL: " + wsdlUrl + "\n");

            // Criar o Service a partir do WSDL
            URL url = new URL(wsdlUrl);
            Service service = Service.create(
                url,
                new QName("http://calculadora.com/", "CalculadoraService")
            );

            // Obter o port (proxy) para chamar métodos do serviço
            Object port = service.getPort(
                new QName("http://calculadora.com/", "CalculadoraServicePort"),
                Object.class
            );

            System.out.println("✓ Conectado ao serviço!\n");

            // Chamar métodos via reflexão (já que não temos os stubs pré-gerados)
            // Para isso, recomenda-se usar wsimport para gerar as classes tipadas

            System.out.println("Observação:");
            System.out.println("  Para chamar métodos com type-safety, execute:");
            System.out.println("    wsimport -keep " + wsdlUrl);
            System.out.println("  Isso gera as classes de stub no pacote com.calculadora.calculadora");
            System.out.println("\nAlternativa: Use SOAP direto via HTTP (veja exemplos em 'soap-requests.xml')");

        } catch (Exception e) {
            System.err.println("✗ Erro ao conectar ao serviço:");
            e.printStackTrace();
        }
    }
}
