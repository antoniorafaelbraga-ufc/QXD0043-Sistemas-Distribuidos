//https://www.devmedia.com.br/serializacao-de-objetos-em-java/23413
package serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entidades.Pessoa;
import util.Desempacotamento;
import util.Empacotamento;

public class Exemplo1 {

	public static void main(String[] args) throws IOException {

		String nome;
		double pc, alt;
		Scanner ler = new Scanner(System.in);

		// 1) desserializa��o: recuperando os objetos gravados no arquivo bin�rio "dados.dat"
		ArrayList<Object> pessoa = Desempacotamento.lerArquivoBinario("dados.dat");

		// 2) entrada de dados
		while (true) {
			System.out.printf("Ficha numero: %d.\n", (pessoa.size() + 1));
			System.out.printf("Informe o nome da pessoa, FIM para encerrar:\n");
			nome = ler.nextLine();
			if (nome.equalsIgnoreCase("FIM"))
				break;

			System.out.printf("\nInforme o peso corporal (em kg)...............: ");
			pc = ler.nextDouble();

			System.out.printf("Informe a altura (em metros: 1,77 por exemplo): ");
			alt = ler.nextDouble();

			pessoa.add(new Pessoa(nome, pc, alt)); // adiciona um novo objeto a lista

			ler.nextLine(); // esvazia o buffer do teclado
			System.out.printf("\n");
		}
		ler.close();

		// 3) serializa��o: gravando o objeto no arquivo bin�rio "dados.dat"
		Empacotamento.gravarArquivoBinario(pessoa, "dados.dat");
	}
}