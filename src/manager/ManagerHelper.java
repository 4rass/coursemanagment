package manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerHelper {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coursemanagment");

	public static StudentsManager getStudentsManager() {
		return new StudentsManager(entityManagerFactory.createEntityManager());
	}
	
	public static RoomsManager getRoomsManager() {
		return new RoomsManager(entityManagerFactory.createEntityManager());
	}
	
	public static UsersManager getUsersManager() {
		return new UsersManager(entityManagerFactory.createEntityManager());
	}
	
	public static LecturersManager getLecturersManager() {
		return new LecturersManager(entityManagerFactory.createEntityManager());
	}
	
	public static CourseSubjectManager getCourseSubjectManager() {
		return new CourseSubjectManager(entityManagerFactory.createEntityManager());
	}
	
	public static CoursesManager getCoursesManager() {
		return new CoursesManager(entityManagerFactory.createEntityManager());
	}
	
	public static CourseMembersManager getCourseMembersManager() {
		return new CourseMembersManager(entityManagerFactory.createEntityManager());
	}
	
	public static DaysManager getDaysManager() {
		return new DaysManager(entityManagerFactory.createEntityManager());
	}
	
	public static ChatManager getChatManager () {
		return new ChatManager (entityManagerFactory.createEntityManager());
	}
}