package com.excilys.java.model;

public class Page {
	
	/**
	 * Class representing a page
	 * @author ninonV
	 *
	 */
	
	 private int currentPage; 
	 private int linesPage; 
	 private static final int DEFAULT_PAGE = 1;
	 private static final int MAX_LINES = 15;
	
	public Page() {
		this.currentPage = DEFAULT_PAGE;
		this.linesPage = MAX_LINES;
	}

	public void nextPage() {
		currentPage ++; 
	}
	
	public void previousPage() {
		if(currentPage>DEFAULT_PAGE) {
			currentPage --; 
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLinesPage() {
		return linesPage;
	}

	public void setLinesPage(int linesPage) {
		this.linesPage = linesPage;
	}
	
}
