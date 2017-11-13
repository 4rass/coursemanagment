package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.openjpa.persistence.EntityManagerImpl;

import com.sun.org.glassfish.gmbal.AMXMetadata;

import entity.Lecturers;
import entity.Reply;
import entity.Users;


public class LecturersManager {

	private final EntityManager entityManager;

	public LecturersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
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
	public String updateLecturer(int id,String firstname,String lastName,int identityCard,String email,
			String phone) {
		
		Lecturers lecturer = new Lecturers(id,firstname,lastName,identityCard,email,phone);
		
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(lecturer);
		entityManager.getTransaction().commit();
		return Reply.OK_STR;
		}catch (Exception e) {
			return Reply.FAIL_STR;
		}
		
	}

		//Lior Asras(10)
	/**
	 * This function create get parameters from user-interface
	 * and create a  new Lecture and return it with id from data base. 
	 * @param firstName
	 * @param lastName
	 * @param identityCard
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 * @return Lecturers.
	 */
	public Lecturers createLectures(String firstName,String lastName,int identityCard,String email,
			String phone,String username,String password){
		
		Users users = new Users();
		users.setUsername(username);
		users.setPassword(password);
		users.setType("Lecture");  //<-----  Type of Lecturers !!!!
		
		
		users = ManagerHelper.getUsersManager().create(users);
		
		if(users != null){
			
		Lecturers lecturer = new Lecturers(firstName,lastName,identityCard,email,phone,users );
		
		try{
			entityManager.getTransaction().begin();
			entityManager.persist(lecturer);
			entityManager.getTransaction().commit();
			return lecturer;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		}else{
			return null;
		}
	}
	//Lior Asras(12)
	/** This function get parameter and delete a Lecture,
	 * return Reply if its done. 
	 * @param id
	 * @return Reply.
	 */ 
	public Reply deleteLecture(int id) {
		
		Lecturers lecturer = get(id);
		try{
		entityManager.getTransaction().begin();
		entityManager.remove(lecturer.getUser());
		entityManager.getTransaction().commit();
		
		return new Reply();
		
		}catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	//Lior Asras(13)
	/**
	 * This function find specific Lecture.
	 * @param id
	 * @return Lecturers.
	 */
	public Lecturers get(Integer id) {
		return entityManager.find(Lecturers.class, id);
	}

	//Lior Asras(11)
	/**
	 * This function get all Lecturers from data base.
	 * @return
	 */
	public List<Lecturers> getAllLecturers(){
		String sql="SELECT * FROM coursemanagment.lecturers ";
		return entityManager.createNativeQuery(sql, Lecturers.class).getResultList();
	}

}
