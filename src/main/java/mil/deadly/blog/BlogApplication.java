package mil.deadly.blog;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mil.deadly.blog.domain.Comment;
import mil.deadly.blog.domain.PeopleRepository;
import mil.deadly.blog.domain.Person;
import mil.deadly.blog.domain.Post;
import mil.deadly.blog.domain.PostsRepository;
import mil.deadly.blog.domain.Tag;
import mil.deadly.blog.domain.TagsRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PeopleRepository people;
	
	@Autowired
	private PostsRepository posts;
	
	@Autowired
	private TagsRepository tags;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("am i inside???");
		people.deleteAll();
		posts.deleteAll();
		tags.deleteAll();
		
		Person mikePiazza = new Person("Mike", "Piazza", "mike.piazza@mets.com");
		Person chipperJones = new Person("Chipper", "Jones", "chipper.jones@braves.com");
		
		Comment comment1 = new Comment("what do you think of today?", mikePiazza);
		Comment comment2 = new Comment("long day, my friend", chipperJones);
		Comment comment3 = new Comment("yea agreed. i'm tired but wanna get pizza still", mikePiazza);
		Comment comment4 = new Comment("your name is like pizza dude", chipperJones);
		
		Post myLongDayPost = new Post("My Long Day");
		
		myLongDayPost.addComment(comment1);
		myLongDayPost.addComment(comment2);
		myLongDayPost.addComment(comment3);
		myLongDayPost.addComment(comment4);
		
		myLongDayPost.setAuthor(mikePiazza);
		
//		myLongDayPost.addTag(new Tag("baseball"));
//		myLongDayPost.addTag(new Tag("nl east"));
//		myLongDayPost.addTag(new Tag("mets"));
//		myLongDayPost.addTag(new Tag("braves"));
//		myLongDayPost.addTag(new Tag("mlb"));
		
//		tags, persons, comment, posts
		tags.save(new Tag("baseball"));
		tags.save(new Tag("nl east"));
		tags.save(new Tag("mets"));
		tags.save(new Tag("braves"));
		tags.save(new Tag("mlb"));
		
		people.save(mikePiazza);
		people.save(chipperJones);
		
		posts.save(myLongDayPost);
		
		System.out.println("lets findByTitle: ");
		System.out.println(posts.findByTitle("My Long Day"));
	}
}
















