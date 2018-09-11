package com.mytaxi.dataaccessobject;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mytaxi.MytaxiServerApplicantTestApplication;

/**
 * Created by PriyankShukla on 10/09/18.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = AbstractRepositoryTest.IntegrationTest.class)
public class AbstractRepositoryTest
{

    @Configuration
    @EntityScan( basePackages = {"com.mytaxi.domainobject"} )
    @EnableAutoConfiguration(exclude = {
        WebMvcAutoConfiguration.class
    })
    protected static class IntegrationTest {
        public static void main(String[] args) {
            SpringApplication.run(MytaxiServerApplicantTestApplication.class, args);
        }
    }

    @Test
    public void emptyTest(){
        // Any generic test can be define here
    }



}
