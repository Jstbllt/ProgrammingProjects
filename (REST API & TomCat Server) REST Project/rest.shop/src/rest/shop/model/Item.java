package rest.shop.model;

import java.util.*;

import rest.shop.dao.LandOrSell;

public class Item{
	private static int number_item =6;
	private String id;
    private String category;
    public String name;
    private String description;
    private int year;
    private float rate;
    private User owner;
    private LandOrSell landOrSell;
    private String disponibility;
    private User futureOwner;
    ArrayList<Recommendation> recommendation = new ArrayList<Recommendation>();
    
    
	public Item() {
	}

	public Item(String id, String category, String name, String description,  int year, User owner, LandOrSell landOrSell) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.year = year;
		this.owner = owner;
		this.landOrSell = landOrSell;
		this.disponibility = "AVAILABLE";
	}
	
	public Item(String category, String name, String description,  int year, User owner, LandOrSell landOrSell) {
		number_item +=1;
		this.id = String.valueOf(number_item);
		this.category = category;
		this.name = name;
		this.description = description;
		this.year = year;
		this.owner = owner;
		this.landOrSell = landOrSell;
		this.disponibility = "AVAILABLE";
	}
	
	public String getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public void updateRate(int stars) {
		this.rate = (float) (((this.rate * (this.recommendation.size()-1)) + stars))/this.recommendation.size();
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ArrayList<Recommendation> getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ArrayList<Recommendation> recommendation) {
		this.recommendation = recommendation;
	}
	
	public void addRecommandation(Recommendation recommandation) {
		this.recommendation.add(recommandation);
	}

	public LandOrSell getLandOrSell() {
		return landOrSell;
	}

	public void setLandOrSell(LandOrSell landOrSell) {
		this.landOrSell = landOrSell;
	}
    public String getStringCategory() {
    	return this.category;
    }

	public String getDisponibility() {
		return disponibility;
	}

	public void setDisponibility(String disponibility) {
		this.disponibility = disponibility;
	}

	public void updateDisponibility(User futureUser) {
		this.disponibility = String.valueOf(this.landOrSell);
    	this.futureOwner = futureUser;
    }
	
	public User getFutureOwner() {
		return futureOwner;
	}

	public void setFutureOwner(User futureOwner) {
		this.futureOwner = futureOwner;
	}
	
	public void returnProduct() {
		this.disponibility = "AVAILABLE";
    	this.futureOwner = null;
	}
    
}

