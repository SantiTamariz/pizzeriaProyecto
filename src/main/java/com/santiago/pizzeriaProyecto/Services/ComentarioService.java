package com.santiago.pizzeriaProyecto.Services;

import java.util.List;

import com.santiago.pizzeriaProyecto.Entities.Comentario;


public interface ComentarioService {
    public List<Comentario> findAll();
    public Comentario findById(Long id);
    public void guardarComentario(Comentario comentario);
}
