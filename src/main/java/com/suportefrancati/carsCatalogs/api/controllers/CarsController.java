package com.suportefrancati.carsCatalogs.api.controllers;

import com.suportefrancati.carsCatalogs.api.dto.CarDTO;
import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {
        Optional<CarDTO> car = carService.findCarById(id);
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


    @PostMapping()
    public ResponseEntity<CarDTO> crateNewCar(@RequestBody Car carro) {
        try {
            CarDTO carDTO = carService.saveNewCar(carro);
            URI location = getUri(carDTO.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCarById(@PathVariable("id") Long id, @RequestBody Car carro) {
        CarDTO car = carService.updateCar(id, carro);
        if (car != null) {
            return ResponseEntity.ok().body(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarById(@PathVariable("id") Long id) {
        if (carService.deleteCar(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
