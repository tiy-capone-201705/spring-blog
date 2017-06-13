package mil.deadly.blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ramblings")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Size(min=1)
	private String title;

	@NotNull
	@Size(min=1)
	private String content;
	
	@NotNull
	private Date createdOn;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments;
	
	public Post() {
		createdOn = new Date();
		comments = new ArrayList<Comment>();
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

}

















