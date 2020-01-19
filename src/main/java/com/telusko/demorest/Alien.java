package com.telusko.demorest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alien
{
	private int id = 0;
	private String name;
	private int points;

	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getPoints()
	{
		return this.points;
	}
	public void setPoints(int points)
	{
		this.points = points;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Override
	public String toString()
	{
		return "Alien [id=" + this.id + ", name=" + this.name + ", points=" + this.points + "]";
	}

}
