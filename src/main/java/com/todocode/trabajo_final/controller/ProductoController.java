package com.todocode.trabajo_final.controller;

import com.todocode.trabajo_final.entity.Producto;
import com.todocode.trabajo_final.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Producto> productos = productoService.findAll();
            return ResponseEntity.status(200).body(productos);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Long id) {
        try {
            Producto producto = productoService.findOne(id);
            return ResponseEntity.status(200).body(producto);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Producto producto) {
        try {
            productoService.create(producto);
            return ResponseEntity.status(201).body("Producto creado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.status(200).body("Producto borrado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editPut(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            productoService.editPut(id, producto);
            return ResponseEntity.status(200).body("Producto modificado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> editPatch(@PathVariable Long id,
            @RequestParam(required = false, name = "nombre") String nombre,
            @RequestParam(required = false, name = "marca") String marca,
            @RequestParam(required = false, name = "costo") Double costo,
            @RequestParam(required = false, name = "cant_disponible") Integer cant_disponible) {
        try {
            productoService.editPatch(id, nombre, marca, costo, cant_disponible);
            return ResponseEntity.status(200).body("Producto modificado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
