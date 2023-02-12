package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Dolce;
import com.example.demo.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Dolce>{

}
