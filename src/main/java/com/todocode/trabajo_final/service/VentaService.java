package com.todocode.trabajo_final.service;

import com.todocode.trabajo_final.entity.Cliente;
import com.todocode.trabajo_final.entity.Producto;
import com.todocode.trabajo_final.entity.Venta;
import com.todocode.trabajo_final.repository.IClienteRepository;
import com.todocode.trabajo_final.repository.IProductoRepository;
import com.todocode.trabajo_final.repository.IVentaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public void create(Long idCliente, List<Producto> productos) throws Exception {
        Cliente cliente = validateCliente(idCliente);
        validateProductos(productos);
        Venta venta = new Venta(cliente, productos);
        ventaRepository.save(venta);
    }

    @Override
    public void create(Venta venta) throws Exception {
        //validateCliente(venta.getCliente().getId());
        //validateProductos(venta.getProductos());
        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> findAll() throws Exception {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas;
    }

    @Override
    public Venta findOne(Long id) throws Exception {
        Optional<Venta> response = ventaRepository.findById(id);
        if (response.isPresent()) {
            Venta venta = response.get();
            return venta;
        } else {
            throw new Exception("Venta no existe");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        validateId(id);
        ventaRepository.deleteById(id);
    }

    @Override
    public void editPut(Long id, Venta venta) throws Exception {
    }

    @Override
    public void editPatch(Long id, String nombre, String apellido, String dni) throws Exception {
    }

    private void validateId(Long id) throws Exception {
        if (ventaRepository.existsById(id) == false) {
            throw new Exception("Venta no existe");
        }
    }

    private Cliente validateCliente(Long idCliente) throws Exception {
        Optional<Cliente> response = clienteRepository.findById(idCliente);
        if (response.isEmpty()) {
            throw new Exception("Cliente no existe");
        }
        Cliente cliente = response.get();
        return cliente;
    }

    private void validateProductos(List<Producto> productos) throws Exception {
        Map<Long, Integer> listaMap = new HashMap<>();
        //Contabiliza cantidad de productos repetidos y Verifica existencia en DB.
        for (Producto producto : productos) {
            validateProducto(producto);

            Long key = producto.getId();
            if (listaMap.containsKey(key) == false) {
                listaMap.put(key, 1);
            } else {
                Integer value = listaMap.get(key);
                listaMap.put(key, value + 1);
            }
        }
        //Compara cantidad de compra con Stock real en DB
        for (Map.Entry<Long, Integer> entry : listaMap.entrySet()) {
            Long id = entry.getKey();
            Integer value = entry.getValue();

            Optional<Producto> response = productoRepository.findById(id);
            Producto producto = response.get();
            if (producto.getCant_disponible() < value) {
                throw new Exception("Stock insuficiente: " + producto.getNombre() + " " + producto.getMarca() + " Cant_disponible: " + producto.getCant_disponible() + " Cant_compra: " + value);
            }
        }
    }

    private void validateProducto(Producto producto) throws Exception {
        if (productoRepository.existsById(producto.getId()) != true) {
            throw new Exception("Producto id:" + producto.getId() + " no existe");
        }
    }

}
