package streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import entidades.Pessoa;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class PessoasInputStream extends InputStream {

    private InputStream is;
    private Pessoa[] pessoas;
    public String data;
    private String path;

    public PessoasInputStream(Pessoa[] p, InputStream is, String path) {
        this.pessoas = p;
        this.is = is;
        this.path = path;
    }

    public Pessoa[] readSystem() {

        Scanner sc = new Scanner(is);

        System.out.println("Informa o nome da pessoa:");
        String nome = sc.nextLine();
        System.out.println("Informe o cpf da pessoa:");
        double cpf = sc.nextDouble();
        System.out.println("Informe a idade do pessoa:");
        int idade = sc.nextInt();

        pessoas[0] = new Pessoa(nome, cpf, idade);

        sc.close();

        return pessoas;
    }

    /**
     *
     * @return
     */
    public Pessoa[] readFile() {
        File fi;
        try {
            fi = new File(this.path);

            if (fi.exists()) {
                ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(fi));

                System.out.println(Arrays.toString(pessoas = (Pessoa[]) objIn.readObject()));
            }
            /*FileInputStream fin = new FileInputStream(this.path);
            int i = 0;
            String val = "";
            String vall[] = new String[3];
            String valll[] = new String[2];
            while ((i = fin.read()) != -1) {
                val = val + (char) i;
            }
            fin.close();
            val = val.replace(" ", "");
            vall = val.split("\n", 3);
            for (i = 0; i < 3; i++) {
                valll = vall[i].split(":", 2);
                if (valll[0].equals("Nome")) {
                    pessoas[0].setNome(valll[1]);
                } else if (valll[0].equals("Idade")) {
                    valll[1] = valll[1].replace(",", ".");
                    valll[1] = valll[1].replace(" ", "");
                    // System.out.println("Idade: " + valll[1]);
                    pessoas[0].setIdade(Integer.parseInt(valll[1]));
                } else if (valll[0].equals("CPF")) {
                    pessoas[0].setCpf(Double.parseDouble(valll[1]));
                } else {
                    System.out.println("Erro ao identificar:" + valll[0] + " " + valll[1]);
                }
            }*/
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return pessoas;
    }

    public Pessoa[] readTCP() {

        int serverPort = 7896; // the server port
        try {
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Esperando mensagem");
            Socket clientSocket = listenSocket.accept();
            Connection c = new Connection(clientSocket);
            //c.run(); //nao sei porque, mas quando tem essas duas linhas da erro
            //listenSocket.close();
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
        return pessoas;
    }

    @Override
    public int read() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }
}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    String data;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try { // an echo server

            this.data = in.readUTF(); // read a line of data from the stream
            out.writeUTF(data.toUpperCase());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar socket: " + e.getMessage());
            }
        }

    }
}
