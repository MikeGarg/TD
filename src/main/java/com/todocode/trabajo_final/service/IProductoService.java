package com.todocode.trabajo_final.service;

import com.todocode.trabajo_final.entity.Producto;
import java.util.List;

public interface IProductoService {

    //Create Cliente
    public void create(Producto producto) throws Exception;

    //Get List Clientes
    public List<Producto> findAll() throws Exception;

    //Find Cliente
    public Producto findOne(Long id) throws Exception;

    //Delete Cliente
    public void delete(Long id) throws Exception;

    //Edit Cliente Put
    public void editPut(Long id, Producto producto) throws Exception;

    //Edit Cliente Patch
    public void editPatch(Long id, String nombre, String marca, Double costo, Integer cant_disponible) throws Exception;

}
