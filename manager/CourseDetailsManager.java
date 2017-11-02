package manager;


import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.CourseDetails;


public class CourseDetailsManager {

	private final EntityManager entityManager;

	public CourseDetailsManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(CourseDetails courseDetail) {
		entityManager.getTransaction().begin();
		entityManager.merge(courseDetail);
		entityManager.getTransaction().commit();
	}

	public void create(CourseDetails courseDetail) {
		entityManager.getTransaction().begin();
		entityManager.persist(courseDetail);
		entityManager.getTransaction().commit();
	}

	public void delete(CourseDetails courseDetail) {
		entityManager.getTransaction().begin();
		entityManager.remove(courseDetail);
		entityManager.getTransaction().commit();
	}

	public CourseDetails get(int id) {
		return entityManager.find(CourseDetails.class, id);
	}

}