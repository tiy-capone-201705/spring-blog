package mil.deadly.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	
	private EntityManagerFactory factory;
	
	public TagApiController(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		EntityManager manager = factory.createEntityManager();
		
		try {
			List<Tag> tags = manager
					.createQuery("from Tag t order by t.value", Tag.class)
					.getResultList();
			return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		EntityManager manager = factory.createEntityManager();
		
		try {
			Tag tag = manager
					.createQuery("from Tag t where t.id = " + id, Tag.class)
					.getSingleResult();
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
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		try
		{
			tag = manager.merge(tag);
			manager.getTransaction().commit();
			return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}



















