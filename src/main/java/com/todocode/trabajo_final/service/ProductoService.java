package com.todocode.trabajo_final.service;

import com.todocode.trabajo_final.entity.Producto;
import com.todocode.trabajo_final.repository.IProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public void create(Producto producto) throws Exception {

        validateProducto(producto);
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() throws Exception {

        List<Producto> productos = productoRepository.findAll();
        return productos;
    }

    @Override
    public Producto findOne(Long id) throws Exception {

        Optional<Producto> response = productoRepository.findById(id);
        if (response.isPresent()) {
            Producto producto = response.get();
            return producto;
        } else {
            throw new Exception("Producto no existe");
        }
    }

    @Override
    public void delete(Long id) throws Exception {

        validateId(id);
        productoRepository.deleteById(id);
    }

    @Override
    public void editPut(Long id, Producto producto) throws Exception {

        validateId(id);
        validateProducto(producto);
        productoRepository.save(producto);
    }

    @Override
    public void editPatch(Long id, String nombre, String marca, Double costo, Integer cant_disponible) throws Exception {

        Optional<Producto> response = productoRepository.findById(id);

        if (response.isPresent()) {
            Producto producto = response.get();

            if (nombre != null && !nombre.isEmpty()) {
                producto.setNombre(nombre);
            }
            if (marca != null && !marca.isEmpty()) {
                producto.setMarca(marca);
            }
            if (costo != null && costo > 0) {
                producto.setCosto(costo);
            }
            if (cant_disponible != null) {
                producto.setCant_disponible(cant_disponible);
            }

            productoRepository.save(producto);
        } else {
            throw new Exception("Producto no existe");
        }

    }

    private void validateProducto(Producto producto) throws Exception {

        if (producto.getNombre().isEmpty() || producto.getNombre() == null) {
            throw new Exception("Nombre Invalido");
        }

        if (producto.getMarca().isEmpty() || producto.getMarca() == null) {
            throw new Exception("Marca Invalido");
        }

        if (producto.getCosto().isNaN() || producto.getCosto() == null || producto.getCosto() < 0) {
            throw new Exception("Costo Invalido");
        }

        if (producto.getCant_disponible() == null || producto.getCant_disponible() < 0) {
            throw new Exception("Cantidad disponible Invalido");
        }
    }

    private void validateId(Long id) throws Exception {

        if (productoRepository.existsById(id) == false) {
            throw new Exception("Producto no existe");
        }

    }

}
