package service;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;



import entity.Rooms;
import manager.ManagerHelper;


@Path("/Rooms")
public class RoomsService {
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();

	@GET
	@Path("get")
	public Rooms getRoomsManager(@QueryParam("id") int id) {
		System.out.println("delete service EmployeeProject");
		return ManagerHelper.getRoomsManager().get(id);
	}
	
	

}
