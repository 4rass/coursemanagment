package manager;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Students;



public class StudentsManager {
	private final EntityManager entityManager;

	public StudentsManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	public void update(Students Student) {
		entityManager.getTransaction().begin();
		entityManager.merge(Student);
		entityManager.getTransaction().commit();
	}

	public void create(Students Student) {
		entityManager.getTransaction().begin();
		entityManager.persist(Student);
		entityManager.getTransaction().commit();
	}

	public void delete(Students Student) {
		entityManager.getTransaction().begin();
		entityManager.remove(Student);
		entityManager.getTransaction().commit();
	}

	public Students get(Integer id) {
		return entityManager.find(Students.class, id);
	}
}
