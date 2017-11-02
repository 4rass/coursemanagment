package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.openjpa.persistence.EntityManagerImpl;
import entity.Lecturers;


public class LecturersManager {

	private final EntityManager entityManager;

	public LecturersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	public void update(Lecturers lecturer) {
		entityManager.getTransaction().begin();
		entityManager.merge(lecturer);
		entityManager.getTransaction().commit();
	}

	public void create(Lecturers lecturer) {
		entityManager.getTransaction().begin();
		entityManager.persist(lecturer);
		entityManager.getTransaction().commit();
	}

	public void delete(Lecturers lecturer) {
		entityManager.getTransaction().begin();
		entityManager.remove(lecturer);
		entityManager.getTransaction().commit();
	}

	public Lecturers get(Integer id) {
		return entityManager.find(Lecturers.class, id);
	}

	
	

}
