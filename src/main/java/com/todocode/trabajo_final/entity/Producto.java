package com.todocode.trabajo_final.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String marca;
    private Double costo;
    private Integer cant_disponible;

    public Producto() {
    }

    public Producto(Long id, String nombre, String marca, Double costo, Integer cant_disponible) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cant_disponible = cant_disponible;
    }

}
