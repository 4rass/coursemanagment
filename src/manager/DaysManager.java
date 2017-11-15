package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Courses;
import entity.Days;
import entity.Reply;

public class DaysManager {
	
	

	private final EntityManager entityManager;

	public DaysManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	
	/**
	 * This function get parameters and update a specific Days to specific course 
	 * and return Reply String if it done or not.
	 * @param id
	 * @param date
	 * @param agenda
	 * @param startTime
	 * @param endTime
	 * @param course
	 * @return Reply.
	 */
	public String updateDaysToCourses(int id,String date,String agenda,String startTime,String endTime,int course) {
		
		Courses course1 = ManagerHelper.getCoursesManager().get(course);
		Days day = new Days(id,date,agenda,startTime,endTime,course1);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(day);
		entityManager.getTransaction().commit();
		
		return Reply.OK_STR;
		}catch (Exception e) {
			return Reply.FAIL_STR;
		}
	}

	
	//Lior Asras (9)
	/**
	 * This function create new Days with id from data base,
	 * if its failed it return null.
	 * @param date
	 * @param agenda
	 * @param startTime
	 * @param endTime
	 * @param course
	 * @return Days.
	 */
	public Days createDaysToCourses (String date,String agenda,String startTime,String endTime,int course) {
		
	Courses courses = ManagerHelper.getCoursesManager().get(course);
	
	Days day = new Days(date,agenda,startTime,endTime,courses);
		
	try{
		entityManager.getTransaction().begin();
		entityManager.persist(day);
		entityManager.getTransaction().commit();
		
		return day;
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	}

	


	//Lior ASras(13)
	/**
	 * This function get parameter and delete a Day that associate to course,
	 * return Reply if its done or not.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteDaysToCourses(int id) {
		
		Days day = get(id);
		try{
		entityManager.getTransaction().begin();
		entityManager.remove(day);
		entityManager.getTransaction().commit();
		return new Reply();
		
		}catch (Exception e) {
			
		Reply r = new Reply();
		r.setId(id);
		r.setMsg(e.getMessage());
		return r;
		}
	}

	public Days get(int id) {
		return entityManager.find(Days.class, id);
	}
	
	public List<Days> getDaysAssociateToCourseById (int id){
		String sql = "SELECT * FROM coursemanagment.days where course ="+id;
		return(List<Days>) entityManager.createNativeQuery(sql, Days.class).getResultList();
	}

}
