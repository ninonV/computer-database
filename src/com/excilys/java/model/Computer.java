package com.excilys.java.model;

import java.sql.Date;

/**
 * Class representing a computer
 * @author ninonV
 *
 */

public class Computer {
	
	private Long idComputer;
	private String name;
	private Date introduced; 
	private Date discontinued;
	private Company manufacturer;
	
	public Computer() {
		super();
	}

	public Computer(String name, Date introduced, Date discontinued, Company manufacturer) {
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
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
		sb.append("\n\tid :").append(idComputer);
		sb.append("\n\tname :").append(name);
		sb.append("\n\tintroduced :").append(introduced);
		sb.append("\n\tdiscontinued :").append(discontinued).append("\n\t");
		sb.append(manufacturer).append("\n");
		return sb.toString();
	}
	
	
	
}

