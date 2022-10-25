package rest.shop.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import rest.shop.model.*;
import rest.shop.dao.*;

public class UserRessource {
	@Context 
	UriInfo uriInfo;
	@Context 
	Request request;
	String id;
	
	public UserRessource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	//Application integration
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public User getUser() {
		User user = UserDao.instance.getUserById(id);
		if(user==null) {
			throw new RuntimeException("Get: User with " + id +  " not found"); 
		}
		return user;
	}
	
	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public User getUserHTML() {
		User user = UserDao.instance.getUserById(id);
		if(user==null) {
			throw new RuntimeException("Get: User with " + id +  " not found"); 
		}
		return user;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUser(JAXBElement<User> user) {
		User c = user.getValue();
		return putAndGetResponse(c);
	}
	
	@DELETE
    public void deleteUser() {
        Boolean c = UserDao.instance.removeUserById(id);
        if(!c)
            throw new RuntimeException("Delete: User with " + id +  " not found");
    }
	
	@SuppressWarnings("unlikely-arg-type")
	private Response putAndGetResponse(User user) {
        Response res;
        if(UserDao.instance.getModel().contains(user.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        UserDao.instance.getModel().add(user);
        return res;
    }	
}
