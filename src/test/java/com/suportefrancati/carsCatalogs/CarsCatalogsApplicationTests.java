package com.suportefrancati.carsCatalogs;

import com.suportefrancati.carsCatalogs.api.models.Car;
import com.suportefrancati.carsCatalogs.api.services.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarsCatalogsApplicationTests {

    @Autowired
    private CarService carService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSaveDell() {
        Car car = new Car();
        car.setNome("ferrari");
        car.setTipo("esportivo");

        try {
            carService.saveNewCar(car);
            try {
                carService.deleteCar(car.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}
