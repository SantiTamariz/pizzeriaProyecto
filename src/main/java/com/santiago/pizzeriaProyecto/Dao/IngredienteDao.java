package com.santiago.pizzeriaProyecto.Dao;

import com.santiago.pizzeriaProyecto.Entities.Ingrediente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteDao extends JpaRepository<Ingrediente,Long>{
    
}
