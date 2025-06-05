package com.exemplo;

// Implementação do serviço
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Olá, " + name + "! Este é um exemplo de RPC com sockets.";
    }
}