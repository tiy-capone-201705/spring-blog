package mil.deadly.blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Post {
	@Id
	private String id;
	private String title;
	private String content;
	private Date createdOn;
	private List<Comment> comments;
	@DBRef //NOTE: DB reference as a Person author
	private Person author;
	@DBRef //NOTE: list of tags needs the DB reference as tags will be its own collection and referenced using $ref from within the Post collection
	private List<Tag> tags;
	
	public Post() {
		comments = new ArrayList<Comment>();
		tags = new ArrayList<Tag>();
		createdOn = new Date();
	}
	
	public Post(String title) {
		this();
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "[" + id + "] " + title;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}

















