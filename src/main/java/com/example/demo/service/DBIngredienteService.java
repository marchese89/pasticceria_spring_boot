package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dolce;
import com.example.demo.model.Ingrediente;
import com.example.demo.repository.IDolceRepository;
import com.example.demo.repository.IngredienteRepository;

@Service("mainIngredienteService")
public class DBIngredienteService implements IIngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private IDolceRepository dolceRepository;
	
	@Override
	public Iterable<Ingrediente> getAll() {
		return ingredienteRepository.findAll();
	}

	@Override
	public Optional<Ingrediente> getById(int id) {
		return ingredienteRepository.findById(id);
	}

	@Override
	public Ingrediente create(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ingrediente> update(int id, Ingrediente ingrediente) {
		
		Optional<Ingrediente> foundIngrediente = ingredienteRepository.findById(id);

		if (foundIngrediente.isEmpty()) {
			return Optional.empty();
		}

		foundIngrediente.get().setNome(ingrediente.getNome());
		foundIngrediente.get().setQta(ingrediente.getQta());
		foundIngrediente.get().setuMisura(ingrediente.getuMisura());
		
		ingredienteRepository.save(foundIngrediente.get());

		return foundIngrediente;
	}

	@Override
	public Boolean delete(int id) {
		
		Optional<Ingrediente> foundIngrediente = ingredienteRepository.findById(id);		

		if (foundIngrediente.isEmpty()) {
			return false;
		}

		ingredienteRepository.delete(foundIngrediente.get());

		return true;
	}

}
