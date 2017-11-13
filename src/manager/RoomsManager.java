package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Rooms;

public class RoomsManager {
	


	private final EntityManager entityManager;

	public RoomsManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Rooms room) {
		entityManager.getTransaction().begin();
		entityManager.merge(room);
		entityManager.getTransaction().commit();
	}

	public void create(Rooms room) {
		entityManager.getTransaction().begin();
		entityManager.persist(room);
		entityManager.getTransaction().commit();
	}

	public void delete(Rooms room) {
		entityManager.getTransaction().begin();
		entityManager.remove(room);
		entityManager.getTransaction().commit();
	}

	public Rooms get(int id) {
		return entityManager.find(Rooms.class, id);
	}



	public List<Rooms> getAllRooms () {
		String sql="SELECT * FROM coursemanagment.Rooms;";
		return entityManager.createNativeQuery (sql,Rooms.class).getResultList();
	}

}
