package utils;

import utils.Size;
import utils.Type;

import java.io.Serializable;

public class Pizza implements Serializable {
    private Size size;
    private Type type;
    private float price;

    public Pizza(Size size, Type type) {
        this.size = size;
        this.type = type;
        this.price = getPrice();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getPrice() {
        if (type == Type.MARGHERITA) {
            price = 5;
        } else if (type == Type.FOUR_CHEESE) {
            price = 7;
        } else if (type == Type.SALSA) {
            price = 6;
        } else if (type == Type.ROYAL) {
            price = 5;
        } else if (type == Type.VEGETARIAN) {
            price = 6;
        }

        if (size == Size.MEDIUM) {
            price += 2;
        }
        if (size == Size.LARGE) {
            price += 3;
        }
        return price;
    }

    @Override
    public String toString() {
        return this.type + " " + this.size + " " + this.price;
    }
}
