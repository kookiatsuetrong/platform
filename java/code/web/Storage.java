package web;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class Storage {

	static EntityManager getManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("main");
		}
		return factory.createEntityManager();
	}
	
	private static EntityManagerFactory factory = null;

	static ZonedDateTime getTime() {
		return ZonedDateTime.now(ZoneId.of("UTC"));
	}
}
