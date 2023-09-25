package TCP;

import entidades.Pessoa;
import java.net.*;
import java.io.*;

public class TCPClient {

    public static void main(String args[]) {
        // arguments supply message and hostname

        Pessoa pss = new Pessoa("Israel", 5664, 20);
        pss.setNome("Virgulino");
        pss.setIdade(50);
        pss.setCpf(5446);

        Socket s = null;
        try {
            int serverPort = 7895;

            s = new Socket("", serverPort); // se for executado em IDE deixa o valor fixo e vai

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeUTF(pss.getNome() + ";" + pss.getCpf() + ";" + pss.getIdade());

            Object data = in.readUTF(); // read a line of data from the stream
            System.out.println("Received: " + data);
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
