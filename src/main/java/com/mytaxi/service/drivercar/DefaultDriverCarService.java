package com.mytaxi.service.drivercar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverCarDO;

/**
 * Created by PriyankShukla on 09/09/18. 
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver car specific
 * things.
 */
@Service
public class DefaultDriverCarService implements DriverCarService
{

    @Autowired
    private DriverCarRepository driverCarRepository;


    @Override public void delete(DriverCarDO driverCar)
    {
        driverCarRepository.delete(driverCar);
    }


    @Override public DriverCarDO save(DriverCarDO driverCar)
    {
        return driverCarRepository.save(driverCar);
    }

}
