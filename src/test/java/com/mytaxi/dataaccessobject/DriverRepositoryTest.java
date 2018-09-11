package com.mytaxi.dataaccessobject;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Created by PriyankShukla on 10/09/18.
 */

public class DriverRepositoryTest  extends AbstractRepositoryTest
{

    private static final String USER_NAME = "driver02";


    @Autowired
    private DriverRepository driverRepository;


    @Test
    public void testDriverById()
    {
        DriverDO driver = driverRepository.findById(1L).get();
        Assert.assertNotNull(driver);
    }


    @Test
    public void testDriverByOnlineStatus()
    {
        List<DriverDO> onlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
        Assert.assertThat(onlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByOfflineStatus()
    {
        List<DriverDO> offlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.OFFLINE);
        Assert.assertThat(offlineDrivers, hasSize(4));
    }
}
