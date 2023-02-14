package com.example.demo.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Dolce;
import com.example.demo.model.Ingrediente;
import com.example.demo.service.DBDolceService;
import com.example.demo.service.DBIngredienteService;

@RestController
public class AdminDolceController {
	
	@Autowired
	@Qualifier("mainDolceService")
	private DBDolceService dolceService;
	@Autowired
	@Qualifier("mainIngredienteService")
	private DBIngredienteService ingredienteService;
	
	public AdminDolceController() {

	}

	@RequestMapping("/admin/api/dolci")
	public Iterable<Dolce> getAll() {
		return dolceService.getAll();
	}

	@RequestMapping("/admin/api/dolci/{id}")
	public Dolce getById(@PathVariable int id) {

		Optional<Dolce> dolce = dolceService.getById(id);

		if (dolce.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}

		return dolce.get();
	}

	@RequestMapping(value = "/admin/api/dolci", method = RequestMethod.POST)
	public Dolce create(@RequestBody Dolce dolce) {

		return dolceService.create(dolce);
	}

	@RequestMapping(value = "/admin/api/dolci/{id}", method = RequestMethod.PUT)
	public Dolce update(@PathVariable int id, @RequestBody Dolce dolce) {
		
		Optional<Dolce> updatedDolce = dolceService.update(id, dolce);

		if (updatedDolce.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}
		
		
		return updatedDolce.get();
	}
	
	@RequestMapping(value = "/admin/api/dolci/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		
		Boolean isDeleted = dolceService.delete(id);

		if (isDeleted == false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}
		
		
	}
	
	@RequestMapping(value = "/admin/api/dolci/ingrediente/{id}", method = RequestMethod.DELETE)
	public void deleteIngrediente(@PathVariable int id) {
		
		Boolean isDeleted = dolceService.removeIngrediente(id);
		
		if (isDeleted == false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}
		
		
	}
	
	@RequestMapping(value = "/admin/api/dolci/ingrediente/{id}", method = RequestMethod.GET)
	public Ingrediente getIngrediente(@PathVariable int id) {
		
		Optional<Ingrediente> ingrediente = ingredienteService.getById(id);
		
		if (ingrediente.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}

		return ingrediente.get();
		
	}
	
	@RequestMapping(value ="/admin/api/dolci/{id}/{nome_ingr}/{qta}/{uMisura}", method = RequestMethod.PUT)
	public Dolce addIngrediente(@PathVariable int id, @PathVariable int qta,  @PathVariable String nome_ingr, @PathVariable String uMisura, @RequestBody Dolce dolce) {
		
		dolceService.addIngrediente(id,nome_ingr, qta, uMisura);
		
		Optional<Dolce> foundDolce = dolceService.getById(id);
		
		if (foundDolce.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}
		
		
		return foundDolce.get();
		
	}
	
	@RequestMapping(value ="/admin/api/dolci/{id}/{nome_ingr}/{qta}/{uMisura}", method = RequestMethod.POST)
	public Ingrediente modIngrediente(@PathVariable int id, @PathVariable int qta,  @PathVariable String nome_ingr, @PathVariable String uMisura, @RequestBody Dolce dolce) {
		
		dolceService.modIngrediente(id, nome_ingr, qta, uMisura);
		
		Optional<Ingrediente> foundIngrediente = ingredienteService.getById(id);
		
		return foundIngrediente.get();
		
	}


}
