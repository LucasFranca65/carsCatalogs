package com.suportefrancati.carsCatalogs.api.repositories;


import com.suportefrancati.carsCatalogs.api.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car, Long> {
}
