package com.excilys.java.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;
import com.excilys.java.service.ComputerService;

/**
 * Servlet class for display list of computersS
 * @author ninon
 *
 */
 
@WebServlet("/ListComputer")
public class ListComputerServlet extends HttpServlet {
	
	private static Page page = new Page();
	private static final long serialVersionUID = 1L;
	private static ComputerService computerService = ComputerService.getInstance();  

	public ListComputerServlet() {
		super();
	}

	@Override
	   protected void doGet(HttpServletRequest request,
	           HttpServletResponse response) throws ServletException, IOException {
		
			int total = computerService.countComputer();
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
				nbPages = page.getTotalPages(total);
			}
		
			List<Computer> computers = computerService.getListPage(page);
			
			request.setAttribute("totalComputers", total);
			request.setAttribute("currentPage", page.getCurrentPage());
			request.setAttribute("totalPages", nbPages);
			request.setAttribute("listComputers", computers);
			request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
	   }
	 
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       doGet(request, response);
	       
	   }

}
