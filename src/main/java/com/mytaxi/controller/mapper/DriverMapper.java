package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }

    public static DriverDTO makeDriverDTO(Object[] object)
    {
        DriverDO driver = (DriverDO) object[0];
        CarDO car =  (CarDO) object[1] ;
        CarDTO carDTO = CarDTO.newBuilder()
        		.setId(car.getId())
        		.setConvertible(car.getConvertible())
        		.setEngineType(car.getEngineType())
        		.setLicensePlate(car.getLicensePlate())
            .setRating(car.getRating())
            .setSeatCount(car.getSeatCount())
            .setManufacturer(car.getManufacturer().getName()).createCarDTO();
        
        DriverDTO driverDTO = makeDriverDTO(driver);
        driverDTO.setCarDTO(carDTO);
        return driverDTO;
    }

    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
