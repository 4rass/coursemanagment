package manager;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.CourseSubject;

public class CourseSubjectManager {

	private final EntityManager entityManager;

	public CourseSubjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(CourseSubject courseSubject) {
		entityManager.getTransaction().begin();
		entityManager.merge(courseSubject);
		entityManager.getTransaction().commit();
	}

	public void create(CourseSubject courseSubject) {
		entityManager.getTransaction().begin();
		entityManager.persist(courseSubject);
		entityManager.getTransaction().commit();
	}

	public void delete(CourseSubject courseSubject) {
		entityManager.getTransaction().begin();
		entityManager.remove(courseSubject);
		entityManager.getTransaction().commit();
	}

	public CourseSubject get(int id) {
		return entityManager.find(CourseSubject.class, id);
	}

}
