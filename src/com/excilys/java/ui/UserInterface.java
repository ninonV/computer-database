package com.excilys.java.ui;

import java.sql.Date;
import java.util.Scanner;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.service.CompanyService;
import com.excilys.java.service.ComputerService;

public class UserInterface {
	
	private static ComputerService computerService = ComputerService.getInstance();  
	private static CompanyService companyService = CompanyService.getInstance();
	
	
	public void start() {
		
		boolean quit = false; 
		
		while (!quit) {
			try {
				System.out.println("Features : ");
				System.out.println("\t 1- List computers");
				System.out.println("\t 2- List companies");
				System.out.println("\t 3- Show computer details");
				System.out.println("\t 4- Create a computer");
				System.out.println("\t 5- Update a computer");
				System.out.println("\t 6- Delete a computer");
				System.out.println("\t 7- Quit");
				
				System.out.println("What do you want to do ? ");
				Scanner sc = new Scanner(System.in);
				String choice = sc.nextLine();
				int choix = Integer.parseInt(choice);
				
				switch(choix) {
					case 1:
						computerService.listComputers();
						break;
						
					case 2: 
						companyService.listCompanies();
						break; 
						
					case 3: 
						Long idC = getID();
						Computer computerID = computerService.findbyID(idC);
						break; 
						
					case 4:
						System.out.println("What is the computer that you want to create : ");
						Computer computerCreate = infoComputer(); 
						computerService.createComputer(computerCreate);
						System.out.println("Computer created with success");
						break; 
						
					case 5:
						System.out.println("Which computer do you want to update: ");
						Long idComputer = getID();
						if (computerService.existComputer(idComputer)) {
							System.out.println("Complete the new informations : ");
							Computer computerUpdate = infoComputer();
							computerUpdate.setIdComputer(idComputer);
							computerService.updateComputer(computerUpdate);
							System.out.println("Computer updated with success");
						}else {
							System.out.println("This computer does not exist");
						}
						break;
						
					case 6: 
						System.out.println("Which computer do you want to delete: ");
						Long idComputerDelete = getID();
						if (computerService.existComputer(idComputerDelete)) {
								computerService.deleteComputer(idComputerDelete);
						}else {
							System.out.println("This computer does not exist");
						}
						break;
					case 7: 
						quit = true; 
						break;
					default: 
						System.out.println("Select a choice between 1 and 7");
				}
			}catch (java.lang.NumberFormatException e) {
				System.err.println("Enter a number between 1 and 7");
			}
			
		}
	}
	
	public Long getID() {
		
		System.out.println("ID : ");
		Scanner sc = new Scanner(System.in);
		String idC= sc.nextLine();
		Long id=null;
		
		try{
			id = Long.parseLong(idC);
		}catch (java.lang.NumberFormatException e) {
			System.err.println("Enter a number");
		}
		return id;
	}
	
	public Computer infoComputer() {
		Scanner sc = new Scanner(System.in);
		Boolean conditionsOK= false;
		String name=null;
		String intro="";
		Date introduced = null;
		String discon="";
		Date discontinued = null;
		String id="";
		Long idCompany= null;
		Company company = new Company();
		Computer computer = new Computer();
		
		while(!conditionsOK) {
			System.out.println("Name : ");
			name= sc.nextLine();
		
			System.out.println("Date of introduction (YYYY-MM-DD) : ");
			intro = sc.nextLine();
			if (intro.length()>0) {
				try {
					introduced = Date.valueOf(intro);
				}catch (java.lang.IllegalArgumentException e)  {
					System.err.println("Enter the following format YYYY-MM-DD");
				}
			}
			
			System.out.println("Date of discontinuation (YYYY-MM-DD) : ");
			discon = sc.nextLine();
			if (discon.length()>0) {
				try {
					discontinued = Date.valueOf(discon);
				}catch (java.lang.IllegalArgumentException e)  {
					System.err.println("Enter the following format YYYY-MM-DD");
				}
			}
			
			System.out.println("ID of the company : ");
			try{
				id = sc.nextLine();
				if (id.length()>0) {
					idCompany = Long.parseLong(id);
					company = companyService.findbyID(idCompany);
				}
			}catch (java.lang.NumberFormatException e) {
				System.err.println("Enter a number");
				company = null;
			}
			
			computer = new Computer(name,introduced,discontinued, company);
			conditionsOK=computerService.allowCreation(computer);
		}	
		return computer;
	}
	
	/*public void backToMenu() {
		boolean quit=false; 
		System.out.println("Enter 'q' to quit");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		String choice = sc.nextLine();
		if (choice.equals("q")) {
			quit = true; 
		}else {
			this.start();
		}
	} */

}
