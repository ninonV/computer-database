package com.excilys.java.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;
import com.excilys.java.service.CompanyService;
import com.excilys.java.service.ComputerService;

/**
 *  Class doing the relation with the user  
 *  @author ninonV
 *  **/

public class UserInterface {
	
	private static ComputerService computerService = ComputerService.getInstance();  
	private static CompanyService companyService = CompanyService.getInstance();
	
	/**
	 * Main menu that calls the different functions 
	 */
	
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
				int choiceInt = Integer.parseInt(choice);
				
				switch(choiceInt) {
					case 1:
						pagesComputers();
						break;
						
					case 2: 
						pagesCompanies();
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
	
	/**
	 * Get the id of the computer
	 * @return Long id
	 */
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
	
	/**
	 * Create a new computer or enter the new informations IF the conditions are checked
	 * @return Computer
	 */
	public Computer infoComputer() {
		Scanner sc = new Scanner(System.in);
		Boolean conditionsOK= false;
		String name=null;
		String intro="";
		LocalDate introduced = null;
		String discon="";
		LocalDate discontinued = null;
		String id="";
		Long idCompany= null;
		Company company = new Company();
		Computer computer = new Computer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		
		while(!conditionsOK) {
			System.out.println("Name : ");
			name= sc.nextLine();
		
			System.out.println("Date of introduction (YYYY-MM-DD) : ");
			intro = sc.nextLine();
			if (intro.length()>0) {
				try {
					introduced = LocalDate.parse(intro, formatter);
				}catch (java.time.format.DateTimeParseException e)  {
					System.err.println("Enter the following format YYYY-MM-DD");
				}
			}
			
			System.out.println("Date of discontinuation (YYYY-MM-DD) : ");
			discon = sc.nextLine();
			if (discon.length()>0) {
				try {
					discontinued = LocalDate.parse(discon, formatter);
				}catch (java.time.format.DateTimeParseException e)  {
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
				company= null;
			}
			
			computer = new Computer(name,introduced,discontinued, company);
			conditionsOK=computerService.allowCreation(computer);
		}	
		return computer;
	}
	
	/**
	 * Method that show the pages related to the computers
	 */
	
	public void pagesComputers(){
		Page page = new Page();
		int total = computerService.countComputer();
		int nbPages = page.getTotalPages(total);
		boolean quitPage = false; 
		
		while (!quitPage) {
			computerService.getListPage(page);
			quitPage = menuPage(page, nbPages);
		}
	}
	
	/**
	 * Method that show the pages related to the companies
	 */
	
	
	public void pagesCompanies() {
		Page page = new Page();
		
		int total = companyService.countCompany();
		int nbPages = page.getTotalPages(total);
		boolean quitPage = false; 
		
		while (!quitPage) {
			companyService.getListPage(page);
			quitPage = menuPage(page, nbPages);
		}
	}

	/**
	 * Show the menu of a page and calls the function of the pages
	 * @param Page page, int nbPages
	 * @return boolean
	 */
	public boolean menuPage(Page page, int nbPages) {
		boolean quitPage = false; 
		
		System.out.println("Page " + page.getCurrentPage() + "/"+ nbPages );
		System.out.println("n : Next page   -   p : Previous page  -  s : Select page   -   q : Quit");
			
		try {
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
				
			switch(choice) {
				case "n":
					if(page.getCurrentPage()==nbPages) {
						System.out.println("\n No more page ! \n");
					}else {
						page.nextPage();
					}
					break;
						
				case "p": 
					if(page.getCurrentPage()==1) {
						System.out.println("\n No previous page ! \n");
					}else {
						page.previousPage();
					}
					break; 
						
				case "s": 
					System.out.println("Number of the page : ");
					String nb= sc.nextLine();
					int newPage=page.getCurrentPage();
					try{
						newPage = Integer.parseInt(nb);
						if (newPage>0 && newPage<=nbPages) {
							page.setCurrentPage(newPage);
							page.setFirstLine(page.getLinesPage() * (page.getCurrentPage()- 1) +1);
						}else {
							System.out.println("Number incorrect");
						}
					}catch (java.lang.NumberFormatException e) {
						System.err.println("Enter a number");
					}
					break;
						
				case "q":
					quitPage = true; 
					break; 
				default: 
					System.out.println("Enter one of the previous instructions");
			}
		}catch (java.lang.NumberFormatException e) {
			System.err.println("Enter a letter or a page followed by a number");
		}
		return quitPage;
	}
	
}
