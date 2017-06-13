package mil.deadly.blog;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import mil.deadly.blog.domain.Person;
import mil.deadly.blog.domain.Tag;

@Configuration
public class SeedData {

	private EntityManagerFactory factory;

	public SeedData(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@PostConstruct
	public void seed() {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		try
		{
			manager.merge(new Tag("Spring"));
			manager.merge(new Tag("Java"));
			manager.merge(new Tag("Turkey"));
			manager.merge(new Tag("Where is Usha?"));
			
			manager.merge(new Person("Mitch", "Ruebush", "mitch.ruebush@capitalone.com"));
			manager.merge(new Person("Corbyn", "Burnson", "baldguy@yahoo.com"));
			manager.merge(new Person("Sian", "Liddiard", "welsh_lady@hotmail.com"));
		}
		finally
		{
			manager.getTransaction().commit();
		}		
	}
}
