package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingrediente {
	
	
	@ManyToOne
	@JoinColumn(name = "dolce_id", referencedColumnName="id")
	Dolce idDolce;
	
	@Id
	private long id;
	
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

	
	
}
