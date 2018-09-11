package com.mytaxi;

import java.time.ZonedDateTime;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Created by Priyank Shukla on 09/09/18.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTest
{

    public CarDO getCar()
    {
        CarDO car = new CarDO();
        car.setId(1L);
        car.setSeatCount(2);
        car.setRating(11.0F);
        car.setDateCreated(ZonedDateTime.now());
        car.setLicensePlate("ABV101");
        car.setEngineType("test");
        car.setConvertible(true);
        ManufacturerDO manufacturer = new ManufacturerDO();
        manufacturer.setName("Audi");
        manufacturer.setId(1L);
        manufacturer.setDateCreated(ZonedDateTime.now());
        car.setManufacturer(manufacturer);
        return car;
    }


    public ManufacturerDO getManufacturer()
    {
    	ManufacturerDO manufacturer = new ManufacturerDO();
        manufacturer.setDateCreated(ZonedDateTime.now());
        manufacturer.setId(1L);
        manufacturer.setName("Audi");
        return manufacturer;
    }


    public CarDTO getCarDTO()
    {
        return CarDTO.newBuilder()
            .setConvertible(true)
            .setEngineType("test")
            .setLicensePlate("ABV101")
            .setSeatCount(2)
            .setManufacturer("Audi")
            .setRating(11.0F)
            .createCarDTO();
    }


    public DriverDO getDriver()
    {
    	DriverDO driver = new DriverDO();
        driver.setId(1L);
        driver.setDateCreated(ZonedDateTime.now());
        driver.setDeleted(false);
        driver.setUsername("test");
        driver.setPassword("test");
        driver.setOnlineStatus(OnlineStatus.ONLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        driver.setCoordinate(geoCoordinate);
        return driver;
    }


    public DriverDTO getDriverData()
    {
        GeoCoordinate geoCoordinate= new GeoCoordinate(90, 90);
        return DriverDTO.newBuilder()
            .setId(1L)
            .setPassword("test")
            .setUsername("test")
            .setCoordinate(geoCoordinate)
            .createDriverDTO();
    }


    public DriverCarDO getDriverCar()
    {
    	DriverCarDO driverCar = new DriverCarDO();
        driverCar.setCarId(1L);
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        return driverCar;
    }
}
