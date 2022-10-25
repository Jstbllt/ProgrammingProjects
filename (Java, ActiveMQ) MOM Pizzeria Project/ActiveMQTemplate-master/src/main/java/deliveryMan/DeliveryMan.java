package deliveryMan;

import org.apache.activemq.ActiveMQConnectionFactory;
import utils.Order;
import utils.State;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeliveryMan implements Runnable{
    private String name;

    public DeliveryMan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void deliver(Order order){
        System.out.println("-> -> -> -> " + name + " is delivering the order " + order.getOrder_number()+ "... ... ...\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void receiveOrderFromCooker() {
        try{
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:60999");
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination ( Topic or Queue);
            Destination destination = session.createQueue("Commande prête pour livraison");
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
                            System.out.println("-- Delivery man " + name + " received:\n" + order + "\n");

                            order.setState(State.IN_DELIVERY);
                            sendUpdateState(order);

                            //Delivery man deliver Order to customer
                            ExecutorService es = Executors.newSingleThreadExecutor();
                            Future<String> future = es.submit(() ->   {
                                deliver(order);
                                return "Order " + order.getOrder_number() + " delivered!\n";
                            });
                            while(!future.isDone()){}
                            es.shutdown();

                            order.setState(State.DELIVERED);
                            sendUpdateState(order);
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
            // Create a message ;
            ObjectMessage message = session.createObjectMessage();
            message.setObject(order);
            // Tell the producer to send the message
            producer.send(message);
            System.out.println("--> Delivery man " + name + " has updated the state of the order " + order.getOrder_number() + " from " + order.getCustomer().getName() + " to: " + order.getState() +"\n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        receiveOrderFromCooker();
    }
}
