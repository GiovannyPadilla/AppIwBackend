package com.example.App.AppIW.repository;

import com.example.App.AppIW.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
    List<ProductEntity> findByCategory(String category);
}