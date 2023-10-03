package serialization;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import entidades.Pessoa;

public class JAXBExample {
	public static void main(String[] args) {
		// Java object. We will convert it to XML.
		Pessoa pessoa = new Pessoa("Antonio", 123456789, 15);

		// Method which uses JAXB to convert object to XML
		jaxbObjectToXML(pessoa);

		//String fileName = "pessoa.xml";

		// Call method which read the XML file above
		//jaxbXmlFileToObject(fileName);
	}

	private static void jaxbObjectToXML(Pessoa pessoa) {
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(Pessoa.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Print XML String to Console
			StringWriter sw = new StringWriter();

			// Write XML to StringWriter
			jaxbMarshaller.marshal(pessoa, sw);

			// Verify XML Content
			String xmlContent = sw.toString();
			
			// Print XML Content
			System.out.println(xmlContent);

			// Store XML to File
			//File file = new File("employee.xml");

			// Writes XML file to file-system
			//jaxbMarshaller.marshal(pessoa, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static void jaxbXmlFileToObject(String fileName) {

		File xmlFile = new File(fileName);

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Pessoa.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Pessoa pessoa = (Pessoa) jaxbUnmarshaller.unmarshal(xmlFile);

			System.out.println(pessoa);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}