package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IstanzaDolce {
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "dolce_id", referencedColumnName = "id")
	Dolce idDolce;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "data")
	private Date data;

	public Dolce getIdDolce() {
		return idDolce;
	}

	public void setIdDolce(Dolce idDolce) {
		this.idDolce = idDolce;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	

}
