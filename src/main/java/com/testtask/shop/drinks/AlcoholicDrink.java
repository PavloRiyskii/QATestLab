package com.testtask.shop.drinks;

import java.util.Objects;

/**
 * Created by Павло on 21.09.2017.
 */
public class AlcoholicDrink extends Drink{
    private String classification;
    private Double grade;

    public AlcoholicDrink() {
    }

    public AlcoholicDrink(String drinkName, Double drinkPurchasePrice, Double drinkVolume, Integer drinkAvalaibleQuantity,
                          String classification, Double grade) {
        super(drinkName, drinkPurchasePrice, drinkVolume, drinkAvalaibleQuantity);
        this.classification = classification;
        this.grade = grade;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AlcoholicDrink that = (AlcoholicDrink) o;
        return Objects.equals(classification, that.classification) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), classification, grade);
    }

    @Override
    public String toString() {
        return "\"" + getDrinkName() + "\", " + getDrinkPurchasePrice() + ", " + "\"" + getClassification() + "\", " + getDrinkVolume() + ", " + getGrade() + "%, " + getDrinkAvalaibleQuantity();
    }
}
