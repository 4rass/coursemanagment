package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Reply;
import entity.Users;

public class UsersManager {
	
	

	private final EntityManager entityManager;

	public UsersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
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
	public String updateUsers(int id,String name,String password) {
		Users user = new Users(id,name,password);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		return Reply.OK_STR;
		}catch (Exception e) {
			return Reply.FAIL_STR;
		}
	}

	/**
	 * This function create new Users and return it with 
	 * id from user-interface .
	 * @param user
	 * @return Users.
	 */
	public Users create(Users user) {
		try{
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	/**
	 * This function get parameter id 
	 * and return it from data base.
	 * @param id
	 * @return Reply.
	 */ 
	public Reply deleteUser(int id) {
		try{
		Users user=get(id);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		return new Reply();

	} catch (Exception e) {
		Reply r = new Reply();
		r.setId(-1);
		r.setMsg(e.getMessage());
		return r;
	}
	}


	/**
	 * This function get a specific Users
	 * by id from data base and return it.
	 * @param id
	 * @return Users.
	 */
	public Users get(int id) {
		return entityManager.find(Users.class, id);
	}
	/*שם משתמש וסיסמא איתן 1*/
	/**
	 * This function get specific Users by name
	 * and return it .
	 * @param name
	 * @param password
	 * @return Users.
	 */
	public Users getByName(String name,String password) {
		try{	
		String sql = "select * from users where userName like '"+name+"' and password like  '"+password+"' ";
		
			return (Users) entityManager.createNativeQuery(sql, Users.class).getSingleResult();
		}catch(Exception e){
		
			return null;
		}
		
	}
	
	/* איתן 2כל המשתמשים*/
	/**
	 * This function get all Users from database
	 * and return them in List of Users. 
	 * @return List Users.
	 */
	public List<Users> getallusers() {
			String sql = "select * from users";
			return (List<Users>) entityManager.createNativeQuery(sql, Users.class).getResultList();
			
	}
	
}
