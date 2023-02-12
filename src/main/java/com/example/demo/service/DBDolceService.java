package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dolce;
import com.example.demo.repository.IDolceRepository;

@Service("mainDolceService")
public class DBDolceService implements IDolceService {

	@Autowired
	private IDolceRepository dolceRepository;

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

}
