package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;

/**
 * All operations with a car will be routed by this controller. Created by
 * PriyankShukla on 09/09/18.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/{carId}")
	public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException {
		return CarMapper.makeCarDTO(carService.findCarById(carId));
	}

	@GetMapping
	public List<CarDTO> getAllCars() {
		return CarMapper.makeCarDTOList(carService.findAllCars());
	}

	@PostMapping
	public CarDTO createCar(@Valid @RequestBody CarDTO carDTO)
			throws ConstraintsViolationException, EntityNotFoundException {
    	
		CarDO carDO = CarMapper.makeCarDO(carDTO);
		return CarMapper.makeCarDTO(carService.create(carDO));
	}

	@DeleteMapping("/{carId}")
	public void deleteCar(@PathVariable long carId) throws EntityNotFoundException {
		carService.delete(carId);
	}

	@PutMapping
	public void updateCar(@Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException {
		carService.update(carDTO);
	}
}
