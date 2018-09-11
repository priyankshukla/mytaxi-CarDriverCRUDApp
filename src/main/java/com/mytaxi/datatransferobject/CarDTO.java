package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

//	@JsonIgnore
	private Long id;

	@NotNull(message = "Rating can not be null!")
	private Float rating;

	@NotNull(message = "EngineType can not be null!")
	private String engineType;

	@NotNull(message = "SeatCount can not be null!")
	private Integer seatCount;

	@NotNull(message = "Convertable feture can not be null!")
	private Boolean convertible;

	@NotNull(message = "License plate can not be null!")
	private String licensePlate;

	@NotNull(message = "Manufacturer can not be null!")
	private String manufacturer;

	private CarDTO() {
	}

	public CarDTO(Long id, @NotNull(message = "Rating can not be null!") Float rating,
			@NotNull(message = "Engine type can not be null!") String engineType,
			@NotNull(message = "SeatCount can not be null!") Integer seatCount,
			@NotNull(message = "Convertable feture can not be null!") Boolean convertible,
			@NotNull(message = "License plate can not be null!") String licensePlate,
			@NotNull(message = "Manufacturer can not be null!") String manufacturer) {
		super();
		this.id = id;
		this.rating = rating;
		this.engineType = engineType;
		this.seatCount = seatCount;
		this.convertible = convertible;
		this.licensePlate = licensePlate;
		this.manufacturer = manufacturer;
	}


	@JsonProperty
	public Long getId() {
		return id;
	}

	public Float getRating() {
		return rating;
	}

	public String getEngineType() {
		return engineType;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public Boolean getConvertible() {
		return convertible;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	
	public static CarDTOBuilder newBuilder() {
		return new CarDTOBuilder();
	}

	public static class CarDTOBuilder {
		private Long id;
		private Float rating;
		private String engineType;
		private Integer seatCount;
		private Boolean convertible;
		private String licensePlate;
		private String manufacturer;

		public CarDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public Long getId() {
			return id;
		}

		public CarDTOBuilder setRating(Float rating) {
			this.rating = rating;
			return this;
		}

		public CarDTOBuilder setEngineType(String engineType) {
			this.engineType = engineType;
			return this;
		}

		public CarDTOBuilder setSeatCount(Integer seatCount) {
			this.seatCount = seatCount;
			return this;
		}

		public CarDTOBuilder setConvertible(Boolean convertible) {
			this.convertible = convertible;
			return this;
		}

		public CarDTOBuilder setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}

		public CarDTOBuilder setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public CarDTO createCarDTO() {
			return new CarDTO(id, rating, engineType, seatCount, convertible, licensePlate, manufacturer);
		}

	}
}
