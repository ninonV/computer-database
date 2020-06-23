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

	public void setIntroduced(LocalDate introduced) throws Exception {
		if (introduced != null && this.discontinued != null && introduced.isAfter(this.discontinued)) {
			throw new Exception("Introduced date is after discontinued date");
		}
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) throws Exception {
		if (discontinued != null && this.introduced != null && discontinued.isBefore(this.introduced)) {
			throw new Exception("Discontinued date is before introduced date");
		}
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
		sb.append(" id :").append(this.idComputer);
		sb.append(", name :").append(this.name);
		sb.append(", introduced :").append(this.introduced);
		sb.append(", discontinued :").append(this.discontinued).append(" ");
		sb.append(this.manufacturer);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((idComputer == null) ? 0 : idComputer.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (idComputer == null) {
			if (other.idComputer != null)
				return false;
		} else if (!idComputer.equals(other.idComputer))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}

