package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.CourseMembers;

import manager.ManagerHelper;


@Path("/CourseMembers")
public class CourseMembersService {
	
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
	
	@GET
	@Path("get")
	public CourseMembers getCourseMembersManager(@QueryParam("id") int id){
		return ManagerHelper.getCourseMembersManager().get(id); 
	}
	
	
	

}
