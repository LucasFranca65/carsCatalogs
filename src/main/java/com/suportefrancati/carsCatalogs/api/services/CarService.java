package com.suportefrancati.carsCatalogs.api.services;

import com.suportefrancati.carsCatalogs.api.dto.CarDTO;
import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarsRepository carsRepository;

    public List<CarDTO> findAllCars() {

        return carsRepository.findAll().stream().map(CarDTO::new).collect(Collectors.toList());

    }

    public Optional<Car> findCarById(Long id) {
        return carsRepository.findById(id);
    }

    public List<CarDTO> findAllCarsByType(String tipo) {
        return carsRepository.findCarByTipo(tipo).stream().map(CarDTO::new).collect(Collectors.toList());
    }

    public Car saveNewCar(Car carro) {
        carsRepository.save(carro);
        return carro;
    }

    @Transactional
    public Car updateCar(Long id, Car carro) {
        Assert.notNull(id, "Não foi passado id para atualizar o registro");

        Optional<Car> optionalCar = carsRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car carDb = optionalCar.get();
            carDb.setNome(carro.getNome());
            carDb.setTipo(carro.getTipo());
            System.out.println("Carro id " + carDb.getId());

            carsRepository.save(carDb);
            return carDb;
        } else {
            throw new RuntimeException("Não foi possivel atualizar o carro");
        }
    }

    public String deleteCar(Long id) {
        Optional<Car> optionalCar = carsRepository.findById(id);
        if (optionalCar.isPresent()) {
            carsRepository.deleteById(id);
            return "Car deletado com sucesso";
        } else {
            return "Não foi possivel deletar o carro, id não encontrado";
        }
    }

}


