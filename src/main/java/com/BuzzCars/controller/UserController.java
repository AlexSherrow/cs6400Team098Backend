package com.BuzzCars.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.BuzzCars.entity.User;

@Path("user")
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAll")
	public Response getAll() {

		String username = "dbmasteruser";
		String password = "R4.?:XM2H|$-aB1Bmj7xKoG!jFD8>RJG";
		String url = "jdbc:mysql://ls-a796dc16d733095b4067e02434fdca87bc364277.cpmqvpdxfdzt.us-east-1.rds.amazonaws.com/cs6400";
		String query = "select * from user";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			List<User> users = new ArrayList();
			while (resultSet.next()) {
				User user = new User();
				user.setUserName(resultSet.getString(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				users.add(user);
			}
			con.close();
			return Response.status(200).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(users)
					.build();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("save")
	@Consumes("application/json")
	public Response create(User input) {

		String username = "dbmasteruser";
		String password = "R4.?:XM2H|$-aB1Bmj7xKoG!jFD8>RJG";
		String url = "jdbc:mysql://ls-a796dc16d733095b4067e02434fdca87bc364277.cpmqvpdxfdzt.us-east-1.rds.amazonaws.com/cs6400";
		String query = "insert into user values ('" + input.getUserName() + "', '" + input.getPassword() + "', '"
				+ input.getFirstName() + "', '" + input.getLastName() + "');";
		System.out.println(query);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
			con.close();
			String result = "Product created : " + input;
			return Response.status(201).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(result)
					.build();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
