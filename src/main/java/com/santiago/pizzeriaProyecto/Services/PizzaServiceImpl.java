package com.santiago.pizzeriaProyecto.Services;

import java.util.List;

import com.santiago.pizzeriaProyecto.Dao.PizzaDao;
import com.santiago.pizzeriaProyecto.Entities.Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService{

    @Autowired
    private PizzaDao pizzaDao;

    @Override
    public List<Pizza> findAll() {
        return pizzaDao.findAll();
    }

    @Override
    public Pizza findById(Long id) {
        return pizzaDao.findById(id).get();
    }

    @Override
    public void guardarPizza(Pizza pizza) {
        pizzaDao.save(pizza);
    }
    
}
