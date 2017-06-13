package mil.deadly.blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String content;
	
	@NotNull
	private Date createdOn;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments;
	
	@NotNull
	@ManyToOne
	private Person author;
	
	@NotNull
	@ManyToMany
	private List<Tag> tags;
	
	public Post() {
		createdOn = new Date();
		comments = new ArrayList<Comment>();
		tags = new ArrayList<Tag>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
		comment.setPost(this);
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

}

















