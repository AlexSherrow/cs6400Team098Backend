package com.BuzzCars.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.BuzzCars.entity.HelloWorld;
import com.BuzzCars.entity.User;

@Path("user")
public class CustomerController {

	public CustomerController() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAll")
	public Response getAllUsers() {

		String username = "dbmasteruser";
		String password = "R4.?:XM2H|$-aB1Bmj7xKoG!jFD8>RJG";
		String url = "jdbc:mysql://ls-a796dc16d733095b4067e02434fdca87bc364277.cpmqvpdxfdzt.us-east-1.rds.amazonaws.com/cs6400";
		String query = "select * from user";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String data = null;
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			User user1 = new User("hi", "hi", "hi", "hi");
			User user2 = new User("hi2", "hi", "hi", "hi");
			List<User> users = new ArrayList();
			users.add(user1);
			users.add(user2);
			con.close();
			HelloWorld helloWorld = new HelloWorld("Hello!");
			return Response.status(200).entity(users).build();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("hi2")
	public Response helloWorld2() {

		String username = "dbmasteruser";
		String password = "R4.?:XM2H|$-aB1Bmj7xKoG!jFD8>RJG";
		String url = "jdbc:mysql://ls-a796dc16d733095b4067e02434fdca87bc364277.cpmqvpdxfdzt.us-east-1.rds.amazonaws.com/cs6400";
		String query = "select * from user";
		System.out.println("HI");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String data = null;
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				data = "";
				for (int i = 1; i <= 4; i++)
					data += result.getString(i) + ", ";
				System.out.println(data);
			}
			con.close();
			HelloWorld helloWorld = new HelloWorld("Hello!");
			return Response.ok(data).build();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
