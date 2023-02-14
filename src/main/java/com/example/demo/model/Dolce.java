package com.example.demo.model;

import java.util.Calendar;
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
		
		int anno = Integer.parseInt(getData().toString().substring(0, 4));
		int mese = Integer.parseInt(getData().toString().substring(5, 7));
		mese--;
		int giorno = Integer.parseInt(getData().toString().substring(8, 10));
		GregorianCalendar calendar = new GregorianCalendar();
		GregorianCalendar calendar2 = new GregorianCalendar(anno,mese,giorno);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		calendar2.set(Calendar.MILLISECOND, 0);
		
		long data = calendar2.getTimeInMillis();
		long dataCorrente = calendar.getTimeInMillis();
		
		long timeDiff = dataCorrente - data;
		int daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));
		if(daysDiff == 0) {
			return prezzo;
		}
		
		if(daysDiff == 1) {
			return prezzo*0.8;
		}
		
		if(daysDiff == 2) {
			return prezzo*0.2;
		}
		
		return 0.0;
		
		
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
	
	public void modificaIngrediente(int id, String nome, int qta, String uMisura) {
		for(Ingrediente i : lista) {
			if(i.getId() == id) {
				i.setNome(nome);
				i.setQta(qta);
				i.setuMisura(uMisura);
			}
		}
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
