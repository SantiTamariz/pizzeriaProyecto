package com.santiago.pizzeriaProyecto.Dao;

import com.santiago.pizzeriaProyecto.Entities.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioDao extends JpaRepository<Comentario, Long>{
    
}
