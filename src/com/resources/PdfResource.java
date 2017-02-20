package com.resources;

public class PdfResource implements Resource{
	private String path;
	private int page;
	
	public PdfResource (String path){
		this.path=path;
	}
	
	public PdfResource (String path, int page){
		this(path);
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
