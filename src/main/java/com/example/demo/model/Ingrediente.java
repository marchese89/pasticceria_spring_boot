package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingrediente {

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "dolce_id", referencedColumnName = "id")
	Dolce idDolce;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private int qta;

	private String uMisura;


	public Dolce getIdDolce() {
		return idDolce;
	}

	public void setIdDolce(Dolce idDolce) {
		this.idDolce = idDolce;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	public String getuMisura() {
		return uMisura;
	}

	public void setuMisura(String uMisura) {
		this.uMisura = uMisura;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
