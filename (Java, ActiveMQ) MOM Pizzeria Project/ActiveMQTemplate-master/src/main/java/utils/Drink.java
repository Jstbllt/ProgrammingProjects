package utils;

import utils.DrinkName;

import java.io.Serializable;

public class Drink implements Serializable {
    private DrinkName drink;
    private float volume;

    public Drink(DrinkName drink, float volume) {
        this.drink = drink;
        this.volume = volume;
    }

    public DrinkName getDrink() {
        return drink;
    }

    public void setDrink(DrinkName drink) {
        this.drink = drink;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return this.drink + " " + this.volume ;
    }
}
