package secao_4_3.serializationBinary.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Importacao {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		FileReader arq = new FileReader("export.txt");
		BufferedReader lerArq = new BufferedReader(arq);

		int i = 1, j, n;

		String linha = lerArq.readLine();
		while (linha != null) {
			System.out.printf("Fichar nro: %d.\n", i);

			n = linha.length();
			for (j = 0; j < n; j++) {
				if (linha.charAt(j) == '|')
					System.out.printf(": ");
				else if (linha.charAt(j) == ';')
					System.out.printf("\n");
				else
					System.out.printf("%c", linha.charAt(j));
			}

			System.out.printf("\n\n");

			i++;
			linha = lerArq.readLine();
		}

		lerArq.close();
	}

}