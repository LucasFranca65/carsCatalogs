package com.suportefrancati.carsCatalogs.api.dto;

import com.suportefrancati.carsCatalogs.api.models.Car;
import lombok.Data;

@Data
public class CarDTO {

    private Long id;
    private String nome;
    private String tipo;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.nome = car.getNome();
        this.tipo = car.getTipo();
    }
}
