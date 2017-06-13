package mil.deadly.blog.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PeopleRepository extends JpaRepository<Person, Long> {

	public List<Person> findByFirstName(String firstName);
	
}
