package mil.deadly.blog.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PostsRepository extends MongoRepository<Post, String> { //extends JpaRepository<Post, Long> {
	public List<Post> findByTitle(String title);
}
