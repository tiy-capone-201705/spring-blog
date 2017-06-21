package mil.deadly.blog.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mil.deadly.blog.domain.Tag;

@RestController
@RequestMapping("/api/tags")
public class TagApiController {
	
	
	public TagApiController() {
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		
		try {
			List<Tag> tags = new ArrayList<Tag>(); 
			return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		
		try {
			Tag tag = new Tag("");
			return new ResponseEntity<Tag>(tag, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody @Valid Tag tag, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors()
				.stream()
				.map(o -> o.getObjectName() + " " + o.getDefaultMessage())
				.collect(Collectors.toList());
			return new ResponseEntity<List<String>>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		try
		{
			return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}



















