package secao_4_3.serializationTexts.serializationXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import secao_4_2.entidades.Pessoa;

public class XStreamXML {

   public static void main(String args[]) {
         // criando um Aluno
         Pessoa pessoa = new Pessoa("Antonio Rafael Braga", 70, 180);
         // exibindo o resultado da serializa��o com XStream
         XStream xstream= new XStream(new JettisonMappedXmlDriver());
         String representacao = xstream.toXML(pessoa);
         System.out.println(representacao);
         
         XStream xstream2 = new XStream();
         Pessoa pessoa2 = (Pessoa) xstream2.fromXML(representacao);
         System.out.println(pessoa2.getNome());
     }
}