package com.santiago.pizzeriaProyecto.Services;

import java.util.List;

import com.santiago.pizzeriaProyecto.Entities.Pizza;

public interface PizzaService {
    public List<Pizza> findAll();
    public Pizza findById(Long id);
    public Pizza guardarPizza(Pizza pizza);
    public void deletePizza(Long id);
}
