package secao_4_3.serializationTexts.serializationXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import secao_4_2.entidades.Pessoa;

public class XStreamXMLExample {

	public static void main(String[] args) {

        Pessoa pessoa = new Pessoa("Antonio", 123456789, 15);
        String xml  = new XStreamXMLExample().object2JSON(pessoa);
        System.out.println(xml);
        //JSON2Object(xml);
	}

	private String object2JSON(Pessoa pessoa) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
        //XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("pessoa", Pessoa.class);
		String xml = xstream.toXML(pessoa);
        System.out.println(xml);
		return xml;
	}
	
	private static void JSON2Object(String xml) {
		//String json = "{\"pessoa\":{\"nome\":\"Rafael\",\"cpf\":123456789,\"idade\":23}}";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("Pessoa", secao_4_2.entidades.Pessoa.class);
		xstream.addPermission(AnyTypePermission.ANY);
		Pessoa pessoa = (Pessoa) xstream.fromXML(xml);
		System.out.println(pessoa.getNome());		
	}
}