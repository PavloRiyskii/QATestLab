package com.testtask.shop.drinks;

import java.util.Objects;

/**
 * Created by Павло on 22.09.2017.
 */
public class DrinkInfo {

    private String drinkName;
    private Integer soldQuantity;
    private Integer rebuyQuantity;
    private Double priceSum;
    private Double drinkPurchasePrice;

    public DrinkInfo() {
    }

    public DrinkInfo(String drinkName, Integer soldQuantity, Integer rebuyQuantity, Double priceSum, Double drinkPurchasePrice) {
        this.drinkName = drinkName;
        this.soldQuantity = soldQuantity;
        this.rebuyQuantity = rebuyQuantity;
        this.priceSum = priceSum;
        this.drinkPurchasePrice = drinkPurchasePrice;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Integer getRebuyQuantity() {
        return rebuyQuantity;
    }

    public void setRebuyQuantity(Integer rebuyQuantity) {
        this.rebuyQuantity = rebuyQuantity;
    }

    public Double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    public Double getDrinkPurchasePrice() {
        return drinkPurchasePrice;
    }

    public void setDrinkPurchasePrice(Double drinkPurchasePrice) {
        this.drinkPurchasePrice = drinkPurchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkInfo info = (DrinkInfo) o;
        return Objects.equals(drinkName, info.drinkName) &&
                Objects.equals(soldQuantity, info.soldQuantity) &&
                Objects.equals(rebuyQuantity, info.rebuyQuantity) &&
                Objects.equals(priceSum, info.priceSum) &&
                Objects.equals(drinkPurchasePrice, info.drinkPurchasePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinkName, soldQuantity, rebuyQuantity, priceSum, drinkPurchasePrice);
    }
}
