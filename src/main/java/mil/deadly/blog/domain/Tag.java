package mil.deadly.blog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private String value;
	
	@ManyToMany(mappedBy="tags", cascade=CascadeType.ALL)
	private List<Post> posts;
	
	@Version
	private Integer version;
	
	public Tag() {
		posts = new ArrayList<Post>();
	}
	
	public Tag(String value) {
		this();
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public void addPost(Post post) {
		this.posts.add(post);
	}
}
