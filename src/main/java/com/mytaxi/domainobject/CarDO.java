package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by PriyankShukla on 09/09/18.
 */
@Entity
@Table(name = "car")
public class CarDO {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "date_created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    private Float rating;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "seat_count")
    private Integer seatCount;
    
    @Column(nullable = false)
    private Boolean convertible;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToOne
    @JoinColumn(name="manufacturer")
    private ManufacturerDO manufacturer;

    public CarDO()
    {
    }

    public CarDO(Long id,Float rating, String engineType, String licensePlate, Integer seatCount) {
		super();
		this.id=id;
		this.dateCreated = null;
		this.rating = rating;
		this.engineType = engineType;
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
		this.manufacturer = null;
		this.deleted=false;
		this.convertible=false;
	}

	public Long getId() {
		return id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public Float getRating() {
		return rating;
	}

	public String getEngineType() {
		return engineType;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public Boolean getConvertible() {
		return convertible;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public ManufacturerDO getManufacturer() {
		return manufacturer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setManufacturer(ManufacturerDO manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "CarDO [id=" + id + ", dateCreated=" + dateCreated + ", rating=" + rating + ", engineType=" + engineType
				+ ", licensePlate=" + licensePlate + ", seatCount=" + seatCount + ", convertible=" + convertible
				+ ", deleted=" + deleted + ", manufacturer=" + manufacturer + "]";
	}

	
}
