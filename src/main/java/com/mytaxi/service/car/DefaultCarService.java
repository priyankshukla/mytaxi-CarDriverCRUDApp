package com.mytaxi.service.car;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Created by PriyankSHukla on 09/09/18. Service to encapsulate the link between
 * DAO and controller and to have business logic for some car specific
 * things.
 */
@Service
public class DefaultCarService implements CarService {
	
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public CarDO findCarById(final Long carId) throws EntityNotFoundException {
		return carCheck(carId);
	}

	@Override
	public Iterable<CarDO> findAllCars() {
		return carRepository.findAll();
	}

	@Override
	public CarDO create(final CarDO car) throws EntityNotFoundException {
		car.setManufacturer(manufacturerCheck(car));
		return carRepository.save(car);
	}

	@Override
	@Transactional
	public void update(@Valid CarDTO carDTO) throws EntityNotFoundException {
		 CarDO car = carCheck(carDTO.getId());
		 car.setManufacturer(manufacturerCheck(CarMapper.makeCarDO(carDTO)));
		 car.setConvertible(carDTO.getConvertible());
		 car.setEngineType(carDTO.getEngineType());
		 car.setLicensePlate(carDTO.getLicensePlate());
		 car.setRating(carDTO.getRating());
		 car.setSeatCount(carDTO.getSeatCount());
		 carRepository.save(car);	 
	}

	@Override
	@Transactional
	public void delete(final Long carId) throws EntityNotFoundException {
		CarDO car = carCheck(carId);
		carRepository.delete(car);
	}

	private CarDO carCheck(final Long carId) throws EntityNotFoundException {
		CarDO car = carRepository.findById(carId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find car entity with id: " + carId));
		return car;
	}

	private ManufacturerDO manufacturerCheck(final CarDO car) throws EntityNotFoundException {
		String manufacturerName = car.getManufacturer().getName();
		ManufacturerDO manufacturer = manufacturerRepository.findByName(manufacturerName);
		if (null == manufacturer) {
            LOG.warn("EntityNotFoundException while checking the Manufacturer");
			throw new EntityNotFoundException("Manufacturer not found with this name: " + manufacturerName);
		}
		
		return manufacturer;
	}

}
