package com.suportefrancati.carsCatalogs.api.controllers;

import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public Car carById(@PathVariable("id") Long id){
        return carService.findCarById(id);
    }
    @GetMapping("/tipo/{tipo}")
    public List<Car> carByType(@PathVariable("tipo") String tipo){
        return carService.findAllCarsByType(tipo);
    }
    @PostMapping("/insert")
    public String crateNewCar(@RequestBody Car carro){
        Car car =  carService.saveNewCar(carro);
        return car.toString();
    }
    @PutMapping("/update/{id}")
    public String updateCarById(@PathVariable("id") Long id, @RequestBody Car carro){
        Car car =  carService.updateCar(id,carro);
        return car.toString();
    }




}
