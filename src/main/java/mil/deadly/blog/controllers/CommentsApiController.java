package mil.deadly.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.deadly.blog.domain.Comment;
import mil.deadly.blog.domain.PeopleRepository;
import mil.deadly.blog.domain.Post;
import mil.deadly.blog.domain.PostsRepository;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentsApiController {
	
	private PostsRepository repo;
	private PeopleRepository peoples;
	
	public CommentsApiController(PostsRepository repository, PeopleRepository peoples) {
		repo = repository;
		this.peoples = peoples;
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@PathVariable Long postId, @RequestBody @Valid Comment comment, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors()
				.stream()
				.map(o -> o.getObjectName() + " " + o.getDefaultMessage())
				.collect(Collectors.toList());
			return new ResponseEntity<List<String>>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			Post post = repo.findOne(postId);
			comment.setAuthor(peoples.findOne(comment.getAuthor().getId()));
			post.addComment(comment);
			post = repo.save(post);
			return new ResponseEntity<Post>(post, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
