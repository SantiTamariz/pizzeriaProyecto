package com.santiago.pizzeriaProyecto.Services;

import java.util.List;

import com.santiago.pizzeriaProyecto.Dao.IngredienteDao;
import com.santiago.pizzeriaProyecto.Entities.Ingrediente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServiceImpl implements IngredienteService{

    @Autowired
    IngredienteDao ingredienteDao;

    @Override
    public List<Ingrediente> findAll() {
        return ingredienteDao.findAll();
    }

    @Override
    public Ingrediente findById(Long id) {
        return ingredienteDao.findById(id).get();
    }

    @Override
    public void guardarIngrediente(Ingrediente ingrediente) {
        ingredienteDao.save(ingrediente);
        
    }
    
}
