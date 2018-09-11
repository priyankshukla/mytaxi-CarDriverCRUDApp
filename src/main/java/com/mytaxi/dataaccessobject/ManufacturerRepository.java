package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.ManufacturerDO;


/**
 * Created by PriyankShukla on 09/09/18.
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>
{

    ManufacturerDO findByName(final String name);
}
