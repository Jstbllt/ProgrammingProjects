package rest.shop.dao;

import java.util.ArrayList;
import java.util.List;

import rest.shop.model.Item;
import rest.shop.model.Recommendation;
import rest.shop.model.User;


public enum ItemDao {
    instance;
	
    private List<Item> contentProvider = new ArrayList<>();

    private ItemDao() {
    	
    	User user1 = UserDao.instance.getUserById("1");
    	User user2 = UserDao.instance.getUserById("2");
    	User user3 = UserDao.instance.getUserById("3");
    	
    	Recommendation recommendation1 = new Recommendation ("Very Good", "Good product", user1);
    	Recommendation recommendation2 = new Recommendation ("Good", "Good", user1);
    	Recommendation recommendation3 = new Recommendation ("Pretty Good", "Can be better", user2);
    	Recommendation recommendation4 = new Recommendation ("Very Good", "I recommend it", user2);
    	Recommendation recommendation5 = new Recommendation ("Satisfying", "Good", user3);
    	Recommendation recommendation6 = new Recommendation ("Good", "Good product", user3);
    	
    	Item item1 = new Item("1", "BOOK", "Harry Potter", "A l'ecole des sorciers, le volume 1", 2020, user1, LandOrSell.LAND);
    	item1.addRecommandation(recommendation3);
    	item1.updateRate(5);
    	item1.addRecommandation(recommendation4);
    	item1.updateRate(2);
    	contentProvider.add(item1);
    	
    	Item item2 = new Item("2", "BOOK", "Désobéir", "Par Frederique Gros", 2020, user1, LandOrSell.LAND);
    	item2.addRecommandation(recommendation5);
    	item2.updateRate(4);
    	
    	contentProvider.add(item2);
    	
    	Item item3 = new Item("3", "DVD", "Hunger Games", "With Katniss Everdeen", 2020, user2, LandOrSell.LAND);
    	item3.addRecommandation(recommendation1);
    	item3.updateRate(5);
    	contentProvider.add(item3);
    	
    	Item item4 = new Item("4", "DVD", "Shrek", "Le 1. Fait ton grrrr", 2020, user2, LandOrSell.LAND);
    	item4.addRecommandation(recommendation6);
    	item4.updateRate(4);
    	contentProvider.add(item4);
    	
    	Item item5 = new Item("5", "VIDEO_GAMES", "KIRBY", "Jeu WII", 2020, user3, LandOrSell.LAND);
    	item5.addRecommandation(recommendation2);
    	item5.updateRate(3);
    	contentProvider.add(item5);
    	
    	Item item6 = new Item("6", "VIDEO_GAMES", "MARIO KART", "Jeu DS", 2020, user3, LandOrSell.LAND);
    	contentProvider.add(item6);
    }
    
    public List<Item> getModel(){
        return contentProvider;
    }
    
    public Item getItemById(String id) {
		for(Item item : contentProvider) {
			if(item.getId().equals(id)) return item;
		}
		return null;
	}
    
    public boolean removeItemById(String id) {
		for(Item item : contentProvider) {
			if(item.getId().equals(id)) {
				contentProvider.remove(item);
				return true;
			}
		}
		return false;
	}
    
    
    //for the users
    public List<Item> getItemsOfUser(String id) {
		List<Item> items = new ArrayList<Item>();
		for(Item item : contentProvider) {
			if(item.getOwner().getId().equals(id)) {
				items.add(item);
			}
		}
		return items;
	}
    
    public List<Item> getItemsByCategory(String category){
    	List<Item> items = new ArrayList<Item>();
    	for(Item item : contentProvider) {
    		if(item.getCategory().equals(category)) {
    			items.add(item);
    		}
    	}	
        return items;
    }
    
    public List<Item> getItemsByCity(String city) {
		List<Item> items = new ArrayList<Item>();
		for(Item item : contentProvider) {
			if(item.getOwner().getCity().toLowerCase().equals(city.toLowerCase())) {
				items.add(item);
			}
		}
		return items;
	}
    
    public List<Item> getItemsByUser(String lastname) {
		List<Item> items = new ArrayList<Item>();
		for(Item item : contentProvider) {
			if(item.getOwner().getLastname().toLowerCase().equals(lastname.toLowerCase())) {
				items.add(item);
			}
		}
		return items;
	}

    public List< Item> getItemsByKeyword(String keyword) {
		List<Item> items = new ArrayList<Item>();
		for(Item item : contentProvider) {
			if(item.getName().toLowerCase().contains(keyword)) {
				items.add(item);
			}
			else if (item.getDescription().toLowerCase().contains(keyword)) {
				items.add(item);
			}
			else if (item.getStringCategory().contains(keyword.toUpperCase())) {
				items.add(item);
			}
			else if(item.getOwner().getCity().toLowerCase().equals(keyword.toLowerCase())) {
				items.add(item);
			}
			else if(item.getOwner().getLastname().toLowerCase().equals(keyword.toLowerCase())) {
				items.add(item);
			}
			else if(item.getOwner().getFirstname().toLowerCase().equals(keyword.toLowerCase())) {
				items.add(item);
			}
		}		
		return items;
	}
}