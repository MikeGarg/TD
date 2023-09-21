package com.todocode.trabajo_final.service;

import com.todocode.trabajo_final.entity.Cliente;
import com.todocode.trabajo_final.repository.IClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public void create(Cliente cliente) throws Exception {

        validateCliente(cliente);

        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAll() throws Exception {

        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public Cliente findOne(Long id) throws Exception {

        Optional<Cliente> response = clienteRepository.findById(id);
        if (response.isPresent()) {
            Cliente cliente = response.get();
            return cliente;
        } else {
            throw new Exception("Cliente no existe");
        }
    }

    @Override
    public void delete(Long id) throws Exception {

        validateId(id);
        clienteRepository.deleteById(id);
    }

    @Override
    public void editPut(Long id, Cliente cliente) throws Exception {

        validateId(id);
        validateCliente(cliente);

        clienteRepository.save(cliente);
    }

    @Override
    public void editPatch(Long id, String nombre, String apellido, String dni) throws Exception {

        Optional<Cliente> response = clienteRepository.findById(id);

        if (response.isPresent()) {
            Cliente cliente = response.get();

            if (nombre != null && !nombre.isEmpty()) {
                cliente.setNombre(nombre);
            }
            if (apellido != null && !apellido.isEmpty()) {
                cliente.setApellido(apellido);
            }
            if (dni != null && !dni.isEmpty()) {
                cliente.setDni(dni);
            }

            clienteRepository.save(cliente);
        } else {
            throw new Exception("Cliente no existe");
        }
    }

    private void validateCliente(Cliente cliente) throws Exception {

        if (cliente.getNombre().isEmpty() || cliente.getNombre() == null) {
            throw new Exception("Nombre Invalido");
        }

        if (cliente.getApellido().isEmpty() || cliente.getApellido() == null) {
            throw new Exception("Apellido Invalido");
        }

        if (cliente.getDni().isEmpty() || cliente.getDni() == null || cliente.getDni().length() < 6) {
            throw new Exception("Dni Invalido");
        }
    }

    private void validateId(Long id) throws Exception {

        if (clienteRepository.existsById(id) == false) {
            throw new Exception("Producto no existe");
        }
    }

}
