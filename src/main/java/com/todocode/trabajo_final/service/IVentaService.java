package com.todocode.trabajo_final.service;

import com.todocode.trabajo_final.entity.Producto;
import com.todocode.trabajo_final.entity.Venta;
import java.util.List;

public interface IVentaService {

    //Create Venta Cliente + Productos
    public void create(Long idCliente, List<Producto> productos) throws Exception;

    //Create Venta Object
    public void create(Venta venta) throws Exception;

    //Get List Venta
    public List<Venta> findAll() throws Exception;

    //Find Venta
    public Venta findOne(Long id) throws Exception;

    //Delete Venta
    public void delete(Long id) throws Exception;

    //Edit Venta
    public void editPut(Long id, Venta venta) throws Exception;

    //Edit Venta Patch
    public void editPatch(Long id, String nombre, String apellido, String dni) throws Exception;
}
