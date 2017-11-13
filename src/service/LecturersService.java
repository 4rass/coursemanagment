package service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Lecturers;
import entity.Reply;
import manager.ManagerHelper;


@Path("/lecturers")
public class LecturersService {

	@GET
	@Path("getLecturers")
	public Lecturers getLecturers(@QueryParam("id") int id) {
		return ManagerHelper.getLecturersManager().get(id);
	}

	//Lior Asras(10)
	/**
	 * This function get parameters from user-interface and send them to function that
	 * create new lecturer with new id and return it . 
	 * @param firstname
	 * @param lastname
	 * @param identityCard
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 * @return Lecturers.
	 */
	@GET
	@Path("createNewLecture")
	public Lecturers createLectures(@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,
			@QueryParam("identityCard")int identityCard,
			@QueryParam("email")String email,
			@QueryParam("phone")String phone,
			@QueryParam("username")String username,
			@QueryParam("password")String password){
		return ManagerHelper.getLecturersManager().createLectures(firstname, lastname, identityCard, email, phone, username, password);
	}


	//Lior Asras(11)
	/**
	 * This function send request from user-interface
	 * to function that get all Lecturers from data base.
	 * @return
	 */
	@GET
	@Path("getAllLecturers")
	public List<Lecturers> getAllLecturers(){
		return ManagerHelper.getLecturersManager().getAllLecturers();
		
	}
	
	//Lior Asras(12)
	/**
	 * This function get parameter from user-interface and send it to
	 * function that delete Lecture from data base.
	 * @param id
	 * @return
	 */
	@GET
	@Path("deleteLecture")
	public Reply deleteLecture(@QueryParam("id")int id){
		return ManagerHelper.getLecturersManager().deleteLecture(id);
	}
	
	//Lior Asras(16)
	/**
	 * This function get parameters and update specific Lecturer
	 * and return Reply OK if its done and FAIL if it failed.
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param identityCard
	 * @param email
	 * @param phone
	 * @return Reply.
	 */
	@GET
	@Path("updateLecture")
	public String updateLecturer(@QueryParam("id")int id,@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,
			@QueryParam("identityCard")int identityCard,
			@QueryParam("email")String email,
			@QueryParam("phone")String phone) {
		return ManagerHelper.getLecturersManager().updateLecturer(id, firstname, lastname, identityCard, email, phone);
	}
			

}