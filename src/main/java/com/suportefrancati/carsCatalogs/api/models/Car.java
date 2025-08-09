package com.suportefrancati.carsCatalogs.api.models;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "carro")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
}
