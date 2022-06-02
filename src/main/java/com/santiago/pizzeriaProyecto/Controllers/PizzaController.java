package com.santiago.pizzeriaProyecto.Controllers;

import java.util.List;

import com.santiago.pizzeriaProyecto.Entities.Pizza;
import com.santiago.pizzeriaProyecto.Services.PizzaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    
    @GetMapping
    private ResponseEntity<List<Pizza>> listarPizzas(Model model){

        ResponseEntity<List<Pizza>> responseEntity = null;

        List<Pizza> pizzas = null;

        pizzas = pizzaService.findAll();

        if (pizzas.size() > 0) {
            // si devuelve la lista == status OK
            responseEntity = new ResponseEntity<List<Pizza>>(pizzas, HttpStatus.OK);
        } else {
            // si el listado esta vacio == otro status
            responseEntity = new ResponseEntity<List<Pizza>>(HttpStatus.NO_CONTENT); //204
        }

        return responseEntity;
        
    }
}
