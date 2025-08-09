package com.suportefrancati.carsCatalogs.api.repositories;


import com.suportefrancati.carsCatalogs.api.dto.CarDTO;
import com.suportefrancati.carsCatalogs.api.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findCarByTipo(String tipo);
}
