package com.excilys.java.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.excilys.java.exception.ComputerException;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.service.CompanyService;
import com.excilys.java.service.ComputerService;
import com.excilys.java.validator.ValidatorComputer;

/**
 * Servlet class for add a computer
 * @author ninon
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2L;
	private static Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService; 
	
	@Override
	public void init(ServletConfig config){
	    try {
			super.init(config);
		} catch (ServletException e) {
			logger.error("Error during initalization in AddComputer ",e);
		}
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	  }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> companies = companyService.listCompanies();
		List<CompanyDTO> companiesDTO =new ArrayList<CompanyDTO>();
		
		companies.stream().forEach(company->companiesDTO.add(CompanyMapper.mapCompanyToDTO(company)));
	
		request.setAttribute("listCompanies", companiesDTO);
		request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errors = new HashMap<String, String>();
		
		String resultCreation;
		ComputerDTO computerDTO = new ComputerDTO(); 
		CompanyDTO companyDTO = new CompanyDTO(); 
		Computer computer = new Computer();
	
		try {
			ValidatorComputer.validatorName(request.getParameter("computerName"));
			computerDTO.setName(request.getParameter("computerName"));
		}catch ( ComputerException e ) {
            errors.put( "computerName", e.getMessage() );
        }
		
		try {
			ValidatorComputer.validatorDate(request.getParameter("introduced"), request.getParameter("discontinued"));
			computerDTO.setIntroduced(request.getParameter("introduced"));
			computerDTO.setDiscontinued(request.getParameter("discontinued"));
		}catch ( ComputerException e ) {
            errors.put( "discontinued", e.getMessage());
        }
		
		if (errors.isEmpty()) {
			companyDTO.setId(request.getParameter("companyId"));
			computerDTO.setCompany(companyDTO);
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
