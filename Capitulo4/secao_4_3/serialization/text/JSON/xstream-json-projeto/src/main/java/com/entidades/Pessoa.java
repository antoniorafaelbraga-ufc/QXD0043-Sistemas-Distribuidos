package com.entidades;

public class Pessoa {
    private String nome;
    private double pesoCorporal; // em kg
    private double altura;       // em metros

    // Construtor padrão - obrigatório
    public Pessoa() {}

    public Pessoa(String nome, double pesoCorporal, double altura) {
        this.nome = nome;
        this.pesoCorporal = pesoCorporal;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPesoCorporal() {
        return pesoCorporal;
    }

    public void setPesoCorporal(double pesoCorporal) {
        this.pesoCorporal = pesoCorporal;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double calcularIMC() {
        return pesoCorporal / (altura * altura);
    }

    public String interpretarIMC() {
        double imc = calcularIMC();
        if (imc < 18.5)
            return "Baixo peso";
        else if (imc < 25.0)
            return "Peso adequado";
        else if (imc < 30.0)
            return "Sobrepeso";
        else
            return "Obesidade";
    }

    @Override
    public String toString() {
        return String.format(
            "Pessoa[nome='%s', peso=%.2fkg, altura=%.2fm, IMC=%.2f (%s)]",
            nome, pesoCorporal, altura, calcularIMC(), interpretarIMC()
        );
    }
}