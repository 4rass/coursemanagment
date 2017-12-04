package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.CourseMembers;
import entity.Reply;
import entity.Students;
import manager.ManagerHelper;


@Path("/CourseMembers")
public class CourseMembersService {
	


	@GET
	@Path("AssociateCoursesToStudentByUserId")
	public List<CourseMembers> getAssociateCoursesToStudentByUserId(@QueryParam ("userId")int userId){
		return ManagerHelper.getCourseMembersManager().getAssociateCoursesToStudentByUserId(userId);
	}
	@GET
	@Path("getCourseMembersByCourseId")
	public List<CourseMembers> getCourseMembersByCourseId(@QueryParam("id")int id){
		return ManagerHelper.getCourseMembersManager().getCourseMembersByCourseId(id);
	}
	//Lior Asras(2)
	/**
	 * This function get parameter from user-interface and send it to function that
	 *  create new course member in data base.  
	 * @param student
	 * @param course
	 * @return CourseMembers.
	 */
	@GET
	@Path("createNewCourseMembers")
	public CourseMembers createCourseMembers(@QueryParam("student") int student,@QueryParam("course")int course){
		return ManagerHelper.getCourseMembersManager().createCourseMembers(student, course);	}
	
	//Lior Asras(1)
	/**
	 * This function get parameter from  user-interface and send them to 
	 * a function that update the course member.
	 * @param id
	 * @param student
	 * @param course
	 * @return
	 */
	@GET
	@Path("updateCourseMember")
	public Reply updateCourseMember(@QueryParam("id")int id,
			@QueryParam("student") int student,@QueryParam("course")int course){
		return ManagerHelper.getCourseMembersManager().updateCourseMember(id, student, course);	}
	
	/**
	 * This function find specific CourseMember 
	 * from data base by parameter id.
	 * @param id
	 * @return CourseMember.
	 */
	@GET
	@Path("getCourseMemberById")
	public CourseMembers getCourseMemberById(@QueryParam("id") int id){
		return ManagerHelper.getCourseMembersManager().get(id);
	}

	
	/**
	 * This function get parameter from user-interface and send them to a
	 * function that delete the course member.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("deleteCourseMember")
	public Reply deleteCourseMembers(@QueryParam("id") int id) {
		return ManagerHelper.getCourseMembersManager().deleteCourseMembers(id);
	}
	// Eytan Mooche(2)
		/**
		 * This function get parameter from user-interface and send them to a
		 * function that bring all the course member.
		 * 
		 * 
		 * @return list
		 */
	@GET
	@Path("getAllCourseMembers")
	public List<CourseMembers> getAllCourseMembers() {
		return( List<CourseMembers>)manager.ManagerHelper.getCourseMembersManager().getAllCourseMembers();
	}
}
