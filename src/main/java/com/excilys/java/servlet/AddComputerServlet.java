package com.excilys.java.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
import com.excilys.java.validator.ValidatorComputer;


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
		String resultCreation;
        Map<String, String> errors = new HashMap<String, String>();
		
		ComputerDTO computerDTO = new ComputerDTO(); 
		CompanyDTO companyDTO = new CompanyDTO(); 
		Computer computer = new Computer();
	
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String idCompany = request.getParameter("companyId");
	
		try {
			ValidatorComputer.validatorName(name);
			computerDTO.setName(name);
		}catch ( Exception e ) {
            errors.put( "computerName", e.getMessage() );
        }
		
		try {
			ValidatorComputer.validatorDate(introduced, discontinued);
			computerDTO.setIntroduced(introduced);
			computerDTO.setDiscontinued(discontinued);
		}catch ( Exception e ) {
            errors.put( "discontinued", e.getMessage());
        }
		
		if (errors.isEmpty()) {
			companyDTO.setIdCompany(idCompany);
			computerDTO.setManufacturer(companyDTO);
			computer = ComputerMapper.mapDtoToComputer(computerDTO);
			computerService.createComputer(computer);
			resultCreation = "Computer added with success.";
			logger.info("Computer added with success.");
		}else {
			resultCreation = "Impossible to add this computer.";
			logger.info("Impossible to add this computer.");
		}
		
		request.setAttribute( "errors", errors );
        request.setAttribute( "resultCreation", resultCreation );

		doGet(request, response);
	}

}
