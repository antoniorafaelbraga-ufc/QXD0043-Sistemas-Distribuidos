package com.exemplo;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class Server {
    public static void main(String[] args) {
        try {
            // Inicia o registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);
            
            // Instancia o serviço e o registra
            Hello hello = new HelloImpl();
            Naming.rebind("HelloService", hello);
            System.out.println("Servidor RMI pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}