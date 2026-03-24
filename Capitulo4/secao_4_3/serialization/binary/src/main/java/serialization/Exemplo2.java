//https://www.devmedia.com.br/serializacao-de-objetos-em-java/23413
package serialization;

import java.util.ArrayList;

import entidades.Pessoa;
import util.Desempacotamento;

public class Exemplo2 {

	public static void main(String[] args) {
		// desserializa��o: recuperando os objetos gravados no arquivo bin�rio
		// "dados.dat"
		ArrayList<Object> pessoa = Desempacotamento.lerArquivoBinario("dados.dat");

		int i = 1;
		for (Object item : pessoa) {
			System.out.printf("Ficha nro....: %d.\n", i++);
			// ((Pessoa)item) - implementa o mecanismo de downcast, ou seja,
			// o objeto "item" declarado a partir da classe
			// base "Object" � referenciado como um objeto "Pessoa"
			System.out.printf("Nome.........: %s\n", ((Pessoa) item).getNome());
			System.out.printf("Peso Corporal: %.2f kgs\n", ((Pessoa) item).getPC());
			System.out.printf("Altura.......: %.2f metros\n", ((Pessoa) item).getAlt());
			System.out.printf("IMC..........: %.2f\n", ((Pessoa) item).IMC());
			System.out.printf("Interpretacao: %s\n\n", ((Pessoa) item).interpretaIMC());
		}
	}
}