package com.mytaxi.service.driver;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverCarRepository driverCarRepository;


    public DefaultDriverService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find driver entity with id: " + driverId));
    }
    
    /**
     * Car selection by Driver
     */
    @Override public DriverDTO selectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        Object[] object = new Object[2];
        DriverDO driver;
        CarDO car;
        try
        {
            driver = driverRepository.findById(driverId)
                    .orElseThrow(() -> new EntityNotFoundException("Could not find Driver entity with id: " + driverId));
            car = carRepository.findById(carId)
                    .orElseThrow(() -> new EntityNotFoundException("Could not find Car entity with id: " + carId));
            
            System.out.println(driver.toString());
            System.out.println(car.toString());
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCarDO driverCar = new DriverCarDO();
                driverCar.setDriverId(driver.getId());
                driverCar.setCarId(car.getId());
                driverCarRepository.save(driverCar);
                object[0] = driver;
                object[1] = car;
            }
            else if (null != driver && null != car && OnlineStatus.OFFLINE.equals(driver.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Driver is offline");
            }
        }
        catch (EntityNotFoundException e)
        {
        	LOG.warn("Car or Driver entity not found ");
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (DataIntegrityViolationException e)
        {
        	LOG.warn("Car is already taken by driver");
            throw new CarAlreadyInUseException("Car is already taken by driver");
        }
        return DriverMapper.makeDriverDTO(object);

    }

    /**
     * Car de-selection by Driver
     */
    @Override public void deSelectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        DriverDO driver;
        CarDO car;
        try
        {
        	 driver = driverRepository.findById(driverId)
                     .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
             car = carRepository.findById(carId)
                     .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCarDO driverCar = driverCarRepository.findByDriverIdAndCarId(driver.getId(), car.getId());
                driverCarRepository.delete(driverCar);
            }
        }
        catch (EntityNotFoundException e)
        {
        	LOG.warn("Car or Driver entity not found");
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (InvalidDataAccessApiUsageException e)
        {
        	LOG.warn("Car is already deselected by the driver");
            throw new CarAlreadyInUseException("Car is already deselected by the driver");
        }
    }

	@Override
	public List<DriverDTO> findSelectedCarsByDrivers() {
        List<DriverDTO> driverDataList = new ArrayList<>();
        List<Object[]> drivers = driverCarRepository.findSelectedCarsByDrivers();
        drivers.forEach(object -> driverDataList.add(DriverMapper.makeDriverDTO(object)));
        return driverDataList;
    }

}
