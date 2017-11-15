package service;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


import entity.Students;

import manager.ManagerHelper;


@Path("/student")
public class StudentsService {
	
	@GET
	@Path("getStudentsByid")
	public Students getStudentsByid(@QueryParam("id") int id){
		return ManagerHelper.getStudentsManager().get(id); 
	}
	
	@GET
	@Path("getStudentNameByUserId")
	public List<Students> getStudentNameByUserId(@QueryParam("id")int id) {
		return ManagerHelper.getStudentsManager().getStudentNameByUserId(id);
	}
	

}
