package mil.deadly.blog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import mil.deadly.blog.domain.Comment;
import mil.deadly.blog.domain.PeopleRepository;
import mil.deadly.blog.domain.Person;
import mil.deadly.blog.domain.Post;
import mil.deadly.blog.domain.PostsRepository;
import mil.deadly.blog.domain.Tag;
import mil.deadly.blog.domain.TagsRepository;

@Configuration //configuration will hook this up automagically using PostConstruct
public class SeedData {

	@Autowired
	private PeopleRepository people;
	
	@Autowired
	private PostsRepository posts;
	
	@Autowired
	private TagsRepository tags;
	
	//TODO
	// need to add PostConstruct (think of it as 'after, we need to construct')
	// additionally i need to add the .save() stuff... 
	// NOTE: need order to happen in certain way for saving: tags, people, posts because of dependencies each of them share on each other
	@PostConstruct
	public void seed() {
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
		
		Tag tag1 = new Tag("baseball");
		Tag tag2 = new Tag("nl east");
		Tag tag3 = new Tag("mets");
		Tag tag4 = new Tag("braves");
		Tag tag5 = new Tag("mlb");
		
		tags.save(tag1);
		tags.save(tag2);
		tags.save(tag3);
		tags.save(tag4);
		tags.save(tag5);
		
		myLongDayPost.addTag(tag1);
		myLongDayPost.addTag(tag2);
		myLongDayPost.addTag(tag3);
		myLongDayPost.addTag(tag4);
		myLongDayPost.addTag(tag5);
		
		
		people.save(mikePiazza);
		people.save(chipperJones);
		
		posts.save(myLongDayPost);
	}
}
