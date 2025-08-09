package com.suportefrancati.carsCatalogs.api.dto;

import com.suportefrancati.carsCatalogs.api.models.Car;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarDTO {

    private Long id;
    private String nome;
    private String tipo;

    /*public CarDTO(Car car) {
        this.id = car.getId();
        this.nome = car.getNome();
        this.tipo = car.getTipo();
    }*/

    public static CarDTO create(Car car) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(car, CarDTO.class);
    }
}
