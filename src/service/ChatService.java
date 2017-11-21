package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Chat;
import manager.ManagerHelper;

@Path("chatService")
public class ChatService {

	@GET
	@Path("createNewMassage")
	public Chat createNewMassage(
			@QueryParam("course")int course,
			@QueryParam("date")String date,
			@QueryParam("massage")String massage){
		return ManagerHelper.getChatManager().createNewMassage(course,date, massage);
	}
	
	@GET
	@Path("getAllMassages")
	public List<Chat> getAllMassages(@QueryParam("id")int id){
		return ManagerHelper.getChatManager().getAllMassages(id);
	}
		
	@GET
	@Path("getCourseIdAssociateToUserId")
	public List<Chat> getCourseIdAssociateToUserId (@QueryParam("userId")int userId){
		return ManagerHelper.getChatManager().getCourseIdAssociateToUserId(userId);
	}
	
}
