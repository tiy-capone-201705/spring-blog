package mil.deadly.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag, Long> {
}
