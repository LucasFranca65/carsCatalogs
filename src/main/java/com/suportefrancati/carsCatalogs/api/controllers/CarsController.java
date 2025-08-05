package com.suportefrancati.carsCatalogs.api.controllers;

import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> allCars(){
        return carService.findAllCars();
    }
}
