package mil.deadly.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.deadly.blog.domain.PeopleRepository;
import mil.deadly.blog.domain.Person;
import mil.deadly.blog.domain.Post;
import mil.deadly.blog.domain.PostsRepository;
import mil.deadly.blog.domain.Tag;
import mil.deadly.blog.domain.TagsRepository;

@RestController
@RequestMapping("/api/posts")
public class PostsApiController {
	
	private PostsRepository repo;
	private PeopleRepository peoples;
	private TagsRepository tags;
	
	public PostsApiController(PostsRepository repository, PeopleRepository peoples, TagsRepository tags) {
		repo = repository;
		this.peoples = peoples;
		this.tags = tags;
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			return new ResponseEntity<List<Post>>(repo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody @Valid Post post, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors()
				.stream()
				.map(o -> o.getObjectName() + " " + o.getDefaultMessage())
				.collect(Collectors.toList());
			return new ResponseEntity<List<String>>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			post = repo.save(post);
			return new ResponseEntity<Post>(post, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
