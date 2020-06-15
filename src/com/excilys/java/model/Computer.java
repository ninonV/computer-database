package com.excilys.java.model;

import java.sql.Date;

/**
 * Classe représentant un ordinateur
 * 
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

	public Computer(String name) {
		super();
		this.name = name;
	}

	public Computer(Long idComputer, String name, Date introduced, Date discontinued, Company manufacturer) {
		super();
		this.idComputer = idComputer;
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
		return "Computer [idComputer=" + idComputer + ", name=" + name + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", manufacturer=" + manufacturer + "]";
	}
	
	
	
}

