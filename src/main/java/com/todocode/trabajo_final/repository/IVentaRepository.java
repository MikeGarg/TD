package com.todocode.trabajo_final.repository;

import com.todocode.trabajo_final.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {
    
}
