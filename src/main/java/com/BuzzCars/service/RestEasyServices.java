package com.BuzzCars.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import com.BuzzCars.controller.UserController;
import com.BuzzCars.controller.VehicleController;

@ApplicationPath("/restapi")
public class RestEasyServices extends Application {

	private Set<Object> singletons = new HashSet();

	public RestEasyServices() {
		this.singletons.add(new UserController());
		this.singletons.add(new VehicleController());
		this.singletons.add(this.getCorsFilter());
	}

	private CorsFilter getCorsFilter() {
		CorsFilter result = new CorsFilter();
		result.getAllowedOrigins().add("http://localhost:3000");
		result.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
		result.setCorsMaxAge(86400);// Max in FF 86400=24h
									// https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Max-Age
		//
		return result;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
