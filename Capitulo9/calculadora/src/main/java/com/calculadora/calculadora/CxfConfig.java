package com.calculadora.calculadora;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public CalculadoraService calculadoraService() {
        return new CalculadoraService();
    }

    @Bean
    public Endpoint calculadoraEndpoint(Bus bus, CalculadoraService calculadoraService) {
        EndpointImpl endpoint = new EndpointImpl(bus, calculadoraService);
        // publica em /services/calculadora (após o contexto raiz do app)
        endpoint.publish("/services/calculadora");
        return endpoint;
    }
}
