package com.tpe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tpe.domain.Car;


@Repository
public interface CarRepository extends CrudRepository<Car, Long>{
	

}
