package com.example.demo;

import clerk.Clerk;
import cooker.Cook;
import customer.Customer;
import deliveryMan.DeliveryMan;
import org.apache.activemq.ActiveMQConnectionFactory;
import utils.*;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static void thread(Runnable runnable, boolean daemon) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.setDaemon(daemon);
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("------ WELCOME TO OUR PIZZERIA ------");
        Pizzeria pizzeria = new Pizzeria();

        Customer customer1 = new Customer("Martinenq", "Simon", "99 avduz", "0698987654", new Date());
        Customer customer2 = new Customer("Bonnet", "Pierre", "99 zertyuio", "0698987654", new Date());
        Customer customer3 = new Customer("Elbaz", "Jonathan", "99 sdfghjk", "0698987654", new Date());
        Customer customer4 = new Customer("Senaux", "Amelie", "99 dfghjk", "0698987654", new Date());

        // Declaration for the customer 1
        Pizza p11 = new Pizza(Size.SMALL, Type.MARGHERITA);
        Pizza p12 = new Pizza(Size.MEDIUM, Type.MARGHERITA);
        ArrayList cp1 = new ArrayList() {{
            add(p11);
            add(p12);
        }};
        customer1.setPizzaList(cp1);

        Drink d11 = new Drink(DrinkName.COLA, 33);
        ArrayList cd1 = new ArrayList() {{
            add(d11);
        }};
        customer1.setDrinkList(cd1);

        // Declaration for the customer 2
        Pizza p21 = new Pizza(Size.SMALL, Type.ROYAL);
        Pizza p22 = new Pizza(Size.MEDIUM, Type.FOUR_CHEESE);
        ArrayList cp2 = new ArrayList() {{
            add(p21);
            add(p22);
        }};
        customer2.setPizzaList(cp2);

        Drink d21 = new Drink(DrinkName.COLA, 33);
        ArrayList cd2 = new ArrayList() {{
            add(d21);
        }};
        customer2.setDrinkList(cd2);

        // Declaration for the customer 3
        Pizza p31 = new Pizza(Size.LARGE, Type.ROYAL);
        ArrayList cp3 = new ArrayList() {{
            add(p31);
        }};
        customer3.setPizzaList(cp3);

        Drink d31 = new Drink(DrinkName.COLA, 33);
        ArrayList cd3 = new ArrayList() {{
            add(d31);
            add(d31);
        }};
        customer3.setDrinkList(cd3);

        // Declaration for the customer 4
        Pizza p41 = new Pizza(Size.MEDIUM, Type.VEGETARIAN);
        ArrayList cp4 = new ArrayList() {{
            add(p41);
        }};
        customer4.setPizzaList(cp4);

        Drink d41 = new Drink(DrinkName.COLA, 33);
        ArrayList cd4 = new ArrayList() {{
            add(d41);
        }};
        customer4.setDrinkList(cd4);

        Clerk clerk1 = new Clerk("Justine");
        Clerk clerk2 = new Clerk("Laura");

        Cook cook1 = new Cook("Jean");
        Cook cook2 = new Cook("Astryd");
        Cook cook3 = new Cook("Antonin");

        DeliveryMan deliveryMan1 = new DeliveryMan("Pascal");
        DeliveryMan deliveryMan2 = new DeliveryMan("Paul");

        thread(customer1, false);
        thread(customer2, false);
        thread(customer3, false);
        thread(customer4, false);

        thread(clerk1, false);
        thread(clerk2, false);

        thread(cook1, false);
        thread(cook2, false);
        thread(cook3, false);

        thread(deliveryMan1, false);
        thread(deliveryMan2, false);

        thread(pizzeria, false);
    }


}