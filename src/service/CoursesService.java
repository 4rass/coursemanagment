package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Courses;
import entity.Reply;
import manager.ManagerHelper;


@Path("/coursesService")
public class CoursesService {
		
		@GET
		@Path("getCoursesById")
		public Courses getCoursesById(@QueryParam("id") int id){
			return ManagerHelper.getCoursesManager().getCourseById(id); 
		}
	

		//Lior Asras(3)
		@GET
		@Path("getAllCourses")
		public List<Courses> getAllCourses(){
			return (List)ManagerHelper.getCoursesManager().getAllCourses();
		}
	
		//orian27.11.2017
		/**
		 * This function get parameters from user-interface and return a updated course
		 *  and return Reply if UPDATE done.
		 * @param id
		 * @param lecturer
		 * @param startDate
		 * @param endDate
		 * @param description
		 * @param courseName
		 * @param courseSubject1
		 * @param room
		 * @param isActive
		 * @return Reply.
		 */
		@GET
		@Path("updateCorses")
		public String updateCorses(@QueryParam("id")int id,
				@QueryParam("courseName")String courseName,
				@QueryParam("lecturer")int lecturer,
				@QueryParam("startdate")String startDate,
				@QueryParam("enddate")String endDate,
				@QueryParam("description")String description,
				@QueryParam("courseSubject")int  courseSubject1,
				@QueryParam("room")int room,
				@QueryParam("isActive")Boolean isActive,
				@QueryParam("presentation")String presentation){
		
			return ManagerHelper.getCoursesManager().updateCorses(id,courseName, lecturer, startDate, endDate, description,  courseSubject1, room, isActive,presentation);
		}
		
		//orian27.11.2017
		@GET
		@Path("createNewCourse")
		public Courses createNewCourse(@QueryParam("courseName")String courseName,
				@QueryParam("lecturer")int lecturer,
				@QueryParam("startdate")String startDate,
				@QueryParam("enddate")String endDate,
				@QueryParam("description")String description,
				@QueryParam("courseSubject")int  courseSubject1,
				@QueryParam("room")int room,
				@QueryParam("isActive")Boolean isActive,
				@QueryParam("presentation")String presentation){
			return ManagerHelper.getCoursesManager().createNewCourse(courseName, lecturer, startDate, endDate, description, courseSubject1, room, isActive,presentation);
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
		@GET                                                  
		@Path("ArchiveCourse")
		public String ArchiveCourse(@QueryParam("id")int id,@QueryParam("isActive")boolean isActive){
			return ManagerHelper.getCoursesManager().ArchiveCourse(id,isActive);
		}
		@GET                                                  
		@Path("ActiveCourse")
		public List<Courses> getActiveCourses(){
			return ManagerHelper.getCoursesManager().getActiveCourses();
		}
		@GET
		@Path("getAssociatedCoursesWithSubject")
		public List<Courses> getAssociatedCoursesWithSubject(@QueryParam("CourseSubject") int CourseSubject){
			return ManagerHelper.getCoursesManager().getAssociatedCoursesWithSubject(CourseSubject);
		}
}
