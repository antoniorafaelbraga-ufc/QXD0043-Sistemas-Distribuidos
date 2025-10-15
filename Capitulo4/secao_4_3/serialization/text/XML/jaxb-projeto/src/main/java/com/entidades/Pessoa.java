package com.entidades;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "pessoa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pessoa implements Serializable {
	
	@XmlElement(required = true)
	private String nome;
	
	@XmlElement(name = "pesoCorporal", required = true)
	private double pc; // peso corporal
	
	@XmlElement(name = "altura", required = true)
	private double alt; // altura em metros

	// Construtor padrão - OBRIGATÓRIO para JAXB
	public Pessoa() {}

	public Pessoa(String nome, double pc, double alt) {
		this.nome = nome;
		this.pc = pc;
		this.alt = alt;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPC() {
		return pc;
	}

	public void setPC(double pc) {
		this.pc = pc;
	}

	public double getAlt() {
		return alt;
	}

	public void setAlt(double alt) {
		this.alt = alt;
	}

	public double IMC() {
		return (getPC() / (getAlt() * getAlt()));
	}

	public String interpretaIMC() {
		double vlrIMC = IMC();
		if (vlrIMC < 18.5)
			return ("baixo peso");
		else if (vlrIMC < 25.0)
			return ("peso adequado");
		else if (vlrIMC < 30.0)
			return ("sobrepeso");
		else
			return ("obesidade");
	}

	@Override
	public String toString() {
		return String.format("Pessoa[nome='%s', peso=%.2fkg, altura=%.2fm, IMC=%.2f (%s)]", 
							 nome, pc, alt, IMC(), interpretaIMC());
	}
}