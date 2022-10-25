package clerk;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import customer.Customer;
import org.apache.activemq.command.ActiveMQObjectMessage;
import utils.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Clerk implements Runnable, Serializable {
    private String name;

    public Clerk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name ;
    }


    public Order createOrder(Customer customer){
        List<Pizza> pizzaList = customer.getPizzaList();
        List<Drink> drinkList = customer.getDrinkList();
        float bill = 0;
        for(Pizza pizza : pizzaList) {
            bill += pizzaList.get(pizzaList.indexOf(pizza)).getPrice();
        }
        if(drinkList != null){
            for(Drink drink : drinkList) {
                bill += 1;
            }
        }
        Order order = new Order(customer, this, bill);
        return order;
    }

    public void receiveOrderFromCustomer() {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination ( Topic or Queue);
            Destination destination = session.createQueue("Demande de prise en charge");
            //Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);
            //Start Connection
            connection.start();
            // Create a JMS Listener form messages
            consumer.setMessageListener(new MessageListener(){
                @Override
                public void onMessage(Message message){
                    try{
                        if (message instanceof ObjectMessage) {

                            //The clerk receive an object message from a customer
                            ObjectMessage objectMessage = (ObjectMessage) message;
                            Customer customer = (Customer) objectMessage.getObject();
                            System.out.println("-- " + name + " received:\n" + customer);
                            Order orderToSend = createOrder(customer);
                            // Send the new order
                            sendNewOrderToPizzeria(orderToSend);
                            sendOrderToCooker(orderToSend);
                            Thread.sleep(200);

                        }else{
                            System.out.println("Received:" + message);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendOrderToCooker(Order orderToSend) {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination
            Destination destination = session.createQueue("Commande créée");
            //Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // Create a message ;
            ObjectMessage message = session.createObjectMessage();
            message.setObject(orderToSend);
            // Tell the producer to send the message
            System.out.println("-- Clerk " + this.name + " send to a cook: \n" + orderToSend + "\n");
            producer.send(message);
            // Clean up
            session.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendNewOrderToPizzeria(Order order) {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination
            Destination destination = session.createQueue("Commandes pour Pizzeria");
            //Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            /// Create a message ;
            ObjectMessage message = session.createObjectMessage();
            message.setObject(order);
            // Tell the producer to send the message
            System.out.println("-- Clerk " + this.name + " send to the pizzeria: \n" + order+ "\n");
            producer.send(message);
            // Clean up
            session.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    //Function to run the thread and produce or listen a message
    public void run() {
        try {
            ExecutorService es = Executors.newSingleThreadExecutor();
            //Future statement, which makes the program waiting for
            //the response of the method inside
            Future<String> future = es.submit(() ->   {
                //Function to receive a message through the message
                //broker
                receiveOrderFromCustomer();
                return "Clerk listening";
            });
            //While the program is waiting for the future response
            //we execute again the reception method
            while(!future.isDone()){
                //Listen again for a message
                receiveOrderFromCustomer();
            }
            es.shutdown();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
