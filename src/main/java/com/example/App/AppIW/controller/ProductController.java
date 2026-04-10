package com.example.App.AppIW.controller;

import com.example.App.AppIW.model.ProductEntity;
import com.example.App.AppIW.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return productService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(Map.of("message", "Producto no encontrado")));
    }


    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductEntity product) {
        try {
            ProductEntity created = productService.create(product);
            return ResponseEntity.status(201).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ProductEntity product) {
        try {
            ProductEntity updated = productService.update(id, product);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok(Map.of("success", true, "message", "Producto eliminado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }
}