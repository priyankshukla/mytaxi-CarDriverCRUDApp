package com.mytaxi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytaxi.AbstractTest;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;

/**
 * Created by PriyankShukla on 10/09/18.
 */
public class DriverServiceTest extends AbstractTest
{

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DefaultDriverService driverService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultDriverService.class);
    }

    @Test
    public void testFindByOnlineStatus()
    {
        List<DriverDO> drivers = Collections.singletonList(getDriver());
        when(driverRepository.findByOnlineStatus(any(OnlineStatus.class))).thenReturn(drivers);
        driverService.find(OnlineStatus.ONLINE);
        verify(driverRepository, times(1)).findByOnlineStatus(any(OnlineStatus.class));
    }


}
