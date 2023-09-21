package com.todocode.trabajo_final.service;

import com.todocode.trabajo_final.entity.Cliente;
import java.util.List;

public interface IClienteService {

    //Create Cliente
    public void create(Cliente cliente) throws Exception;

    //Get List Clientes
    public List<Cliente> findAll() throws Exception;

    //Find Cliente
    public Cliente findOne(Long id) throws Exception;

    //Delete Cliente
    public void delete(Long id) throws Exception;

    //Edit Cliente
    public void editPut(Long id, Cliente cliente) throws Exception;
    
    //Edit Cliente Patch
    public void editPatch(Long id, String nombre, String apellido, String dni) throws Exception;

}
