package com.mytaxi.dataaccessobject;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.mytaxi.domainobject.CarDO;

/**
 * Created by PriyankShukla on 10/09/18.
 */
public class CarRepositoryTest extends AbstractRepositoryTest
{

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testDriverById()
    {
        CarDO car = carRepository.findById(1L).get();
        Assert.assertNotNull(car);
    }


   @Test
    public void testAllCars()
    {
        List<CarDO> cars = Lists.newArrayList(carRepository.findAll());
        Assert.assertThat(cars, hasSize(3));
    }

}
