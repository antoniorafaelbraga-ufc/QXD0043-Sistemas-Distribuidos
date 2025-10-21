package secao_4_4.multicastCS;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMulticast {

    public static void main(String[] args) {
        MulticastSocket mcs = null;
        
        try {
            System.out.println("Cliente Multicast iniciado...");
            
            // Cria o socket UMA VEZ, fora do loop
            mcs = new MulticastSocket(12347);
            InetAddress grp = InetAddress.getByName("239.0.0.1");
            mcs.joinGroup(grp);
            
            System.out.println("Aguardando mensagens no grupo 239.0.0.1:12347...\n");
            
            // Loop para receber mensagens continuamente
            while (true) {
                byte rec[] = new byte[256];
                DatagramPacket pkg = new DatagramPacket(rec, rec.length);
                
                mcs.receive(pkg); // Bloqueia até receber uma mensagem
                
                // Usa apenas os bytes recebidos, não todo o buffer
                String data = new String(pkg.getData(), 0, pkg.getLength());
                System.out.println("Dados recebidos: " + data);
            }
            
        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (mcs != null && !mcs.isClosed()) {
                mcs.close();
            }
        }
    }
}