package com.example.demo.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Dolce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	
	private Double prezzo;
	
	
	public Dolce() {
		this.setData((new GregorianCalendar()).getTime());
	}
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private Date data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ingrediente> lista;
	
	public void aggiungiIngrediente(String nome,int qta, String uMisura) {
		Ingrediente i = new Ingrediente();
		i.setIdDolce(this);
		i.setNome(nome);
		i.setQta(qta);
		i.setuMisura(uMisura);
		lista.add(i);
	}
	
	public void rimuoviIngrediente(int id) {
		int index = -1;
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getId() == id) {
				index = i;
				break;
			}
		}
		lista.remove(index);
	}

	public List<Ingrediente> getLista() {
		return lista;
	}

	public void setLista(List<Ingrediente> lista) {
		this.lista = lista;
	}
	
}
