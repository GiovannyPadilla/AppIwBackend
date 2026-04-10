package com.example.App.AppIW.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE", nullable = false)
    private String name;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "PRECIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Column(name = "CATEGORIA")
    private String category;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime createdAt;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime updatedAt;
}