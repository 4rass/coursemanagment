package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Lecturers;
import manager.ManagerHelper;


@Path("/Lecturers")
public class LecturersService {

	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();

	@GET
	@Path("get")
	public Lecturers LecturersManager(@QueryParam("id") int id) {
		return ManagerHelper.getLecturersManager().get(id);
	}

	

}