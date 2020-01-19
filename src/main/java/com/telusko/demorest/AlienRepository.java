package com.telusko.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository
{

	Connection con = null;

	public AlienRepository()
	{
		//		System.out.println("getAlien called...");
		//		Alien a1 = new Alien();
		//		a1.setId(101);
		//		a1.setName("Navin");
		//		a1.setPoints(60);
		//
		//		Alien a2 = new Alien();
		//		a2.setId(102);
		//		a2.setName("Arati");
		//		a2.setPoints(70);
		//
		//		this.aliens.add(a1);
		//		this.aliens.add(a2);

		String url = "jdbc:mysql://localhost:3306/restdb";
		String username = "root";
		String password = "xsw23456";

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection(url, username, password);
		} catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public List<Alien> getAliens()
	{
		System.out.println("Get all Request");
		List<Alien> aliens = new ArrayList<>();

		String sql = "select * from alien";

		try
		{
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

				aliens.add(a);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return aliens;
	}

	public Alien getAlien(int id)
	{
		System.out.println("Get Specific Request, id = " + id);
		String sql = "select * from alien where id =" + id;

		Alien a = new Alien();

		try
		{
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery(sql);


			if (rs.next())
			{

				a.setId(rs.getInt("Id"));
				a.setName(rs.getString("name"));
				a.setPoints(rs.getInt("points"));
			}


			System.out.println("ID Found " + a.getId());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return a;
	}

	public void create(Alien a1)
	{
		System.out.println("Create Request");
		String sql = "insert into alien values(?,?,?)";
		try
		{
			PreparedStatement pst = this.con.prepareStatement(sql);
			pst.setInt(1,  a1.getId());
			pst.setString(2,  a1.getName());
			pst.setInt(3,  a1.getPoints());
			pst.executeUpdate();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public void update(Alien a1)
	{
		System.out.println("Update Request");
		String sql = "update alien set name=?, points=? where id=?";
		try
		{
			PreparedStatement pst = this.con.prepareStatement(sql);
			pst.setString(1,  a1.getName());
			pst.setInt(2,  a1.getPoints());
			pst.setInt(3,  a1.getId());
			pst.executeUpdate();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public void delete(int id)
	{
		System.out.println("Delete Request");
		String sql = "delete from alien where id=?";
		try
		{
			PreparedStatement pst = this.con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


}
