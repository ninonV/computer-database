package com.excilys.java.model;

public class Page {
	
	/**
	 * Class representing a page
	 * @author ninonV
	 *
	 */
	
	private int firstLine;
	private int currentPage; 
	private int linesPage;
	private static final int DEFAULT_PAGE = 1;
	private static final int MAX_LINES = 20;
	
	public Page() {
		this.firstLine = DEFAULT_PAGE;
		this.currentPage = DEFAULT_PAGE;
		this.linesPage = MAX_LINES;
	}

	/**
	 * Go to the next page
	 */
	public void nextPage() {
		currentPage ++; 
		firstLine+=MAX_LINES;
	}
	
	/**
	 * Go to the previous page;
	 */
	public void previousPage() {
		currentPage --;
		firstLine-=MAX_LINES;
	}
	
	public int getTotalPages(int total) {
	    return (int) Math.ceil((double) total/ linesPage);
	}
	
	public int getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(int firstLine) {
		this.firstLine = firstLine;
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
