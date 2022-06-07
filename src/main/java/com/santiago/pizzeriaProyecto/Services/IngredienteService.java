package com.santiago.pizzeriaProyecto.Services;

import java.util.List;

import com.santiago.pizzeriaProyecto.Entities.Ingrediente;

public interface IngredienteService {
    public List<Ingrediente> findAll();
    public Ingrediente findById(Long id);
    public Ingrediente guardarIngrediente(Ingrediente ingrediente);
    public void deleteIngrediente(Long id);

}
