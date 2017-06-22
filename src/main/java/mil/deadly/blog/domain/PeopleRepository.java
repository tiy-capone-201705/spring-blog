package mil.deadly.blog.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PeopleRepository extends MongoRepository<Person, String> {

	public List<Person> findByFirstName(String firstName);
	
}
