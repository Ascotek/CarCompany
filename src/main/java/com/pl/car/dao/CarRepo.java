package com.pl.car.dao;

import com.pl.car.dao.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends CrudRepository<Car, Long> {

}

