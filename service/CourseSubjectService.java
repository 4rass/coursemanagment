package service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.CourseSubject;
import manager.ManagerHelper;


@Path("/CourseSubject")
public class CourseSubjectService {
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
	

	@GET
	@Path("get")
	public CourseSubject getCourseSubjectManager(@QueryParam("id") int id) {
		System.out.println("delete service EmployeeProject");
		return ManagerHelper.getCourseSubjectManager().get(id);
	}
	
	
}
