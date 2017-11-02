package manager;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Days;

public class DaysManager {
	
	

	private final EntityManager entityManager;

	public DaysManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Days day) {
		entityManager.getTransaction().begin();
		entityManager.merge(day);
		entityManager.getTransaction().commit();
	}

	public void create(Days day) {
		entityManager.getTransaction().begin();
		entityManager.persist(day);
		entityManager.getTransaction().commit();
	}

	public void delete(Days day) {
		entityManager.getTransaction().begin();
		entityManager.remove(day);
		entityManager.getTransaction().commit();
	}

	public Days get(int id) {
		return entityManager.find(Days.class, id);
	}

}
