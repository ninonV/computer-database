package com.excilys.java.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.java.DTO.ComputerDTO;
import com.excilys.java.DTO.DashboardDTO;
import com.excilys.java.DTO.mapper.ComputerMapper;
import com.excilys.java.model.Computer;
import com.excilys.java.model.Pagination;
import com.excilys.java.service.ComputerService;


@Controller
@RequestMapping("/ListComputer")
public class ListComputerController {
	
	private static Logger logger = LoggerFactory.getLogger(ListComputerController.class);
	
	@Autowired
	private ComputerService computerService; 
	
	@GetMapping
	public ModelAndView listComputer(DashboardDTO dashboardDTO) { 
        ModelAndView modelView = new ModelAndView("dashboard"); 
        
        Pagination page = new Pagination();
        
        if(dashboardDTO.getLinesNb()!=null) {
			int linesNb= Integer.parseInt(dashboardDTO.getLinesNb());
			page.setLinesPage(linesNb);
		}
        

		int total = computerService.countComputer(dashboardDTO.getSearch());
		int nbPages = page.getTotalPages(total);
		
		if(dashboardDTO.getPageNb()!=null) {
			int pageAsked = Integer.parseInt(dashboardDTO.getPageNb());
			if (pageAsked>0 & pageAsked <= nbPages) {
				page.setCurrentPage(pageAsked);
			}
		}
		
		page.setFirstLine(page.calculFirstLine());

		List<Computer> computers = computerService.getListPage(page,dashboardDTO.getSearch(),dashboardDTO.getOrder());
		List<ComputerDTO> computersDTO = computers.stream().map(computer->ComputerMapper.mapComputertoDTO(computer)).collect(Collectors.toList());
        
        modelView.addObject("totalComputers", total); 
        modelView.addObject("currentPage", page.getCurrentPage());
        modelView.addObject("totalPages", nbPages);
        modelView.addObject("linesNb", page.getLinesPage());
        modelView.addObject("search", dashboardDTO.getSearch());
        modelView.addObject("order", dashboardDTO.getOrder());
        modelView.addObject("listComputers", computersDTO);
        
        return modelView; 
    } 
	
	@PostMapping
	 public ModelAndView deleteComputer(@RequestParam String selection){
		ModelAndView modelView = new ModelAndView("redirect:/ListComputer"); 
		
		 if(selection!=null) {
			 String[] idToDelete = selection.split(",");
			 for(int i=0;i<idToDelete.length;i++) {
				   Long id = Long.valueOf(idToDelete[i]);
				   computerService.deleteComputer(id);
				   logger.info("Computers deleted");	
			 }
		   }
		return modelView;
	}
}
