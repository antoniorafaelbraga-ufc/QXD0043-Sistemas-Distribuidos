package secao_4_4.multicastP2P;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class MulticastPeerChat {
    private static final String MULTICAST_ADDR = "239.0.0.1";
    private static final int PORT = 6789;
    
    public static void main(String args[]) {
        MulticastSocket socket = null;
        
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDR);
            socket = new MulticastSocket(PORT);
            socket.joinGroup(group);
            
            InetAddress localAddr = InetAddress.getLocalHost();
            String peerId = localAddr.getHostAddress();
            
            System.out.println("=== Chat P2P Multicast ===");
            System.out.println("Seu ID: " + peerId);
            System.out.println("Grupo: " + MULTICAST_ADDR + ":" + PORT);
            System.out.println("Digite suas mensagens (ou 'sair' para encerrar)\n");
            
            // Thread para RECEBER mensagens
            MulticastSocket finalSocket = socket;
            Thread receiver = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        byte[] buffer = new byte[1000];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        finalSocket.receive(packet);
                        
                        String message = new String(packet.getData(), 0, packet.getLength());
                        String sender = packet.getAddress().getHostAddress();
                        
                        // Não exibe mensagens próprias
                        if (!sender.equals(peerId)) {
                            System.out.println("\n[" + sender + "]: " + message);
                            System.out.print("Você: ");
                        }
                    }
                } catch (IOException e) {
                    if (!Thread.currentThread().isInterrupted()) {
                        System.out.println("Erro ao receber: " + e.getMessage());
                    }
                }
            });
            receiver.setDaemon(true);
            receiver.start();
            
            // Thread para ENVIAR mensagens
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Você: ");
                String input = scanner.nextLine();
                
                if (input.equalsIgnoreCase("sair")) {
                    break;
                }
                
                if (!input.trim().isEmpty()) {
                    byte[] data = input.getBytes();
                    DatagramPacket packet = new DatagramPacket(data, data.length, group, PORT);
                    socket.send(packet);
                }
            }
            
            receiver.interrupt();
            socket.leaveGroup(group);
            scanner.close();
            System.out.println("\nDesconectado do chat!");
            
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}