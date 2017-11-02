package service;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Users;




@Path("/users")
public class Userservice {
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
	
	
	

	@GET
	@Path("userid")
	public Users getuser(@QueryParam("id") Integer id) {
		return manager.ManagerHelper.getUsersManager().get(id);
	}

	@GET
	@Path("user")

	public Users getuserbyName(@QueryParam("username") String name,@QueryParam("password")String password) {
		return manager.ManagerHelper.getUsersManager().getByName(name,password);
	}
	

	@GET
	@Path("allusers")

	public List<Users> getallusers() {

		return manager.ManagerHelper.getUsersManager().getallusers();
	}
	
	
	

}
