package service;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Reply;
import entity.Users;
import manager.ManagerHelper;




@Path("/usersService")
public class Userservice {
	

	/**
	 * This function get user from user-interface
	 * by parameter id , and return Users.
	 * @param id
	 * @return Users.
	 */
	@GET
	@Path("getUsersById")
	public Users getUsersById(@QueryParam("id") Integer id) {
		return manager.ManagerHelper.getUsersManager().get(id);
	}

	/**
	 * This function get Users by name 
	 * and return it to user-interface.
	 * @param name
	 * @param password
	 * @return Users.
	 */
	@GET
	@Path("getUser")
	public Users getUsersByName(@QueryParam("username") String name,@QueryParam("password")String password) {
		return manager.ManagerHelper.getUsersManager().getByName(name,password);
	}
	

	/**
	 * This function get all Users from data base
	 * and return it to user-interface.
	 * @return List <Users>.
	 */
	@GET
	@Path("getAllUsers")
	public List<Users> getAllUsers() {
		return manager.ManagerHelper.getUsersManager().getallusers();
	}
	
	//Lior Asras(15)
	/**
	 * This function update specific User 
	 * and return String if its done.
	 * @param id
	 * @param name
	 * @param password
	 * @return String OK Or FAIL.
	 */
	@GET
	@Path("updateUser")
		public String updateUsers(@QueryParam("id")int id,@QueryParam("username") String name,
				@QueryParam("password")String password) {
		return ManagerHelper.getUsersManager().updateUsers(id, name, password);
	}
		
	/**
	 * This function get parameter from 
	 * user-interface and delete  course
	 * member in data base.
	 * @param id
	 */
	@GET
	@Path("deleteUser")
	public Reply deleteUser(@QueryParam("id") Integer id) {
		return manager.ManagerHelper.getUsersManager().deleteUser(id);
	}

}
