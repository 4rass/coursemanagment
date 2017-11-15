package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Days;
import entity.Reply;
import manager.ManagerHelper;


@Path("/daysService")
public class DaysService {
		
	/**
	 * This function get parameter id from user-interface
	 * and return  specific Days . 
	 * @param id
	 * @return Days.
	 */
		@GET
		@Path("getDaysById")
		public Days getDaysById(@QueryParam("id") int id){
			return ManagerHelper.getDaysManager().get(id); 
		}
	
		//Lior Asras (9)
		/**
		 * This function get parameters from user interface and 
		 * send them to function that create new Day to courses
		 * and return it with id from data base.
		 * @param date
		 * @param agenda
		 * @param startTime
		 * @param endTime
		 * @param course
		 * @return Days.
		 */
		@GET
		@Path("createNewDaysToCourses")
		public Days createDaysToCourses(@QueryParam("date")String date,@QueryParam("agenda")String agenda,
				@QueryParam("startTime")String startTime,@QueryParam("endTime")String endTime,
				@QueryParam("course")int course){
			return ManagerHelper.getDaysManager().createDaysToCourses(date, agenda, startTime, endTime, course);
		}
		
		//Lior Asras(13)
		/**
		 * This function get parameter from user-interface and
		 * send it to a function that delete that day that assoicate to course.
		 * return Reply if its done or not.
		 * @param id
		 * @return Reply.
		 */
		@GET
		@Path("deleteDaysToCourses")
		public Reply deleteDaysToCourses(@QueryParam("id")int id){
			return ManagerHelper.getDaysManager().deleteDaysToCourses(id);
		}
		
		/**
		 * This function get parameters from user-interface
		 *  and  send them to function that 
		 *  update a specific Days to specific course 
		 * and return Reply String if it done or not.
		 * @param id
		 * @param date
		 * @param agenda
		 * @param startTime
		 * @param endTime
		 * @param course
		 * @return Reply.
		 */
		@GET
		@Path("updateDaysToCourse")
		public String updateDaysToCourses(
				@QueryParam("id")int id,
				@QueryParam("date")String date,@QueryParam("agenda")String agenda,
				@QueryParam("startTime")String startTime,@QueryParam("endTime")String endTime,
				@QueryParam("course")int course) {
			return ManagerHelper.getDaysManager().updateDaysToCourses(id, date, agenda, startTime, endTime, course);
		}
		
		@GET
		@Path("getDaysAssociateToCourseById")
		public List<Days> getDaysAssociateToCourseById (@QueryParam("id")int id){
			return ManagerHelper.getDaysManager().getDaysAssociateToCourseById(id);
		}
}
