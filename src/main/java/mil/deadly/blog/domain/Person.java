package mil.deadly.blog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="people")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private String id;
	
	@NotNull
	@Size(min=3, max=40)
	@Email
	private String email;
	
	@NotEmpty
	@NotNull
	private String firstName;

	@NotEmpty
	@NotNull
	private String lastName;
	
	@OneToMany(mappedBy="author")
	private List<Post> posts;
	
	@OneToMany(mappedBy="author")
	private List<Comment> comments;
	
	@Version
	private Integer version;
	
	public Person() {
		setPosts(new ArrayList<Post>());
		comments = new ArrayList<Comment>();
	}
	
	public Person(String firstName, String lastName, String email) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
