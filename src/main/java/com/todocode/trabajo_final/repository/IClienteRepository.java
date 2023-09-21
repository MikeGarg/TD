package com.todocode.trabajo_final.repository;

import com.todocode.trabajo_final.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
