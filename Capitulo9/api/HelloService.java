package main.java.com.exemplo;

import javax.ejb.Stateless;

// EJB Stateless que retorna uma mensagem
@Stateless
public class HelloService {
    public String sayHello(String name) {
        return "Olá, " + name + "! Bem-vindo à API EJB.";
    }
}