package rest.shop.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Recommendation {
	private int number_recommandation = 6;
	private int id;
	private String title;
	private String comment;
	private User publisher;	
	
	public Recommendation() {
		
	}
	
	public Recommendation(String title, String comment, User publisher) {
		this.title = title;
		this.comment = comment;
		this.publisher = publisher;
		this.number_recommandation +=1;
		this.id = this.number_recommandation;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
}
