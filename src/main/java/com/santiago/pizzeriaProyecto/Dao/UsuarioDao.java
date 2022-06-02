package com.santiago.pizzeriaProyecto.Dao;

import com.santiago.pizzeriaProyecto.Entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
}
