package mil.deadly.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PostsRepository extends JpaRepository<Post, Long> {

}
