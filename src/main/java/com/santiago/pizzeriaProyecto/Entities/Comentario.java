package com.santiago.pizzeriaProyecto.Entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El comentario no puede estar vacio")
    @Size(max = 256, min = 20, message = "Nombre entre 20 y 256")
    private String texto;

    @NotNull(message = "La puntuacion no puede ser nula")
    private int puntuación;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "El email no puede ser vacio")
    private String emailUsuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Pizza pizza;

    //Hola prueba
    
}
