package com.excilys.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.java.DTO.CompanyDTO;
import com.excilys.java.DTO.ComputerDTO;
import com.excilys.java.DTO.mapper.CompanyMapper;
import com.excilys.java.DTO.mapper.ComputerMapper;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.service.CompanyService;
import com.excilys.java.service.ComputerService;
import com.excilys.java.validator.ValidatorComputer;

/**
 * Servlet class for edit a computer
 * @author ninon
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private static Logger logger = LoggerFactory.getLogger(EditComputerServlet.class);

	@Autowired
	private CompanyService companyService; 
	@Autowired
	private ComputerService computerService;  
	
	@Override
	public void init(ServletConfig config) {
	    try {
			super.init(config);
		} catch (ServletException e) {
			logger.error("Error during initalization in EditComputer ",e);
		}
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Computer computer = new Computer();
		ComputerDTO computerDTO = new ComputerDTO();
		String id = request.getParameter("computerId");
		if (id !=null ) {
			computer = computerService.findbyID(Long.parseLong(id));
		}
		
		if (computer!=null) {
			computerDTO = ComputerMapper.mapComputertoDTO(computer);
		}else {
			logger.info("The computer does not exist");
		}
		
		
		List<Company> companies = companyService.listCompanies();
		List<CompanyDTO> companiesDTO =new ArrayList<CompanyDTO>();
		companies.stream().forEach(company->companiesDTO.add(CompanyMapper.mapCompanyToDTO(company)));
	
		request.setAttribute("listCompanies", companiesDTO);
		request.setAttribute("computer", computerDTO);
		request.getRequestDispatcher("/views/editComputer.jsp").forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultCreation;
        Map<String, String> errors = new HashMap<String, String>();
		
		ComputerDTO computerDTO = new ComputerDTO(); 
		CompanyDTO companyDTO = new CompanyDTO(); 
		Computer computer = new Computer();
	
		try {
			ValidatorComputer.validatorName(request.getParameter("computerName"));
			computerDTO.setName(request.getParameter("computerName"));
		}catch ( Exception e ) {
            errors.put( "computerName", e.getMessage() );
        }
		
		try {
			ValidatorComputer.validatorDate(request.getParameter("introduced"), request.getParameter("discontinued"));
			computerDTO.setIntroduced(request.getParameter("introduced"));
			computerDTO.setDiscontinued(request.getParameter("discontinued"));
		}catch ( Exception e ) {
            errors.put( "discontinued", e.getMessage());
        }
		
		if (errors.isEmpty()) {
			System.out.println(request.getParameter("companyId"));
			computerDTO.setId(request.getParameter("computerId"));
			
			companyDTO.setId(request.getParameter("companyId"));
			computerDTO.setCompany(companyDTO);
			System.out.println(computerDTO);
			computer = ComputerMapper.mapDtoToComputer(computerDTO);
			System.out.println(computer);
			computerService.updateComputer(computer);
			
			resultCreation = "Computer updated with success.";
			logger.info("Computer updated with success.");

		}else {
			resultCreation = "Impossible to update this computer.";
			logger.info("Impossible to update this computer.");
		}
		
		request.setAttribute( "errors", errors );
        request.setAttribute( "resultCreation", resultCreation );
		doGet(request, response);
	}

}
