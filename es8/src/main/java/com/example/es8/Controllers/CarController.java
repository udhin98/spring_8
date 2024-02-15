package com.example.es8.Controllers;

import com.example.es8.Entities.Car;
import com.example.es8.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarRepository carRepository;
    @GetMapping("/all")
    public List<Car> getAllCar () {
        return carRepository.findAll();
    }
    @GetMapping("/{id}")
    public Car getCarById (@PathVariable Long id) {
        Boolean exist = carRepository.existsById(id);
        if (exist) {
            return carRepository.findById(id).orElse(null);
        } return new Car();
    }
    @PostMapping("/create")
    public Car createCar (@RequestBody Car car) {
        carRepository.save(car);
        return car;
    }
    @PutMapping("/{id}")
    public Car updateType (@PathVariable Long id, @RequestParam String type) {
        boolean exist = carRepository.existsById(id);
        if (exist) {
            Car car = carRepository.findById(id).orElse(null);
            car.setType(type);
            carRepository.save(car);
            return car;
        } return new Car();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarById (@PathVariable Long id) {
        boolean exist = carRepository.existsById(id);
        if (exist) {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @DeleteMapping("/all")
    public void deleteAll () {
        carRepository.deleteAll();
    }

}
