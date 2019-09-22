package com.pl.car.manager;

import com.pl.car.dao.Car;
import com.pl.car.dao.CarRepo;
import com.pl.car.dao.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarManager {

    private CarRepo carRepo;

    @Autowired
    public CarManager(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Iterable<Car> findAll(){
        return carRepo.findAll();
    }

    public Optional<Car> findById (Long id){
        return carRepo.findById(id);
    }

//    public Optional<Car> findCheapestPrice() {
//        Iterable<Car> minCars = carRepo.findAll();
//
//        Long indWithPriceMin=1L;
//
//        int i=1;
//        for (Car car : minCars) {
//            if(car.getPrice()<carRepo.findById(indWithPriceMin).get().getPrice()){
//                indWithPriceMin=(long)i;
//            }
//            i++;
//        }
//        return carRepo.findById(indWithPriceMin);
//    }
//
//    public Optional<Car> findMostExpensivePrice() {
//        Iterable<Car> maxCars = carRepo.findAll();
//
//        Long indWithPriceMax=1L;
//
//        int i=1;
//        for (Car car : maxCars) {
//            if(car.getPrice()<carRepo.findById(indWithPriceMax).get().getPrice()){
//                indWithPriceMax=(long)i;
//            }
//            i++;
//        }
//        return carRepo.findById(indWithPriceMax);
//
//    }

    public Car save (Car car){
        return carRepo.save(car);
    }

    public void deleteById(Long id){
        carRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)    //automatycznie sie uruchomi
    public void fillDB(){
        save(new Car("ford", 150000L, Color.GREEN));
        save(new Car("mercedes", 450000L, Color.RED));
        save(new Car("bentley", 650000L, Color.BLACK));
    }


}

