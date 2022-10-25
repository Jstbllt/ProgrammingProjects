package rest.shop.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.shop.model.*;
import rest.shop.dao.*;

@Path("/users")
public class UsersRessources {
	@Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<User> getUsersBrowser() {
        List<User> users = new ArrayList<User>();
        users.addAll(UserDao.instance.getModel());
        return users;
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        users.addAll(UserDao.instance.getModel());
        return users;
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = UserDao.instance.getModel().size();
        return String.valueOf(count);
    }
    
    @POST 
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newUser(@FormParam("firstname") String firstname,
    		@FormParam("lastname") String lastname,
    		@FormParam("city") String city,
    		@Context HttpServletResponse servletResponse) throws IOException{
    	User user = new User(firstname, lastname, city);
    	//String id = user.getId();
    	UserDao.instance.getModel().add(user);
    	servletResponse.sendRedirect("../rest/users");
    }
    
    @Path("{user}")
    public UserRessource getUser(@PathParam("user") String id) {
        return new UserRessource(uriInfo, request, id);
    }
    
    
    //return a list of item by keyword
    @GET
    @Path("/{user}/items")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByKeyword(@PathParam("user") String id){
    	List<Item> items = new ArrayList<Item>();
    	items.addAll(ItemDao.instance.getItemsOfUser(id));
    	return items;
    }
    
     
}
