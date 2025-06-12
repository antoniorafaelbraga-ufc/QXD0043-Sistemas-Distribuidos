package secao_4_3.serializationTexts.serializationXML;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import secao_4_2.entidades.Pessoa;

public class XStreamXMLExample_old {
	public static void main(String[] args) throws IOException {
		
		Pessoa pessoa = new Pessoa("Antonio", 123456789, 15);
		Object2XML(pessoa);
		System.out.println();
		XML2Object();
	}

	private static void XML2Object() throws IOException {
		
		XStream instream = new XStream();

		BufferedReader br = new BufferedReader(new FileReader("pessoa.xml"));
		StringBuffer buff = new StringBuffer();
		String line;
		while((line = br.readLine()) != null){
		   buff.append(line);
		}
		
		instream.alias("Pessoa", secao_4_2.entidades.Pessoa.class);
		instream.addPermission(AnyTypePermission.ANY);
		
		Pessoa p = (Pessoa)instream.fromXML(buff.toString());
		System.out.println("Nome: "+p.getNome());
		System.out.println("CPF: "+p.getCpf());
		System.out.println("idade: "+p.getIdade());
	}
	
	
	private static void Object2XML(Pessoa pessoa) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("Pessoa", Pessoa.class);
		String xml = xstream.toXML(pessoa);
		System.out.println(xml);
		
		FileWriter gravar = new FileWriter("pessoa.xml");
		try {
		    BufferedWriter s = new BufferedWriter(gravar);
		    s.write(xml);
		    s.flush();
		    s.close();		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    gravar.close();
		}
	}
}
