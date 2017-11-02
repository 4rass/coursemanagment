package manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerHelper {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coursemanagment");

	public static StudentsManager getStudentsManager() {

		return new StudentsManager(entityManagerFactory.createEntityManager());

	}
}