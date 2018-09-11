package com.mytaxi.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;

public class CarMapper {

    public static CarDTO makeCarDTO(CarDO car)
    {
        return CarDTO.newBuilder()
            .setId(car.getId())
            .setConvertible(car.getConvertible())
            .setEngineType(car.getEngineType())
            .setLicensePlate(car.getLicensePlate())
            .setManufacturer(car.getManufacturer().getName())
            .setRating(car.getRating())
            .setSeatCount(car.getSeatCount())
            .createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Iterable<CarDO> carIterable)
    {
        List<CarDTO> carDataList = new ArrayList<>();
        carIterable.forEach(car -> carDataList.add(makeCarDTO(car)));
        return carDataList;
    }


    public static CarDO makeCarDO(CarDTO carDTO)
    {
        CarDO carDO = new CarDO();
        carDO.setId(carDTO.getId());
        carDO.setConvertible(carDTO.getConvertible());
        carDO.setEngineType(carDTO.getEngineType());
        carDO.setLicensePlate(carDTO.getLicensePlate());
        ManufacturerDO manufacturer = new ManufacturerDO();
        manufacturer.setName(carDTO.getManufacturer());
        carDO.setManufacturer(manufacturer);
        carDO.setRating(carDTO.getRating());
        carDO.setSeatCount(carDTO.getSeatCount());
        return carDO;

    }

}
