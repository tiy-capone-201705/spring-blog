package mil.deadly.blog.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagsRepository extends MongoRepository<Tag, String> { //extends JpaRepository<Tag, Long> {
}
