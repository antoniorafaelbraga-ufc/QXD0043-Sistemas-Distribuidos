package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Empacotamento {

	// serialização: gravando o objetos no arquivo binário "nomeArq"
	public static void gravarArquivoBinario(ArrayList<Object> lista, String nomeArq) {
		File arq = new File(nomeArq);
		try {
			arq.delete();
			arq.createNewFile();

			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
			objOutput.writeObject(lista);
			objOutput.close();
					
		} catch (IOException erro) {
			System.out.printf("Erro: %s", erro.getMessage());
		} finally {
			System.gc();
			Runtime.getRuntime().gc();
		}
	}
}