package streams;

import java.io.IOException;

import entidades.Pessoa;

public class TestStreams {
	public static void main(String[] args) throws IOException {

		Pessoa[] pessoas = new Pessoa[1];
                //String PATH = "CAMINHO_COMPLETO_ATE_ARQUIVO/NOME_ARQUIVO.TXT";
                String PATH = "serial.txt";

                PessoasInputStream pis = new PessoasInputStream(pessoas, System.in, PATH);
		pessoas = pis.readSystem();
		pis.readTCP();
		pis.readFile();
		
		PessoasOutputStream pos = new PessoasOutputStream(pessoas, System.out, PATH);
		pos.writeSystem();
		pos.writeFile();
		pos.writeTCP();
                
		pis.close();
		pos.close();
	}
}
