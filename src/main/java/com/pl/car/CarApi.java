package com.pl.car;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarApi {
    List<Car>cars;

    public CarApi(){
        cars= new ArrayList<Car>();
        cars.add(new Car("ford", 450000L, Color.GREEN));
        cars.add(new Car("mercedes", 650000L, Color.BLACK));
        cars.add(new Car("bentley", 800000L, Color.RED));
    }

    @GetMapping("car")
    public List<Car> getCars(){
        return cars;
    }

    @PostMapping("car")
    public boolean addCars(@RequestBody Car c){
        return cars.add(c);
    }

    @DeleteMapping("car")
    public void deleteCars(@RequestParam int index){
        cars.remove(index);
    }
}
