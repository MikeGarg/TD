package com.todocode.trabajo_final.controller;

import com.todocode.trabajo_final.entity.Cliente;
import com.todocode.trabajo_final.service.IClienteService;
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
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Cliente> clientes = clienteService.findAll();
            return ResponseEntity.status(200).body(clientes);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> finOne(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findOne(id);
            return ResponseEntity.status(200).body(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Cliente cliente) {
        try {
            clienteService.create(cliente);
            return ResponseEntity.status(201).body("Cliente creado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return ResponseEntity.status(200).body("Cliente borrado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editPut(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            clienteService.editPut(id, cliente);
            return ResponseEntity.status(200).body("Cliente editado con éxito");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());

        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> editPatch(@PathVariable Long id,
            @RequestParam(required = false, name = "nombre") String nombre,
            @RequestParam(required = false, name = "apellido") String apellido,
            @RequestParam(required = false, name = "dni") String dni) {
        try {
            clienteService.editPatch(id, nombre, apellido, dni);
            return ResponseEntity.status(200).body("Cliente modificado con éxito!");

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
