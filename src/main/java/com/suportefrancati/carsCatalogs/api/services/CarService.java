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

        return carsRepository.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
        /*
         * mesmo que
         * List<Car> cars = carsRepository.findAll();
         * List<CarDTO> list = new ArrayList();
         * for(Car c: cars){
         *
         *
         * */

    }

    public Optional<CarDTO> findCarById(Long id) {

        Optional<Car> car = carsRepository.findById(id);
        if (car.isPresent()) {
            return carsRepository.findById(id).map(CarDTO::create);

        } else {
            return Optional.empty();
        }

    }

    public List<CarDTO> findAllCarsByType(String tipo) {
        return carsRepository.findCarByTipo(tipo).stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public CarDTO saveNewCar(Car carro) {
        Assert.isNull(carro.getId(), "Erro ao salvar o carro");
        return CarDTO.create(carro);
    }

    @Transactional
    public CarDTO updateCar(Long id, Car carro) {
        Assert.notNull(id, "Não foi passado id para atualizar o registro");

        Optional<Car> optionalCar = carsRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car carDb = optionalCar.get();
            carDb.setNome(carro.getNome());
            carDb.setTipo(carro.getTipo());
            System.out.println("Carro id " + carDb.getId());

            carsRepository.save(carDb);
            return CarDTO.create(carDb);
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


