package com.todocode.trabajo_final.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate fecha_venta;
    private Double total;

    @ManyToMany(targetEntity = Producto.class)
    private List<Producto> productos;

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    public Venta() {
        this.fecha_venta = LocalDate.now();
    }

    public Venta(Cliente cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
        this.fecha_venta = LocalDate.now(); //fecha actual
        this.total = totalCompra(productos); //funcion suma costos
    }

    //Funcion suma el costo de todos los Productos
    private Double totalCompra(List<Producto> productos) {
        Double suma = 0D;
        for (Producto producto : productos) {
            suma += producto.getCosto();
        }
        return suma;
    }

}
