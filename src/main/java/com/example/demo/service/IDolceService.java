package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Dolce;

public interface IDolceService {

	public Iterable<Dolce> getAll();

	public Optional<Dolce> getById(int id);

	public Dolce create(Dolce dolce);

	public Optional<Dolce> update(int id, Dolce dolce);

	public Boolean delete(int id);
	
	public Boolean addIngrediente(int id, String nome,int qta, String uMisura);
	
	public Boolean modIngrediente(int id, String nome,int qta, String uMisura);
	
	public Boolean removeIngrediente(int id);
	
	public Boolean addIstanze(int id, int qta);
	
	public Boolean removeIstanza(int id);
}
