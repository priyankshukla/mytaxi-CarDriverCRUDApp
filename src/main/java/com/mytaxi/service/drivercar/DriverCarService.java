package com.mytaxi.service.drivercar;

import com.mytaxi.domainobject.DriverCarDO;

/**
 * Created by PriyankShukla on 09/09/18.
 */
public interface DriverCarService
{

    void delete(DriverCarDO driverCarDO);

    DriverCarDO  save(DriverCarDO driverCarDO);

//    DriverCarDO findByDriverIdAndCarId(final Long driverId, final Long carId);

}
