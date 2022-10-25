package utils;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizzeria implements Runnable, Serializable {

    private List<Order> orderList = new ArrayList<Order>();
    public void listenOrders() {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination ( Queue);
            Destination destination = session.createQueue("Commandes pour Pizzeria");
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
                            ObjectMessage objectMessage = (ObjectMessage) message;
                            //Receive the object Order
                            Order received_order = (Order) objectMessage.getObject();
                            int cpt = 0;
                            //Verify if the order already exists in the orders database of the pizzeria
                            if (orderList.isEmpty()) {
                                //if yes, we add the entire order to the database
                                System.out.println("-- Pizzeria has received a new order " + received_order.getOrder_number() + " : " + received_order.getState() + "\n");
                                orderList.add(received_order);
                            }
                            else{
                                //if not, we update the state
                                for (Order order : orderList) {
                                    if (order.getOrder_number() == received_order.getOrder_number()) {
                                        order.setState(received_order.getState());
                                        System.out.println("-- Pizzeria has received state update for order " + order.getOrder_number() + " to: " + order.getState() + "\n");
                                        cpt+=1;
                                    }
                                }
                                if (cpt == 0){
                                    System.out.println("-- Pizzeria has received a new order " + received_order.getOrder_number() + " : " + received_order.getState() + "\n");
                                    orderList.add(received_order);
                                    cpt = 0;
                                }
                            }
                        }else{

                            System.out.println("Received :" + message);
                        }
                    }catch(JMSException e){
                        e.printStackTrace();
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        listenOrders();
    }
}
