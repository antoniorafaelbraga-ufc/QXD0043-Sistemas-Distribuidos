package serialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import entidades.Pessoa;

public class XStreamJSONExample {

	public static void main(String[] args) {

        Pessoa pessoa = new Pessoa("Antonio", 123456789, 15);
        object2JSON(pessoa);
        System.out.println();
        //JSON2Object();
	}

	private static void object2JSON(Pessoa pessoa) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
        //XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("pessoa", Pessoa.class);
        System.out.println(xstream.toXML(pessoa));
	}
	
	private static void JSON2Object() {
		String json = "{\"pessoa\":{\"nome\":\"Banana\",\"cpf\":123456789,\"idade\":23}}";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("Pessoa", entidades.Pessoa.class);
		xstream.addPermission(AnyTypePermission.ANY);
		Pessoa pessoa = (Pessoa) xstream.fromXML(json);
		System.out.println(pessoa.getNome());		
	}
}