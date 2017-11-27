package manager;


import java.util.List;

import javax.persistence.EntityManager;
import javax.print.DocFlavor.STRING;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.CourseSubject;
import entity.Courses;
import entity.Lecturers;
import entity.Reply;
import entity.Rooms;
public class CoursesManager {

	private final EntityManager entityManager;

	public CoursesManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	//Lior Asras (7)
	/**
	 * This function get parameters from user-interface and update a course
	 *  and return Reply if the update done.
	 * @param id
	 * @param lecturer
	 * @param startDate
	 * @param endDate
	 * @param description
	 * @param courseName
	 * @param courseSubject
	 * @param details
	 * @param isActive
	 * @return Reply.
	 */
	public String updateCorses(int id,String courseName,int lecturer,String startDate,String endDate,
			String description, int  courseSubject1,int room1,
			boolean isActive,String presentation) {
		Lecturers lecturers = ManagerHelper.getLecturersManager().get(lecturer);
		CourseSubject courseSubject = ManagerHelper.getCourseSubjectManager().get(courseSubject1);
		Rooms room = ManagerHelper.getRoomsManager().get(room1);
		Courses course = new Courses(id,courseName,lecturers,startDate,endDate,description,courseSubject,
				room,isActive,presentation);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(course);
		entityManager.getTransaction().commit();
			
		return Reply.OK_STR;
		
		}catch (Exception e) {
			e.printStackTrace();
			
			return Reply.FAIL_STR;
		}
	}

	//orian27.11.2017
	/**
	 * This function get parameters and create a new Course, 
	 * and returned the Course from data baase with id.
	 * if its failed return null.s 
	 * @param courseName
	 * @param lecturer
	 * @param startDate
	 * @param endDate
	 * @param description
	 * @param courseSubject1
	 * @param room1
	 * @param isActive
	 * @return
	 */
	public Courses createNewCourse(String courseName,int lecturer,String startDate,String endDate,
			String description, int  courseSubject1,int room1,
			boolean isActive,String presentation) {
		
		Lecturers lecturers = ManagerHelper.getLecturersManager().get(lecturer);
		CourseSubject courseSubject = ManagerHelper.getCourseSubjectManager().get(courseSubject1);
		Rooms room = ManagerHelper.getRoomsManager().get(room1);
		
		
		Courses course = new Courses(courseName,lecturers,startDate,endDate,description,courseSubject,
				room,isActive, presentation);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.persist(course);
		entityManager.getTransaction().commit();
		return course;
		}catch (Exception e) {
			return null;
		}
	}

	
	//Lior Asras(14)
	/**
	 * This function put a specific Course in Archive that set this Course
	 * NOT active.
	 * @param courseName
	 * @param lecturer
	 * @param startDate
	 * @param endDate
	 * @param description
	 * @param courseSubject1
	 * @param room1
	 * @param isActive
	 * @return OK if its done, or fail if it failed.
	 */
	public String ArchiveCourse(int id,boolean isActive) {
		Courses course = get(id);
		course.setActive(isActive);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(course);
		entityManager.getTransaction().commit();
		
		return Reply.OK_STR;
		
		} catch (Exception e) {
			return Reply.FAIL_STR;
		}
	}

	/**
	 * This function find a specific Courses by 
	 * parameter id from 
	 * user-interface .
	 * @param id
	 * @return
	 */
	public Courses get(Integer id) {
		return entityManager.find(Courses.class, id);
	}
	
	

	//Lior Asras(3)
	/**
	 * This function get all courses from data base.
	 * @return
	 */
	public List<Courses> getAllCourses(){
		String sql=" SELECT distinct * FROM coursemanagment.courses";
		return (List)entityManager.createNativeQuery(sql, Courses.class).getResultList();
		
	}
	public List<Courses>getActiveCourses(){
		String sql ="SELECT * FROM coursemanagment.courses where isActive = 1";
		return entityManager.createNativeQuery(sql, Courses.class).getResultList();
	}
	public List<Courses>getAssociatedCoursesWithSubject(int courseSubject){
		String sql= "SELECT * FROM courses where courseSubject =" + courseSubject+" ";
		return (List) entityManager.createNativeQuery(sql , Courses.class).getResultList();		
	}
	
	public Courses getCourseById(int id){
		String sql = "select * from courses where id ="+id;
		return (Courses) entityManager.createNativeQuery(sql, Courses.class).getSingleResult();
		
	}
	public String uploadPresentation(String id,String presentation){
		Courses course = get(Integer.parseInt(id));
		course.setPresentation(presentation);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(course);
		entityManager.getTransaction().commit();
		return Reply.OK_STR;
		} catch (Exception e) {
			return Reply.FAIL_STR;
		}
	}
	
}
	