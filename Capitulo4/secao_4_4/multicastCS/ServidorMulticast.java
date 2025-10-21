package secao_4_4.multicastCS;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorMulticast {

    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getByName("239.0.0.1");
            DatagramSocket ds = new DatagramSocket();
            
            System.out.println("Servidor Multicast iniciado...");
            
            // Envia mensagens periodicamente
            int contador = 0;
            while (true) {
                String mensagem = "Ola Mundo SD 2025.1 - Mensagem #" + contador;
                byte[] b = mensagem.getBytes();
                DatagramPacket pkg = new DatagramPacket(b, b.length, addr, 12347);
                
                ds.send(pkg);
                System.out.println("Enviado: " + mensagem);
                
                contador++;
                Thread.sleep(3000); // Envia a cada 3 segundos
            }
            
        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}