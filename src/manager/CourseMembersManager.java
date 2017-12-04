package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.CourseMembers;
import entity.Courses;
import entity.Reply;
import entity.Students;

public class CourseMembersManager {
	
	private final EntityManager entityManager;

	public CourseMembersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}


	//Lior Asras(1)
	/**
	 * This function update a specific member of course .
	 * @param student
	 * @param course
	 * @return Reply.
	 */
	public Reply updateCourseMember(int id,int student,int course) {
		
		Students students = ManagerHelper.getStudentsManager().get(student);
		Courses courses = ManagerHelper.getCoursesManager().get(course);
		
		CourseMembers courseMember = new CourseMembers(id,students,courses);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(courseMember);
		entityManager.getTransaction().commit();
		
		return new Reply();
		
		}catch (Exception e){
		Reply r = new  Reply();
		r.setId(-1);
		r.setMsg(e.getMessage());
		return r;
		}
		
	}

	
	//Lior Asras(2)
	/**
	 * This function get parameter from user-interface and create new course member in data base. 
	 * @param student
	 * @param course
	 * @return CourseMembers.
	 */
	public CourseMembers createCourseMembers(int student,int course) {
		
		Students students = ManagerHelper.getStudentsManager().get(student);
		Courses courses = ManagerHelper.getCoursesManager().get(course);
		
		CourseMembers courseMembers = new CourseMembers(students,courses);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.persist(courseMembers);
		entityManager.getTransaction().commit();
		
		return courseMembers;
		
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * This function find specific CourseMember 
	 * from data base by parameter id.
	 * @param id
	 * @return CourseMember.
	 */
	public CourseMembers get(int id) {
		return entityManager.find(CourseMembers.class, id);
	}
		/**
		 * This function get parameter from user-interface and delete  course
		 * member in data base.
		 * 
		 * @param id
		 */
		public Reply deleteCourseMembers(int id) {
			
			try {
				CourseMembers cm = get(id);
				entityManager.getTransaction().begin();
				entityManager.remove(cm);
				entityManager.getTransaction().commit();
				return new Reply();
		
			} catch (Exception e) {
				Reply r = new Reply();
				r.setId(-1);
				r.setMsg(e.getMessage());
				return r;
			}
		}



	/**
	 * This function get all CourseMembers from data base.
	 * @return List CourseMembers.
	 */
	public List<CourseMembers> getAllCourseMembers() {
		String sql = "select * from coursemembers";
		return (List) entityManager.createNativeQuery(sql, CourseMembers.class).getResultList();
	}
	
	public List<CourseMembers> getCourseMembersByCourseId(int id){
		String sql = "SELECT * FROM coursemanagment.coursemembers where course ="+id;
		return (List)entityManager.createNativeQuery(sql, CourseMembers.class).getResultList();
	}
	
	public List<CourseMembers> getAssociateCoursesToStudentByUserId(int userId){
		String sql = "SELECT * FROM coursemanagment.coursemembers cm "+
						"inner join coursemanagment.students s on s.id = cm.student "+
						"inner join coursemanagment.users u on u.id = s.user " +
						"where u.id ="+userId;
		return (List<CourseMembers>)entityManager.createNativeQuery(sql, CourseMembers.class).getResultList();
	}
}
