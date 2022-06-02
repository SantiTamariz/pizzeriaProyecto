package com.santiago.pizzeriaProyecto.Services;

import java.util.List;

import com.santiago.pizzeriaProyecto.Entities.Usuario;

public interface UsuarioService {
    public List<Usuario> findAll();
    public Usuario findById(Long id);
    public void guardarUsuario(Usuario usuario);
}
