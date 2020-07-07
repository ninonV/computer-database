package com.excilys.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.excilys.java.DTO.ComputerDTO;
import com.excilys.java.DTO.mapper.ComputerMapper;
import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;
import com.excilys.java.service.ComputerService;

/**
 * Servlet class for display list of computers
 * @author ninon
 */
@WebServlet("/ListComputer")
public class ListComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2L;
	private static Logger logger = LoggerFactory.getLogger(ListComputerServlet.class);
	
	@Autowired
	private Page page;
	@Autowired
	private ComputerService computerService;  
	
	public void init(ServletConfig config) {
	    try {
			super.init(config);
		} catch (ServletException e) {
			logger.error("Error during initalization in ListComputer ",e);
		}
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	  }

	@Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Computer> computers = new ArrayList<Computer>();
			List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		
			String inputSearch = request.getParameter("search");
			String order = request.getParameter("order");
			int total = computerService.countComputer(inputSearch);
			int nbPages = page.getTotalPages(total);
			
			if(request.getParameter("pageNb")!=null) {
				int pageAsked = Integer.parseInt(request.getParameter("pageNb"));
				if (pageAsked>0 & pageAsked <= nbPages) {
					page.setCurrentPage(pageAsked);
					page.setFirstLine(page.getLinesPage() * (page.getCurrentPage()- 1) +1);
				}
			}
			
			if(request.getParameter("linesNb")!=null) {
				int LinesAsked = Integer.parseInt(request.getParameter("linesNb"));
				page.setLinesPage(LinesAsked);
				page.setCurrentPage(1);
				page.setFirstLine(1);
				nbPages = page.getTotalPages(total);
			}
		
			computers = computerService.getListPage(page,inputSearch,order);
			computers.stream().forEach(computer->computersDTO.add(ComputerMapper.mapComputertoDTO(computer)));
			
			request.setAttribute("totalComputers", total);
			request.setAttribute("currentPage", page.getCurrentPage());
			request.setAttribute("totalPages", nbPages);
			request.setAttribute("linesNb", page.getLinesPage());
			request.setAttribute("search", inputSearch);
			request.setAttribute("order", order);
			request.setAttribute("listComputers", computersDTO);
			request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
	   }
	 
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   if(request.getParameter("selection")!=null) {
			   String[] idToDelete = request.getParameter("selection").split(",");
			   for(int i=0;i<idToDelete.length;i++) {
				   Long id = Long.valueOf(idToDelete[i]);
				   computerService.deleteComputer(id);
				   logger.info("Computers deleted");
			   }
		   }
		   
		   doGet(request, response);
	       
	   }

}
