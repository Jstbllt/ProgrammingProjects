package customer;

import javax.jms.* ;

import org.apache.activemq.ActiveMQConnectionFactory;
import utils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Customer implements Runnable, Serializable{
    private String surname;
    private String firstname;
    private String address;
    private String telephone_number;
    private Date date_first_order;
    private ArrayList pizzaList = null;
    private ArrayList drinkList = null;

    public Customer(String surname, String firstname, String address, String telephone_number , Date date_first_order) {
        this.surname = surname;
        this.firstname = firstname;
        this.address = address;
        this.telephone_number = telephone_number;
        this.date_first_order = date_first_order;
    }

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}
    public String getName() {return firstname + ' ' + surname;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getTelephone_number() {return telephone_number;}

    public void setTelephone_number(String telephone_number) {this.telephone_number = telephone_number;}

    public Date getDate_first_order() {return date_first_order;}

    public void setDate_first_order(Date date_first_order) {this.date_first_order = date_first_order;}

    public ArrayList getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(ArrayList pizzaList) {
        this.pizzaList = pizzaList;
    }

    public ArrayList getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(ArrayList drinkList) {
        this.drinkList = drinkList;
    }


    public void sendOrder() {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination
            Destination destination = session.createQueue("Demande de prise en charge");
            //Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // Create a message ;
            Customer customer = this;

            ObjectMessage message = session.createObjectMessage();
            message.setObject(customer);

            // Tell the producer to send the message
            System.out.println("New customer sent an order request:\n" + customer);
            producer.send(message);

            // Clean up
            session.close();
            connection.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        sendOrder();
    }

    @Override
    public String toString() {
        return surname + " " + firstname + "\n" + address + "\n" + telephone_number + "\nDate of first order: " + date_first_order + "\nDrink choice(s): " + drinkList + "\nPizza choice(s): " + pizzaList + "\n"  ;
    }
}

