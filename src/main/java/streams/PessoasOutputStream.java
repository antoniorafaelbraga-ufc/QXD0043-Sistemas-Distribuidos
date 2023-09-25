package streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import entidades.Pessoa;
import java.io.File;
import java.io.ObjectOutputStream;

public class PessoasOutputStream extends OutputStream {

    private OutputStream op;
    private Pessoa[] pessoas;
    private String path;

    public PessoasOutputStream() {
    }

    public PessoasOutputStream(Pessoa[] p, OutputStream os, String path) {
        this.pessoas = p;
        this.op = os;
        this.path = path;
    }

    public void writeSystem() {

        PrintStream opLocal = new PrintStream(op);

        // envia quantidade de pessoas;
        int qtdpessoa = pessoas.length;
        opLocal.println("Quantidade de pessoas: " + qtdpessoa);

        // envia os dados de um conjunto (array) de Pessoas
        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                int tamanhoNomePessoa = pessoa.getNome().getBytes().length;
                String nome = pessoa.getNome();
                double cpf = pessoa.getCpf();
                int idade = pessoa.getIdade();

                opLocal.println(" tamanhoNomePessoa: " + tamanhoNomePessoa + "\n" + " nomePessoa: " + nome + "\n"
                        + " cpf: " + cpf + "\n" + " idade: " + idade);
            }
        }
    }

    public void writeFile() {
        // envia os dados de um conjunto (array) de Pessoas
        File fi;
        try {
            fi = new File(this.path);
            
            if (!fi.exists()) fi.createNewFile();
            
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(fi));
            
            objOut.writeObject(pessoas);
            objOut.close();
            
//            FileOutputStream fout = new FileOutputStream(this.path);
//            String s = "Nome: " + pessoas[0].getNome() + "\nCPF: " + pessoas[0].getCpf() + "\nIdade: "
//                    + pessoas[0].getIdade();
//            byte b[] = s.getBytes();// converting string into byte array
//            fout.write(b);
//            fout.close();
            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeTCP() {
        // envia os dados de um conjunto (array) de Pessoas
        Socket s = null;
        try {
            System.out.println("streams.PessoasOutputStream.writeTCP()");
            int serverPort = 7895;
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF("Nome: " + pessoas[0].getNome() + "\nIdade: " + pessoas[0].getIdade() + "\nCPF: " + pessoas[0].getCpf()); // UTF is a string encoding see Sn. 4.4
            String data = in.readUTF(); // read a line of data from the stream
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

    @Override
    public void write(int b) throws IOException {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return "Ola, mundo! Estamos sobrescrevendo o método toString()!\n" + " PessoasOutputStream [ \n"
                + " getClass()=" + getClass() + ",\n" + " hashCode()=" + hashCode() + ",\n" + " toString()="
                + super.toString() + "]";
    }
}
