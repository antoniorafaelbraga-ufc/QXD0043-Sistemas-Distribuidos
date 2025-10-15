package com.exemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import com.entidades.Pessoa;

public class JAXBModernExample {
    
    public static void main(String[] args) {
        System.out.println("=== Iniciando Exemplo JAXB ===\n");
        
        // Criando objeto Pessoa com peso e altura
        Pessoa pessoa = new Pessoa("Antonio", 70.5, 1.75);
        
        System.out.println("Pessoa criada: " + pessoa);
        System.out.println();
        
        // Convertendo para XML
        System.out.println("=== XML Gerado ===");
        String xml = objectToXml(pessoa);
        System.out.println(xml);
        
        // Salvando em arquivo
        Path xmlPath = Paths.get("pessoa.xml");
        saveXmlToFile(pessoa, xmlPath);
        System.out.println("✓ Arquivo salvo: " + xmlPath.toAbsolutePath() + "\n");
        
        // Lendo do arquivo
        System.out.println("=== Lendo do Arquivo ===");
        Pessoa pessoaLida = xmlFileToObject(xmlPath);
        if (pessoaLida != null) {
            System.out.println("Objeto recuperado: " + pessoaLida);
        }
    }
    
    private static String objectToXml(Pessoa pessoa) {
        try {
            JAXBContext context = JAXBContext.newInstance(Pessoa.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            
            var sw = new java.io.StringWriter();
            marshaller.marshal(pessoa, sw);
            return sw.toString();
            
        } catch (JAXBException e) {
            System.err.println("Erro ao converter para XML:");
            e.printStackTrace();
            throw new RuntimeException("Erro ao converter para XML", e);
        }
    }
    
    private static void saveXmlToFile(Pessoa pessoa, Path path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Pessoa.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            
            try (var outputStream = Files.newOutputStream(path)) {
                marshaller.marshal(pessoa, outputStream);
            }
            
        } catch (JAXBException | IOException e) {
            System.err.println("Erro ao salvar arquivo:");
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }
    }
    
    private static Pessoa xmlFileToObject(Path path) {
        try {
            if (!Files.exists(path)) {
                System.err.println("Arquivo não encontrado: " + path);
                return null;
            }
            
            JAXBContext context = JAXBContext.newInstance(Pessoa.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            try (var inputStream = Files.newInputStream(path)) {
                return (Pessoa) unmarshaller.unmarshal(inputStream);
            }
            
        } catch (JAXBException | IOException e) {
            System.err.println("Erro ao ler arquivo:");
            e.printStackTrace();
            throw new RuntimeException("Erro ao ler arquivo", e);
        }
    }
}