package rest.shop.dao;

import java.util.ArrayList;
import java.util.List;

import rest.shop.model.User;

public enum UserDao {
	instance;
	
	private List<User> contentProvider = new ArrayList<>();
	
	private UserDao() {
		User user1  = new User("1", "Astryd", "Casimir", "Nanterre");
		User user2  = new User("2", "Justine", "Boillot", "Neuilly");
		User user3  = new User("3", "Simon", "Martinenq", "Paris");
		User user4  = new User("4", "Theo", "Canario", "Villejuif");
		contentProvider.add(user1);
		contentProvider.add(user2);
		contentProvider.add(user3);
		contentProvider.add(user4);
	}
	public List<User> getModel(){
		return contentProvider;
	}
	
	public User getUserById(String id) {
		for(User user : contentProvider) {
			if(user.getId().equals(id)) 
				return user;
		}
		return null;
	}
	
	public boolean removeUserById(String id) {
		for(User user : contentProvider) {
			if(user.getId().equals(id)) {
				contentProvider.remove(user);
				return true;
			}
		}
		return false;
	}
}
