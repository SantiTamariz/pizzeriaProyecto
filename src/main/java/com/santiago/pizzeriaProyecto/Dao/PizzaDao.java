package com.santiago.pizzeriaProyecto.Dao;

import com.santiago.pizzeriaProyecto.Entities.Pizza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaDao extends JpaRepository<Pizza, Long>{
    
}
