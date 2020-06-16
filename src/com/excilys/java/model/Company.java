package com.excilys.java.model;

/**
 * Class representing a company 
 * @author ninonV
 *
 */


public class Company {

	private long idCompany; 
	private String name;

	public Company() {
		super();
	}

	public Company(long idCompany, String name) {
		super();
		this.idCompany = idCompany;
		this.name = name;
	}

	public long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(long idCompany) {
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
		sb.append("\n\t\tid :").append(idCompany);
		sb.append("\n\t\tname :").append(name).append("\n");
		return sb.toString();
	}

	
}
