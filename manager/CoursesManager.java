package manager;


import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Courses;
public class CoursesManager {

	private final EntityManager entityManager;

	public CoursesManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Courses course) {
		entityManager.getTransaction().begin();
		entityManager.merge(course);
		entityManager.getTransaction().commit();
	}

	public void create(Courses course) {
		entityManager.getTransaction().begin();
		entityManager.persist(course);
		entityManager.getTransaction().commit();
	}

	

	public void delete(Courses course) {
		entityManager.getTransaction().begin();
		entityManager.remove(course);
		entityManager.getTransaction().commit();
	}

	public Courses get(Integer id) {
		return entityManager.find(Courses.class, id);
	}
}
	