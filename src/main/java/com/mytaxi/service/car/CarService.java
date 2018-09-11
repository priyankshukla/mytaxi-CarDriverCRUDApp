package com.mytaxi.service.car;

import javax.validation.Valid;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Created by PriyankShukla on 09/09/18.
 */
public interface CarService
{

    CarDO findCarById(final Long carId) throws EntityNotFoundException;

    Iterable<CarDO> findAllCars();

    CarDO create(final CarDO car) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;

    void update(@Valid CarDTO carDTO) throws EntityNotFoundException;
}
