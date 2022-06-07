package com.santiago.pizzeriaProyecto.Services;

import java.util.ArrayList;
import java.util.List;

import com.santiago.pizzeriaProyecto.Dao.IngredienteDao;
import com.santiago.pizzeriaProyecto.Dao.PizzaDao;
import com.santiago.pizzeriaProyecto.Entities.Ingrediente;
import com.santiago.pizzeriaProyecto.Entities.Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService{

    @Autowired
    private PizzaDao pizzaDao;

    @Autowired
    private IngredienteDao ingredienteDao;

    @Override
    public List<Pizza> findAll() {
        return pizzaDao.findAll();
    }

    @Override
    public Pizza findById(Long id) {
        return pizzaDao.findById(id).get();
    }

    @Override
    public Pizza guardarPizza(Pizza pizza) {
        List<Ingrediente> ingredientes = new ArrayList<>();
        double precio=0;

        for (Ingrediente ing : pizza.getIngredientes()){
            long id = ing.getId();
            if(ingredienteDao.findById(id)!=null){
                precio =+ ing.getPrecio();
                ingredientes.add(ing);
            }

        }
        pizza.setPrecio(precio);
        pizza.setIngredientes(ingredientes);
        System.out.println(ingredientes);
        return pizzaDao.save(pizza);
    }

    @Override
    public void deletePizza(Long id) {
        pizzaDao.deleteById(id);
        
    }

    
    
}
