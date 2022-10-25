package cooker;

import org.apache.activemq.ActiveMQConnectionFactory;
import utils.*;

import javax.jms.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Cook implements Runnable{
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void cookPizzas(Order order) throws InterruptedException {
        List<Pizza> pizzaOrdered = order.getOrderedPizza();
        for(Pizza pizza : pizzaOrdered){
            if (pizza.getType() == Type.MARGHERITA){
                Thread.sleep(100);
                System.out.println("** MARGHERITA from order " + order.getOrder_number() + " is READY!\n");
            }else if (pizza.getType() == Type.ROYAL){
                Thread.sleep(400);
                System.out.println("** ROYAL from order " + order.getOrder_number() + " is READY!\n");
            }else if (pizza.getType() == Type.FOUR_CHEESE){
                Thread.sleep(100);
                System.out.println("** 4 CHEESE from order " + order.getOrder_number() + " is READY!\n");
            }else if (pizza.getType() == Type.VEGETARIAN){
                Thread.sleep(200);
                System.out.println("** VEGETARIAN from order " + order.getOrder_number() + " is READY!\n");
            }else if (pizza.getType() == Type.SALSA){
                Thread.sleep(300);
                System.out.println("** SALSA from order " + order.getOrder_number() + " is READY!\n");
            }
        }
    }

    public  void receiveOrderFromClerk() {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination ( Topic or Queue);
            Destination destination = session.createQueue("Commande créée");
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
                            Order order = (Order) objectMessage.getObject();
                            System.out.println("-- Cook " + name + " received:\n" + order + "\n");

                            order.setState(State.IN_PROGRESS);
                            //Update new order state to pizzeria
                            sendUpdateState(order);

                            //Preparation of the Pizzas
                            ExecutorService es = Executors.newSingleThreadExecutor();
                            Future<String> future = es.submit(() ->   {
                                cookPizzas(order);
                                return "Order finished";
                            });
                            while(!future.isDone()){}
                            es.shutdown();

                            sendMessageForReadyOrder(order);
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

    public void sendMessageForReadyOrder(Order order) {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination
            Destination destination = session.createQueue("Commande prête pour livraison");
            //Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // Create a message ;
            order.setState(State.READY_TO_BE_DELIVERED);
            sendUpdateState(order);

            ObjectMessage message = session.createObjectMessage();
            message.setObject(order);

            // Tell the producer to send the message
            System.out.println("--> Cook " + name + ": order " + order.getOrder_number()+ " from " +order.getCustomer().getName() +" is ready to be delivered.");
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void sendUpdateState(Order order) {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination ( Topic or Queue);
            Destination destination = session.createQueue("Commandes pour Pizzeria");
            //Start Connection
            connection.start();
            //Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // Create a message
            ObjectMessage message = session.createObjectMessage();
            message.setObject(order);
            // Tell the producer to send the message
            producer.send(message);
            System.out.println("--> Cook " + name + " has updated the state of the order " + order.getOrder_number() + " from " + order.getCustomer().getName() + " to: " + order.getState() +"\n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ExecutorService es = Executors.newSingleThreadExecutor();
            Future<String> future = es.submit(() ->   {
                receiveOrderFromClerk();
                return "Cook listening";
            });
            while(!future.isDone()){
                receiveOrderFromClerk();
            }
            es.shutdown();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
