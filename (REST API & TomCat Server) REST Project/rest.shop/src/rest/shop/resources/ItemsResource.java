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

import rest.shop.dao.ItemDao;
import rest.shop.dao.LandOrSell;
import rest.shop.dao.UserDao;
import rest.shop.model.Item;
import rest.shop.model.Recommendation;
import rest.shop.model.User;



/// Will map the resource to the URL items
@Path("/items")
public class ItemsResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    // Return the list of todos to the user in the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Item> getItemsBrowser() {
        List<Item> items = new ArrayList<Item>();
        items.addAll(ItemDao.instance.getModel());
        return items;
    }

    // Return the list of todos for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Item> getItems() {
        List<Item> items = new ArrayList<Item>();
        items.addAll(ItemDao.instance.getModel());
        return items;
    }

    // retuns the number of todos
    // Use http://localhost:8080/com.vogella.jersey.todo/rest/items/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = ItemDao.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newItem(@FormParam("category") String category,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("year") int year,
            @FormParam("owner") String ownerid,
            @FormParam("landOrSell") LandOrSell landOrSell,
            @Context HttpServletResponse servletResponse) throws IOException {
    	User owner = UserDao.instance.getUserById(ownerid);
        Item item = new Item(category, name, description, year, owner, landOrSell);
        if (description != null) {
            item.setDescription(description);
        }
        ItemDao.instance.getModel().add(item);
        servletResponse.sendRedirect("http://127.0.0.1:5500/home.html");
    }

    // Defines that the next path parameter after items is
    // treated as a parameter and passed to the ItemsResources
    // Allows to type http://localhost:8080/rest.shop/rest/items/1
    // 1 will be treaded as parameter items and passed to TodoResource
    @Path("{item}")
    public ItemResource getTodo(@PathParam("item") String id) {
        return new ItemResource(uriInfo, request, id);
    }
    
  //return a list of item of a special category
    @GET
    @Path("category/{category}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByCategory(@PathParam("category") String category) {
    	List<Item> items = new ArrayList<Item>();
        items.addAll(ItemDao.instance.getItemsByCategory(category));
        return items;
    }   
    
    //return a list of item of a special category
    @GET
    @Path("category/{category}")
    @Produces(MediaType.TEXT_XML)
    public List<Item> getItemsByCategoryBrowser(@PathParam("category") String category) {
    	List<Item> items = new ArrayList<Item>();
        items.addAll(ItemDao.instance.getItemsByCategory(category));
        return items;
    }   
    
  //return a list of item in a given city
    @GET
    @Path("/city/{city}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByCity(@PathParam("city") String city){
    	List<Item> items = new ArrayList<Item>();
    	items.addAll(ItemDao.instance.getItemsByCity(city));
    	return items;
    }
    
  //return a list of item in a given user lastname
    @GET
    @Path("/user/{lastname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByUser(@PathParam("lastname") String lastname){
    	List<Item> items = new ArrayList<Item>();
    	items.addAll(ItemDao.instance.getItemsByUser(lastname));
    	return items;
    }
    
    //return a list of item by keyword
    @GET
    @Path("/keyword/{keyword}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> getItemsByKeyword(@PathParam("keyword") String keyword){
    	List<Item> items = new ArrayList<Item>();
    	items.addAll(ItemDao.instance.getItemsByKeyword(keyword));
    	return items;
    }
    
    @POST
    @Path("/{item}/addRecommendation")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newRecommendationOnItem(@FormParam("id") String id,
    		@FormParam("title") String title,
            @FormParam("comment") String comment,
            @FormParam("publisher") String publisherid,
            @FormParam("stars") int stars,
            @Context HttpServletResponse servletResponse) throws IOException {
    	User publisher = UserDao.instance.getUserById(publisherid);
    	Recommendation recommandation = new Recommendation(title, comment, publisher);
    	Item item = ItemDao.instance.getItemById(id);
    	item.addRecommandation(recommandation);
    	item.updateRate((stars));
        servletResponse.sendRedirect("http://127.0.0.1:5500/home.html");
    }
    
    @POST
    @Path("/{item}/by_or_borrow")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newDisponibilityOnItem(@FormParam("idItem") String idItem,
    		@FormParam("futurOwner") String Owner,
            @Context HttpServletResponse servletResponse) throws IOException {
    	User futureOwner = UserDao.instance.getUserById(Owner);
    	Item item = ItemDao.instance.getItemById(idItem);
    	item.updateDisponibility(futureOwner);
        servletResponse.sendRedirect("http://127.0.0.1:5500/home.html");
    }
    
    @POST
    @Path("/{item}/returnProduct")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void returnProduct(@PathParam("item") String itemid, @Context HttpServletResponse servletResponse) throws IOException {
    	Item item = ItemDao.instance.getItemById(itemid);
    	item.returnProduct();
        servletResponse.sendRedirect("http://127.0.0.1:5500/home.html");
    }
    
    @POST
    @Path("/{item}/deleteProduct")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteItem(@PathParam("item") String itemid, @Context HttpServletResponse servletResponse) throws IOException {
    	boolean c = ItemDao.instance.removeItemById(itemid);
    	if(c==false)
            throw new RuntimeException("Delete: Item with " + itemid +  " not found");
        servletResponse.sendRedirect("http://127.0.0.1:5500/home.html");
    }
     
}