package com.suportefrancati.carsCatalogs.api.services;

import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarsRepository carsRepository;

    public List<Car> findAllCars(){
        return carsRepository.findAll();
    }


}
