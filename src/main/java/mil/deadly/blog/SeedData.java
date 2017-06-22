package mil.deadly.blog;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import mil.deadly.blog.domain.PeopleRepository;
import mil.deadly.blog.domain.Person;
import mil.deadly.blog.domain.Tag;

@Configuration
public class SeedData {

	private PeopleRepository people;

	public SeedData(PeopleRepository people) {
		this.people = people;
	}
	
	@PostConstruct
	public void seed() {
		people.save(new Person("Mitch", "Ruebush", "mitch.ruebush@capitalone.com"));
		people.save(new Person("Corbyn", "Burnson", "baldguy@yahoo.com"));
		people.save(new Person("Sian", "Liddiard", "welsh_lady@hotmail.com"));
	}
}
