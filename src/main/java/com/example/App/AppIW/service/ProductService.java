package com.example.App.AppIW.service;

import com.example.App.AppIW.model.ProductEntity;
import com.example.App.AppIW.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<ProductEntity> getAll() {
        return repo.findAll();
    }

    public Optional<ProductEntity> getById(int id) {
        return repo.findById(id);
    }

    public ProductEntity create(ProductEntity product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return repo.save(product);
    }

    public ProductEntity update(int id, ProductEntity updated) {
        return repo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setPrice(updated.getPrice());
            existing.setStock(updated.getStock());
            existing.setCategory(updated.getCategory());
            existing.setUpdatedAt(LocalDateTime.now());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        repo.deleteById(id);
    }

    public List<ProductEntity> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
}