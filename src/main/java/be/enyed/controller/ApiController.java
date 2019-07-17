package be.enyed.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.enyed.model.Person;
import be.enyed.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping(path = {"/api" /* ,"/hr","/applic" */})
@Api(tags = {"persons","people"})
@SwaggerDefinition(
		basePath = "/api/",
		tags = { 
			@Tag(description = "All the persons",name = "persons"), 
			@Tag(description = "All the people",name = "people")
		}
	)
public class ApiController {
	private final PeopleService peopleService;
	
	public ApiController(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

	@GetMapping(value = "/people")
	@ApiResponses(value = {
			@ApiResponse(message = "I look at all the lonely people -The Beatles-", code = 200)
	})
	public ResponseEntity<Set<Person>> allPeople() {
		return ResponseEntity.ok(peopleService.findAll());
	}
	
	@GetMapping(value = "/people/{id}")
	@ApiParam(defaultValue = "1" ,allowableValues = "range[1, infinity]" , example = "3" )
	@ApiResponses(value = {
			@ApiResponse(message = "Person with ID or if not found an 'unknown' person", code = 200)
	})
	public ResponseEntity<Person> personById(@PathVariable Long id) {
		return ResponseEntity.ok(peopleService.findOne(id));
	}
	
	@PostMapping( path = "/person" , consumes =  "application/json")
	@ApiParam(
		name = "body",
		examples = @Example( @ExampleProperty(mediaType =  "application/json", value = "{id: 3}")),
		allowEmptyValue = false, 
		allowMultiple = false,
		required = true
			)
	public ResponseEntity<Person> person(@RequestBody Map<String, Long> body) {
		return ResponseEntity.ok(peopleService.findOne(body.get("id")));
	}

}
