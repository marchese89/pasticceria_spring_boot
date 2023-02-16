package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dolce;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.IstanzaDolce;
import com.example.demo.repository.IDolceRepository;
import com.example.demo.repository.IIstanzaDolceRepository;
import com.example.demo.repository.IngredienteRepository;

@Service("mainDolceService")
public class DBDolceService implements IDolceService {

	@Autowired
	private IDolceRepository dolceRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private IIstanzaDolceRepository istanzaDolceRepository;

	@Override
	public Iterable<Dolce> getAll() {
		return dolceRepository.findAll();
		
	}

	@Override
	public Optional<Dolce> getById(int id) {
		return dolceRepository.findById(id);
	}

	@Override
	public Dolce create(Dolce dolce) {
		return dolceRepository.save(dolce);
	}

	@Override
	public Optional<Dolce> update(int id, Dolce dolce) {
		System.out.println("dolce_nome: "+dolce.getNome()+", dolce_prezzo: "+dolce.getPrezzo());
		Optional<Dolce> foundDolce = dolceRepository.findById(id);

		if (foundDolce.isEmpty()) {
			return Optional.empty();
		}

		foundDolce.get().setNome(dolce.getNome());
		foundDolce.get().setPrezzo(dolce.getPrezzo());
		
		dolceRepository.save(foundDolce.get());

		return foundDolce;
	}

	@Override
	public Boolean delete(int id) {
		Optional<Dolce> foundDolce = dolceRepository.findById(id);

		if (foundDolce.isEmpty()) {
			return false;
		}

		dolceRepository.delete(foundDolce.get());

		return true;
	}

	@Override
	public Boolean addIngrediente(int id, String nome,int qta, String uMisura) {
		Optional<Dolce> foundDolce = dolceRepository.findById(id);
		foundDolce.get().aggiungiIngrediente(nome, qta, uMisura);
		dolceRepository.save(foundDolce.get());
		return true;
	}
	
	@Override
	public Boolean modIngrediente(int id, String nome,int qta, String uMisura) {
		Optional<Ingrediente> foundIngrediente = ingredienteRepository.findById(id);
		Optional<Dolce> foundDolce = dolceRepository.findById(foundIngrediente.get().getIdDolce().getId());
		foundDolce.get().modificaIngrediente(id,nome, qta, uMisura);
		dolceRepository.save(foundDolce.get());
		return true;
	}
	
	
	@Override
	public Boolean removeIngrediente(int id) {
		Optional<Ingrediente> foundIngrediente = ingredienteRepository.findById(id);
		Optional<Dolce> foundDolce = dolceRepository.findById(foundIngrediente.get().getIdDolce().getId());
		foundDolce.get().rimuoviIngrediente(id);
		dolceRepository.save(foundDolce.get());
		ingredienteRepository.delete(foundIngrediente.get());
		return true;
	}

	@Override
	public Boolean addIstanze(int id, int qta) {
		Optional<Dolce> foundDolce = dolceRepository.findById(id);
		foundDolce.get().aggiungiIstanza(qta);
		dolceRepository.save(foundDolce.get());
		return true;
	}
	
	@Override
	public Boolean removeIstanza(int id) {
		Optional<IstanzaDolce> foundIstanzaDolce = istanzaDolceRepository.findById(id);
		Optional<Dolce> foundDolce = dolceRepository.findById(foundIstanzaDolce.get().getIdDolce().getId());
		foundDolce.get().rimuoviIstanza(id);
		dolceRepository.save(foundDolce.get());
		istanzaDolceRepository.delete(foundIstanzaDolce.get());
		return true;
	}

}
