package service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


import entity.Students;

import manager.ManagerHelper;


@Path("/student")
public class StudentsService {
	
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
	
	@GET
	@Path("get")
	public Students getStudents(@QueryParam("id") int id){
		return ManagerHelper.getStudentsManager().get(id); 
	}
	
	

}
