package com.santiago.pizzeriaProyecto.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pizza implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotNull(message = "La foto no puede ser nula")
    private String foto;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "el precio no puede ser negativo")
    @Max(value = 50, message = "el precio no puede ser mayor que 50€")
    private double precio;
    
    @NotEmpty(message = "La lista de ingredientes no puede estar vacia")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "pizzas")
    private List<Ingrediente> ingredientes;

    //No requerido
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "pizza")
    private List<Comentario> comentarios;
    
}
