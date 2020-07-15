package com.excilys.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.java.DTO.CompanyDTO;
import com.excilys.java.DTO.ComputerDTO;
import com.excilys.java.DTO.mapper.CompanyMapper;
import com.excilys.java.DTO.mapper.ComputerMapper;
import com.excilys.java.exception.ComputerDateException;
import com.excilys.java.exception.ComputerNameException;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.service.CompanyService;
import com.excilys.java.service.ComputerService;
import com.excilys.java.validator.ValidatorComputer;

@Controller
public class EditComputerController {

	private static Logger logger = LoggerFactory.getLogger(EditComputerController.class);

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@GetMapping("/EditComputer")
	public ModelAndView computerInfo(ComputerDTO computerDTO) {
		ModelAndView modelView = new ModelAndView("editComputer");
		Computer computer = new Computer();

		if (computerDTO.getId() != null) {
			computer = computerService.findbyID(Long.parseLong(computerDTO.getId()));
		}

		if (computer != null) {
			computerDTO = ComputerMapper.mapComputertoDTO(computer);
		} else {
			logger.info("The computer does not exist");
		}

		List<Company> companies = companyService.listCompanies();
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		companies.stream().forEach(company -> companiesDTO.add(CompanyMapper.mapCompanyToDTO(company)));

		modelView.addObject("listCompanies", companiesDTO);
		modelView.addObject("computer", computerDTO);
		return modelView;
	}

	@PostMapping("/EditComputer")
	public RedirectView addComputer(ComputerDTO computerDTO, CompanyDTO companyDTO) {
		ModelAndView modelView = new ModelAndView("editComputer");
		
		Map<String, String> errors = new HashMap<String, String>();
		String resultCreation;
		Computer computer = new Computer();
	
		try {
			ValidatorComputer.validatorName(computerDTO.getName());
			ValidatorComputer.validatorDate(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
		}catch ( ComputerNameException e ) {
            errors.put( "computerName", e.getMessage() );
        }catch ( ComputerDateException e ) {
            errors.put( "discontinued", e.getMessage());
        }
		
		if (errors.isEmpty()) {
			computer = ComputerMapper.mapDtoToComputer(computerDTO);
			computerService.createComputer(computer);
			resultCreation = "Computer updated with success.";
			logger.info("Computer updated with success.");
			
			modelView.addObject("resultCreation", resultCreation);
			return new RedirectView("/ListComputer");
		}else {
			resultCreation = "Impossible to update this computer.";
			logger.info("Impossible to update this computer.");
			
			modelView.addObject("errors", errors );
			modelView.addObject("resultCreation", resultCreation);
			return new RedirectView("redirect:/editComputer?computer=" + computerDTO);
		}
	}
}
