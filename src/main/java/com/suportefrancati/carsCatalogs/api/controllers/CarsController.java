package com.suportefrancati.carsCatalogs.api.controllers;

import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<List<Car>> allCars() {
        List<Car> car = carService.findAllCars();
        return ResponseEntity.ok().body(car);
    }

    @GetMapping("/{id}")
    public Car carById(@PathVariable("id") Long id) {
        return carService.findCarById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Car> carByType(@PathVariable("tipo") String tipo) {
        return carService.findAllCarsByType(tipo);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public String crateNewCar(@RequestBody Car carro) {
        Car car = carService.saveNewCar(carro);
        return car.toString();
    }

    @PutMapping("/{id}")
    public String updateCarById(@PathVariable("id") Long id, @RequestBody Car carro) {
        Car car = carService.updateCar(id, carro);
        return car.toString();
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable("id") Long id) {
        return carService.deleteCar(id);

    }
}
