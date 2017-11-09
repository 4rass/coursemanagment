package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.CourseSubject;
import entity.Reply;

public class CourseSubjectManager {

	private final EntityManager entityManager;

	public CourseSubjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	//Lior Asras (6)
	/**
	 * This function update CourseSubject and return  CourseSubject with update.
	 * @param id
	 * @param subject
	 * @return Reply.
	 */
	public Reply updateCourseSubject(int id,String subject) {
		CourseSubject courseSubject = new CourseSubject(id,subject);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(courseSubject);
		entityManager.getTransaction().commit();
		
		return new Reply();
		
		}catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	//Lior Asras (4)
	/**
	 * This function that create new CourseSubject and return it with id from data base.
	 * if its failed it return null.
	 * @param Subject
	 * @return CourseSubject.
	 */
	public CourseSubject createNewCourseSubject(String Subject) {
		
		CourseSubject courseSubject = new CourseSubject(Subject);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.persist(courseSubject);
		entityManager.getTransaction().commit();
		
		return courseSubject;
		
		} catch (Exception e) {
			return null;
		}
	}

	//Lior Asras (5)
	/**
	 * This function get parameter ID and delete it from data base,
	 * and return Reply if its done.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteCourseSubject(int id) {
		
		CourseSubject courseSubject= get(id);
		try{
		entityManager.getTransaction().begin();
		entityManager.remove(courseSubject);
		entityManager.getTransaction().commit();
		
		return new Reply();
		}catch (Exception e) {
			Reply r = new  Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public CourseSubject get(int id) {
		return entityManager.find(CourseSubject.class, id);
	}

	public List<CourseSubject> getAllCourseSubject(){
		String sql = "SELECT * FROM coursemanagment.coursesubject";
		return (List) entityManager.createNativeQuery(sql , CourseSubject.class).getResultList();
	}
}
