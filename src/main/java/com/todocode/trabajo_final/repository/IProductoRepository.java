package com.todocode.trabajo_final.repository;

import com.todocode.trabajo_final.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
