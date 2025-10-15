package com.xstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.entidades.Pessoa;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class XStreamJSON {
    
    public static void main(String[] args) {
        System.out.println("=== XStream JSON Exemplo ===\n");
        
        // Criando objeto Pessoa
        Pessoa pessoa = new Pessoa("Antonio", 70.5, 1.75);
        
        System.out.println("Objeto criado: " + pessoa);
        System.out.println();
        
        // Convertendo objeto para JSON
        System.out.println("=== Convertendo para JSON ===");
        String json = objectToJson(pessoa);
        System.out.println(json);
        
        // Salvando JSON em arquivo
        Path jsonPath = Paths.get("pessoa.json");
        saveJsonToFile(json, jsonPath);
        System.out.println("\n✓ Arquivo salvo: " + jsonPath.toAbsolutePath());
        
        // Convertendo JSON de volta para objeto
        System.out.println("\n=== Convertendo de volta para Objeto ===");
        Pessoa pessoaLida = jsonToObject(json);
        if (pessoaLida != null) {
            System.out.println("Objeto recuperado: " + pessoaLida);
        }
        
        // Lendo JSON do arquivo
        System.out.println("\n=== Lendo JSON do Arquivo ===");
        Pessoa pessoaDoArquivo = jsonFileToObject(jsonPath);
        if (pessoaDoArquivo != null) {
            System.out.println("Objeto do arquivo: " + pessoaDoArquivo);
        }
    }
    
    /**
     * Converte objeto Java para String JSON
     */
    private static String objectToJson(Pessoa pessoa) {
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.alias("pessoa", Pessoa.class);
            
            return xstream.toXML(pessoa);
            
        } catch (Exception e) {
            System.err.println("Erro ao converter para JSON:");
            e.printStackTrace();
            throw new RuntimeException("Erro ao converter para JSON", e);
        }
    }
    
    /**
     * Converte String JSON para objeto Java
     */
    private static Pessoa jsonToObject(String json) {
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            xstream.alias("pessoa", Pessoa.class);
            xstream.addPermission(AnyTypePermission.ANY);
            
            return (Pessoa) xstream.fromXML(json);
            
        } catch (Exception e) {
            System.err.println("Erro ao converter JSON para objeto:");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Salva JSON em arquivo
     */
    private static void saveJsonToFile(String json, Path path) {
        try {
            Files.writeString(path, json);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo:");
            e.printStackTrace();
        }
    }
    
    /**
     * Lê JSON de arquivo e converte para objeto
     */
    private static Pessoa jsonFileToObject(Path path) {
        try {
            if (!Files.exists(path)) {
                System.err.println("Arquivo não encontrado: " + path);
                return null;
            }
            
            String json = Files.readString(path);
            return jsonToObject(json);
            
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo:");
            e.printStackTrace();
            return null;
        }
    }
}