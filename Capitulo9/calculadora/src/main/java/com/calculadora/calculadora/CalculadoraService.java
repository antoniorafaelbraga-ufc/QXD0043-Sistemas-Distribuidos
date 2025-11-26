package com.calculadora.calculadora;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(serviceName = "CalculadoraService", targetNamespace = "http://calculadora.com/")
public class CalculadoraService {

    @WebMethod
    public double somar(double a, double b) {
        return a + b;
    }

    @WebMethod
    public double subtrair(double a, double b) {
        return a - b;
    }

    @WebMethod
    public double multiplicar(double a, double b) {
        return a * b;
    }

    @WebMethod
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero");
        }
        return a / b;
    }
}
