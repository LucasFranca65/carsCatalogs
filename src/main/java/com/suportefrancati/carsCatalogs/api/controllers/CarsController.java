package com.suportefrancati.carsCatalogs.api.controllers;

import com.suportefrancati.carsCatalogs.api.dto.CarDTO;
import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.findAllCars();
        if (cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(cars);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Optional<Car> car = carService.findCarById(id);
        return car.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());

        /* expressão lambda acima é o mesmo que  if (car.isPresent()) {
            return ResponseEntity.ok().body(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }


    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarDTO>> getCarByType(@PathVariable("tipo") String tipo) {
        List<CarDTO> cars = carService.findAllCarsByType(tipo);
        if (cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(cars);
        }
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
