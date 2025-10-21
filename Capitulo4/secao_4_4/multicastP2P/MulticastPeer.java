package secao_4_4.multicastP2P;

import java.net.*;
import java.io.*;

public class MulticastPeer {
    public static void main(String args[]) {
        MulticastSocket s = null;
        
        try {
            InetAddress group = InetAddress.getByName("239.0.0.1");
            s = new MulticastSocket(6789);
            
            // Obtém o endereço local para identificar mensagens próprias
            InetAddress localAddr = InetAddress.getLocalHost();
            
            s.joinGroup(group);
            System.out.println("Peer conectado ao grupo 239.0.0.1:6789");
            System.out.println("Endereço local: " + localAddr.getHostAddress());
            
            // Envia mensagem para o grupo
            String mensagem = "Ola mundo SD 2021.1 de " + localAddr.getHostAddress();
            byte[] m = mensagem.getBytes();
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);
            System.out.println("Mensagem enviada: " + mensagem + "\n");
            
            // Recebe mensagens de outros peers
            System.out.println("Aguardando 3 mensagens de outros peers...\n");
            
            for (int i = 0; i < 3; i++) {
                byte[] buffer = new byte[1000]; // Novo buffer a cada iteração
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                
                // Usa apenas os bytes válidos recebidos
                String received = new String(messageIn.getData(), 0, messageIn.getLength());
                String senderAddr = messageIn.getAddress().getHostAddress();
                
                // Verifica se é mensagem própria
                if (senderAddr.equals(localAddr.getHostAddress())) {
                    System.out.println("[PRÓPRIA] " + received);
                } else {
                    System.out.println("[" + senderAddr + "] " + received);
                }
            }
            
            s.leaveGroup(group);
            System.out.println("\nPeer desconectado do grupo");
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null)
                s.close();
        }
    }
}