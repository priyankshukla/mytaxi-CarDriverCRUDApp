package com.mytaxi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytaxi.AbstractTest;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.DefaultCarService;

/**
 * Created by PriyankShukla on 10/09/18.
 */
public class CarServiceTest extends AbstractTest
{

    @Mock
    private CarRepository carRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private DefaultCarService carService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultCarService.class);
    }

    @Test
    public void testCreate() throws EntityNotFoundException
    {
    	CarDO car = getCar();
    	ManufacturerDO manufacturer = getManufacturer();
        when(manufacturerRepository.findByName(any(String.class))).thenReturn(manufacturer);
        when(carRepository.save(any(CarDO.class))).thenReturn(car);
        carService.create(car);
        verify(manufacturerRepository, times(1)).findByName(any(String.class));
        verify(carRepository, times(1)).save(car);
    }


}

