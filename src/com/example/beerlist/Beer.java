package com.example.beerlist;

public class Beer {
	private String title;
	private String description;
	
	public Beer(String title, String description)
	{
		this.title = title;
		this.description = description;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDesc(String desc)
	{
		this.description = desc;
	}
	
	public String toString()
	{
		return title;
	}
}












