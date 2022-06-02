package com.santiago.pizzeriaProyecto.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(max = 50, min = 4, message = "Nombre entre 3 y 50")
    private String nombre;

    @NotEmpty(message = "Los apellidos no pueden estar vacios")
    private String apellidos;

    @Column(unique = true)
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;

    //Encriptar contraseña con Spring Security
    @NotEmpty(message = "La contraseña no puede estar vacia")
    private String contraseña;

}
