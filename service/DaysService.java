package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Days;


import manager.ManagerHelper;


@Path("/Days")
public class DaysService {
		
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("coursemanagment");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
		
		@GET
		@Path("get")
		public Days DaysManager(@QueryParam("id") int id){
			return ManagerHelper.getDaysManager().get(id); 
		}
	

}
