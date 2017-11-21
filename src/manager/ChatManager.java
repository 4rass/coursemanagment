package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Chat;
import entity.Courses;
import entity.Students;

public class ChatManager {



	private final EntityManager entityManager;

	public ChatManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update( Chat chat) {
		entityManager.getTransaction().begin();
		entityManager.merge(chat);
		entityManager.getTransaction().commit();
	}

	public Chat createNewMassage(int course1,String date,String massage) {
		
		Courses course = ManagerHelper.getCoursesManager().get(course1);
		Chat chat = new Chat( course, date, massage);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.persist(chat);
		entityManager.getTransaction().commit();
		return chat;
		} catch (Exception e) {
			return null;
		}		
	
	}

	public List<Chat> getAllMassages(int id){
		String sql = "SELECT * FROM coursemanagment.chat where course="+id;
		return (List)entityManager.createNativeQuery(sql, Chat.class).getResultList();
	}
	
	
	public List<Chat> getCourseIdAssociateToUserId(int userId){
		String sql = " SELECT c.id,c.course FROM coursemanagment.chat c "+ 
					" inner join coursemanagment.students s on s.id = c.student "+
					" inner join coursemanagment.users u on u.id = s.user "+
					" where u.id ="+userId ;
		return entityManager.createNativeQuery(sql, Chat.class).getResultList();
	}
	
	public void delete(Chat chat) {
		entityManager.getTransaction().begin();
		entityManager.remove(chat);
		entityManager.getTransaction().commit();
	}

	public Chat get(int id) {
		return entityManager.find(Chat.class, id);
	}
}
