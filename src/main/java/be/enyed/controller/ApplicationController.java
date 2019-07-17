package be.enyed.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import be.enyed.model.Person;
import be.enyed.service.PeopleService;

@Controller
public class ApplicationController {
	
	private PeopleService service;
	
	public ApplicationController(PeopleService service) {
		this.service = service;
	}

	@GetMapping(path = {"/hello","/"})
	@ResponseBody
	public String hello(@RequestParam(defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@GetMapping(path = {"/people"})
	@ResponseBody
	public Set<Person> people() {
		return service.findAll();
	}
	
	@GetMapping(path = {"/swagger","/api"})
	public String swagger() {
		return "redirect:/swagger-ui.html";
	}

}
