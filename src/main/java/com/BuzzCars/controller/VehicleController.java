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

import com.BuzzCars.entity.Vehicle;

@Path("vehicle")
public class VehicleController {

	public VehicleController() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAll")
	public Response getAll() {

		String username = "dbmasteruser";
		String password = "R4.?:XM2H|$-aB1Bmj7xKoG!jFD8>RJG";
		String url = "jdbc:mysql://ls-a796dc16d733095b4067e02434fdca87bc364277.cpmqvpdxfdzt.us-east-1.rds.amazonaws.com/cs6400";
		String query = "select * from vehicle";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String data = null;
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			List<Vehicle> output = new ArrayList();
			while (rs.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setVin(rs.getString(1));
				vehicle.setChassisType(rs.getString(2));
				vehicle.setDescription(rs.getString(3));
				vehicle.setFuelType(rs.getString(4));
				vehicle.setManufacturer(rs.getString(5));
				vehicle.setMileage(rs.getInt(6));
				vehicle.setModelName(rs.getString(7));
				vehicle.setModelYear(rs.getInt(8));
				output.add(vehicle);
			}
			con.close();
			return Response.status(200).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(output)
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("save")
	@Consumes("application/json")
	public Response create(Vehicle input) {

		String username = "dbmasteruser";
		String password = "R4.?:XM2H|$-aB1Bmj7xKoG!jFD8>RJG";
		String url = "jdbc:mysql://ls-a796dc16d733095b4067e02434fdca87bc364277.cpmqvpdxfdzt.us-east-1.rds.amazonaws.com/cs6400";
		String query = "insert into vehicle values ('" + input.getVin() + "', '" + input.getModelName() + "', '"
				+ input.getDescription() + "', '" + input.getManufacturer() + "');";
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
