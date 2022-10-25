package utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import customer.Customer;
import clerk.Clerk;

public class Order implements Serializable {
    private static int total_number_of_order = 0;
    private int order_number;
    private Date date_time;
    private List<Pizza> orderedPizza;
    private List<Drink> orderedDrink;
    private Customer customer;
    private Clerk clerk;
    private float bill;
    private State state;

    public Order(Customer customer, Clerk clerk, float bill) {
        this.date_time = new Date();
        this.orderedPizza = customer.getPizzaList();
        this.orderedDrink = customer.getDrinkList();
        this.customer = customer;
        this.clerk = clerk;
        this.bill = bill;
        this.state = State.VALIDATED;
        this.total_number_of_order += 1;
        this.order_number = total_number_of_order;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public List<Pizza> getOrderedPizza() {
        return orderedPizza;
    }

    public void setOrderedPizza(List<Pizza> orderedPizza) {
        this.orderedPizza = orderedPizza;
    }

    public List<Drink> getOrderedDrink() {
        return orderedDrink;
    }

    public void setOrderedDrink(List<Drink> orderedDrink) {
        this.orderedDrink = orderedDrink;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public float getBill() {
        return bill;
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order number: " + order_number + "\nDate and time of the order: " + date_time + "\nPizza(s) ordered: " + orderedPizza + "\nDrink(s) ordered (optional): " + orderedDrink + "\nCustomer: " + customer.getName() + "\nClerk involved: " + clerk.getName() + "\nTotal amount: " + bill + " euros.\nSTATE: " + state;
    }
}