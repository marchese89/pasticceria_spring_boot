package com.example.demo.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Dolce;
import com.example.demo.service.IDolceService;
@RestController
public class DolceController {
	
	@Autowired
	@Qualifier("mainDolceService")
	private IDolceService dolceService;
	
	public DolceController() {

	}

	@RequestMapping("/api/dolci")
	public Iterable<Dolce> getAll(){
		return dolceService.getAll();
	}
	
	@RequestMapping("/api/dolci/{id}")
	public Dolce getById(@PathVariable int id) {
		
		Optional<Dolce> dolce = dolceService.getById(id);
		
		if(dolce.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
		}
		
		return dolce.get();
	}

}
