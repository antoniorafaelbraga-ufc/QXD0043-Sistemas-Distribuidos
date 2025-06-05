package com.exemplo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor pronto na porta 5000...");
            // Espera por conexões
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado!");
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    // Recebe a requisição
                    String input = in.readLine();
                    System.out.println("Requisição recebida: " + input);
                    // Processa a chamada ao método
                    if (input.startsWith("sayHello:")) {
                        String name = input.split(":")[1];
                        HelloService service = new HelloServiceImpl();
                        String response = service.sayHello(name);
                        // Envia a resposta de volta ao cliente
                        out.println(response);
                    } else {
                        out.println("Método não suportado.");
                    }
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}