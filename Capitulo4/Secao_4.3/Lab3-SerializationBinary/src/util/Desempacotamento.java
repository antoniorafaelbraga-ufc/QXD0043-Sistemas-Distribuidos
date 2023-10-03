package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Desempacotamento {
	// desserialização: recuperando os objetos gravados no arquivo binário "nomeArq"
	public static ArrayList<Object> lerArquivoBinario(String nomeArq) {
		ArrayList<Object> lista = new ArrayList();
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (ArrayList<Object>) objInput.readObject();
				objInput.close();
			}
		} catch (IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch (ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}

		return (lista);
	}
}
