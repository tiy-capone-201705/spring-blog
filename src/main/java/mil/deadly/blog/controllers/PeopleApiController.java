package mil.deadly.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.deadly.blog.domain.PeopleRepository;
import mil.deadly.blog.domain.Person;

@RestController
@RequestMapping("/api/people")
public class PeopleApiController {

	private PeopleRepository repo;
	
	public PeopleApiController(PeopleRepository repository) {
		repo = repository;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			return new ResponseEntity<List<Person>>(repo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{myId}")
	public ResponseEntity<?> getByFirstName(@PathVariable("myId") String id) {
		try {
			return new ResponseEntity<List<Person>>(repo.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody @Valid Person person, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors()
				.stream()
				.map(o -> o.getObjectName() + " " + o.getDefaultMessage())
				.collect(Collectors.toList());
			return new ResponseEntity<List<String>>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			person = repo.save(person);
			return new ResponseEntity<Person>(person, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}




















