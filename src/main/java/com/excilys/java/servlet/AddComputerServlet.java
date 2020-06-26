package com.excilys.java.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.DTO.CompanyDTO;
import com.excilys.java.DTO.ComputerDTO;
import com.excilys.java.mapper.ComputerMapper;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.service.CompanyService;
import com.excilys.java.service.ComputerService;


@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
	private static CompanyService companyService = CompanyService.getInstance(); 
	private static ComputerService computerService = ComputerService.getInstance(); 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = companyService.listCompanies();
		request.setAttribute("listCompanies", companies);
		request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDTO = new ComputerDTO(); 
		CompanyDTO companyDTO = new CompanyDTO(); 
		Computer computer = new Computer();
		boolean conditionsOK = false;
		
		//while(!conditionsOK) {
		computerDTO.setName(request.getParameter("computerName"));
		computerDTO.setIntroduced(request.getParameter("introduced"));
		computerDTO.setDiscontinued(request.getParameter("discontinued"));
		
		
		System.out.println(computerDTO);
		System.out.println(request.getParameter("companyId"));
		
		String idCompany = request.getParameter("companyId");
/*		if (idCompany!=0) {
			companyDTO.setIdCompany(request.getParameter("companyId"));
			company = companyService.findbyID(idCompany);
		}
		*/
		
		//companyDTO.setName(request.getParameter("nameCompany"));
		
		//computerDTO.setManufacturer(companyDTO);
	
		//computer = ComputerMapper.mapDTO(computerDTO);
		//conditionsOK = computerService.allowCreation(computer);
		//}
		
		//computerService.createComputer(computer);
		//logger.info("Computer created with success !");
		
		doGet(request, response);
	}
	

}
