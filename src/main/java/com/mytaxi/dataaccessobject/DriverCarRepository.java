package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.DriverCarDO;

/**
 * Created by PriyankShukla on 09/09/18.
 */
public interface DriverCarRepository extends CrudRepository<DriverCarDO, Long>
{
    DriverCarDO findByDriverIdAndCarId(final Long driverId, final Long carId);

    @Query("SELECT D, C FROM CarDO C, DriverDO D, DriverCarDO DC " +
            "WHERE DC.carId = C.id AND D.id = DC.driverId"
     )
	List<Object[]> findSelectedCarsByDrivers();

}
