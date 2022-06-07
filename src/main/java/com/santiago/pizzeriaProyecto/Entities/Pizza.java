package com.santiago.pizzeriaProyecto.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

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

    private String foto;

    private double precio;
    
    @NotEmpty(message = "La lista de ingredientes no puede estar vacia")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                name = "ingredientes_pizza",
                joinColumns = @JoinColumn(name="pizzas_id"),
                inverseJoinColumns = @JoinColumn(name="ingredientes_id")
    )
    private List<Ingrediente> ingredientes;

    //No requerido
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "pizza")
    private List<Comentario> comentarios;
    
}
