package com.douglas.userapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/source")
public class SourceController {

	@ApiOperation(value = "Redirect to code", notes = "Redirects to the github link with the code")
	@GetMapping
	public void source(HttpServletResponse response) {
		try {
			response.sendRedirect("https://github.com/douglaslbittencourt/user-api");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
