package com.todocode.trabajo_final.controller;

import com.todocode.trabajo_final.entity.Producto;
import com.todocode.trabajo_final.entity.Venta;
import com.todocode.trabajo_final.service.IVentaService;
import java.util.List;
import java.util.function.ObjDoubleConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Venta> ventas = ventaService.findAll();
            return ResponseEntity.status(200).body(ventas);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Long id) {
        try {
            Venta venta = ventaService.findOne(id);
            return ResponseEntity.status(200).body(venta);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/{idCliente}")
    public ResponseEntity<Object> create(@PathVariable Long idCliente, @RequestBody List<Producto> productos) {
        try {
            ventaService.create(idCliente, productos);
            return ResponseEntity.status(201).body("Venta creada con éxito!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(Venta venta) {
        try {
            ventaService.create(venta);
            return ResponseEntity.status(201).body("Venta creada con éxito!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            ventaService.delete(id);
            return ResponseEntity.status(200).body("Venta eliminada con éxito!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
