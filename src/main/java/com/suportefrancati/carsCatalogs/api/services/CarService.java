package com.suportefrancati.carsCatalogs.api.services;

import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarsRepository carsRepository;

    public List<Car> findAllCars(){
        return carsRepository.findAll();
    }

    public Car findCarById(Long id){
        return carsRepository.findById(id).get();
    }

    public List<Car> findAllCarsByType(String tipo){
        return carsRepository.findCarByTipo(tipo);
    }

    public Car saveNewCar(Car carro){
        carsRepository.save(carro);
        return carro;
    }

    public Car updateCar(Long id, Car carro){
        Assert.notNull(id,"Não foi passado id para atualizar o registro");

        Optional<Car> optionalCar = carsRepository.findById(id);
        if(optionalCar.isPresent()){
            Car carDb = optionalCar.get();
            carDb.setNome(carro.getNome());
            carDb.setTipo(carro.getTipo());
            System.out.println("Carro id "+carDb.getId());

            carsRepository.save(carDb);
            return carDb;
        }else{
            throw new RuntimeException("Não foi possivel atualizar o carro");
        }
    }

}
