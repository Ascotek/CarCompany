package com.pl.car.api;

import com.pl.car.dao.Car;
import com.pl.car.manager.CarManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/CarCompany")
public class CarApi {

    private CarManager carManager;

    @Autowired
    public CarApi(CarManager carManager) {
        this.carManager = carManager;
    }


    @GetMapping("/all")
    public Iterable<Car> getAll(){
        return this.carManager.findAll();
    }

    @GetMapping
    public Optional<Car> getById(@RequestParam Long id){
        return this.carManager.findById(id);
    }
//    @GetMapping
//    public Optional<Car> getByMinPrice(@RequestParam Long price){
//        return this.carManager.findCheapestPrice();
//    }
//    @GetMapping
//    public Optional<Car> getByMaxPrice(@RequestParam Long price){
//        return this.carManager.findMostExpensivePrice();
//    }

    @PostMapping                     //dodawanie
    public Car post(@RequestBody Car car){
        return this.carManager.save(car);
    }

    @PutMapping                      //nadpisywanie
    public Car put(@RequestBody Car car){
        return this.carManager.save(car);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        carManager.deleteById(id);
    }
}
