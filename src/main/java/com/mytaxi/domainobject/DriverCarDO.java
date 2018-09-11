package com.mytaxi.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by PriyankShukla on 09/09/18.
 */

@Entity
@Table(name = "driver_car")
public class DriverCarDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "driver_id", unique = true, nullable = false)
	private Long driverId;

	@Column(name = "car_id", unique = true, nullable = false)
	private Long carId;

	public Long getId() {
		return id;
	}

	public Long getDriverId() {
		return driverId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

}
