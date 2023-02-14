package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Ingrediente;

public interface IIngredienteService {
	
	public Iterable<Ingrediente> getAll();

	public Optional<Ingrediente> getById(int id);

	public Ingrediente create(Ingrediente ingrediente);

	public Optional<Ingrediente> update(int id, String nome_ingr, int qta, String uMisura);

	public Boolean delete(int id);


}
