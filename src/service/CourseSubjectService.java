package service;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.CourseSubject;
import entity.Courses;
import entity.Reply;
import manager.ManagerHelper;


@Path("/courseSubjectService")
public class CourseSubjectService {

	@GET
	@Path("getCourseSubjectById")
	public CourseSubject getCourseSubjectById(@QueryParam("id") int id) {
		return ManagerHelper.getCourseSubjectManager().get(id);
	}
	
	//Lior Asras (5)
	/**
	 * This function get parameter from user-interface 
	 * and send it to function that delete specific course subject and return Reply id its done.
	 * @param id
	 * @return Reply.
	 */
	@GET
	@Path("deleteCourseSubject")
	public Reply deleteCourseSubject(@QueryParam("id") int id) {
		return ManagerHelper.getCourseSubjectManager().deleteCourseSubject(id);
	}
	
	//Lior Asras (4)
	/**
	 * This function get parameter from user-interface
	 *  and create new CourseSubject and return it with id. if its failed return null. 
	 * @param subject
	 * @return
	 */
	@GET
	@Path("createNewCourseSubject")
	public CourseSubject createNewCourseSubject(@QueryParam("subject") String subject){
		return ManagerHelper.getCourseSubjectManager().createNewCourseSubject(subject);
	}
	
	
	//Lior Asras (6)
	@GET
	@Path("updateCourseSubject")
	public Reply updateCourseSubject(@QueryParam("id") int id
			,@QueryParam("subject") String subject){
		return ManagerHelper.getCourseSubjectManager().updateCourseSubject(id, subject);
	}
	
	@GET
	@Path("getAllCourseSubject")
	public List<CourseSubject> getAllCourseSubject(){
		return ManagerHelper.getCourseSubjectManager().getAllCourseSubject();
	}
}
