package com.testtask.shop.drinks;

import java.util.Objects;

/**
 * Created by Павло on 21.09.2017.
 */
public class Drink {

    private String drinkName;
    private Double drinkPurchasePrice;
    private Double drinkVolume;
    private Integer drinkAvalaibleQuantity;

    public Drink() {
    }

    public Drink(String drinkName, Double drinkPurchasePrice, Double drinkVolume, Integer drinkAvalaibleQuantity) {
        this.drinkName = drinkName;
        this.drinkPurchasePrice = drinkPurchasePrice;
        this.drinkVolume = drinkVolume;
        this.drinkAvalaibleQuantity = drinkAvalaibleQuantity;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Double getDrinkPurchasePrice() {
        return drinkPurchasePrice;
    }

    public void setDrinkPurchasePrice(Double drinkPurchasePrice) {
        this.drinkPurchasePrice = drinkPurchasePrice;
    }

    public Double getDrinkVolume() {
        return drinkVolume;
    }

    public void setDrinkVolume(Double drinkVolume) {
        this.drinkVolume = drinkVolume;
    }

    public Integer getDrinkAvalaibleQuantity() {
        return drinkAvalaibleQuantity;
    }

    public void setDrinkAvalaibleQuantity(Integer drinkAvalaibleQuantity) {
        this.drinkAvalaibleQuantity = drinkAvalaibleQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(drinkName, drink.drinkName) &&
                Objects.equals(drinkPurchasePrice, drink.drinkPurchasePrice) &&
                Objects.equals(drinkVolume, drink.drinkVolume) &&
                Objects.equals(drinkAvalaibleQuantity, drink.drinkAvalaibleQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinkName, drinkPurchasePrice, drinkVolume, drinkAvalaibleQuantity);
    }
}

