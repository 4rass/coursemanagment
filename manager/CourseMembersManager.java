package manager;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.CourseMembers;

public class CourseMembersManager {
	
	private final EntityManager entityManager;

	public CourseMembersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(CourseMembers courseMember) {
		entityManager.getTransaction().begin();
		entityManager.merge(courseMember);
		entityManager.getTransaction().commit();
	}

	public void create(CourseMembers courseMember) {
		entityManager.getTransaction().begin();
		entityManager.persist(courseMember);
		entityManager.getTransaction().commit();
	}

	public void delete(CourseMembers courseMember) {
		entityManager.getTransaction().begin();
		entityManager.remove(courseMember);
		entityManager.getTransaction().commit();
	}

	public CourseMembers get(int id) {
		return entityManager.find(CourseMembers.class, id);
	}


}
