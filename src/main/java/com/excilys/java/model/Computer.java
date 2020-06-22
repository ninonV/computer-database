package com.excilys.java.model;

import java.time.LocalDate;

/**
 * Class representing a computer
 * @author ninonV
 *
 */

public class Computer {
	
	private Long idComputer;
	private String name;
	private LocalDate introduced; 
	private LocalDate discontinued;
	private Company manufacturer;
	
	public Computer() {
		super();
	}

	public Computer(String name, LocalDate introduced, LocalDate discontinued, Company manufacturer) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.manufacturer = manufacturer;
	}

	public Long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(Long idComputer) {
		this.idComputer = idComputer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		if (introduced != null && this.discontinued != null && introduced.isBefore(this.discontinued)) {
			this.introduced = introduced;
		}
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		if (discontinued != null && this.introduced != null && discontinued.isAfter(this.introduced)) {
			this.discontinued = discontinued;
		}
	}

	public Company getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append(" id :").append(this.idComputer);
		sb.append(", name :").append(this.name);
		sb.append(", introduced :").append(this.introduced);
		sb.append(", discontinued :").append(this.discontinued).append(" ");
		sb.append(this.manufacturer);
		return sb.toString();
	}
	
	
	
}

