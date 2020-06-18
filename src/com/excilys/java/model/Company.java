package com.excilys.java.model;

/**
 * Class representing a company 
 * @author ninonV
 *
 */


public class Company {

	private Long idCompany; 
	private String name;

	public Company() {
		super();
	}

	public Company(Long idCompany, String name) {
		super();
		this.idCompany = idCompany;
		this.name = name;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append(" id :").append(this.idCompany);
		sb.append(", name :").append(this.name).append("\n");
		return sb.toString();
	}

	
}
