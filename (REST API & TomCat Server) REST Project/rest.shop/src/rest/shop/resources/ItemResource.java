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

import rest.shop.dao.ItemDao;
import rest.shop.model.Item;


public class ItemResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    public ItemResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item getItem() {
        Item item = ItemDao.instance.getItemById(id);
        if(item==null)
            throw new RuntimeException("Get: Todo with " + id +  " not found");
        return item;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public Item getTodoHTML() {
        Item item = ItemDao.instance.getItemById(id);
        if(item==null)
            throw new RuntimeException("Get: Todo with " + id +  " not found");
        return item;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putItem(JAXBElement<Item> item) {
        Item c = item.getValue();
        return putAndGetResponse(c);
    }

    
    @DELETE
    public void deleteItem() {
        boolean c = ItemDao.instance.removeItemById(id);
        if(c==false)
            throw new RuntimeException("Delete: Item with " + id +  " not found");
    } 

    @SuppressWarnings("unlikely-arg-type")
	private Response putAndGetResponse(Item item) {
        Response res;
        if(ItemDao.instance.getModel().contains(item.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        ItemDao.instance.getModel().add(item);
        return res;
    }



}