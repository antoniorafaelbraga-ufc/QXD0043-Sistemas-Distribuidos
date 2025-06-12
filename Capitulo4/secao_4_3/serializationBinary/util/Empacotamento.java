package secao_4_3.serializationBinary.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Empacotamento {

	// serializa��o: gravando o objetos no arquivo bin�rio "nomeArq"
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