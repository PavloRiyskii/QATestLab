package com.testtask.shop.drinks;

import java.util.List;
import java.util.Objects;

/**
 * Created by Павло on 21.09.2017.
 */
public class SoftDrink extends Drink {
    private String group;
    private List<String> composition;

    public SoftDrink() {
    }

    public SoftDrink(String drinkName, Double drinkPurchasePrice, Double drinkVolume, Integer drinkAvalaibleQuantity,
                     String group, List<String> composition) {
        super(drinkName, drinkPurchasePrice, drinkVolume, drinkAvalaibleQuantity);
        this.group = group;
        this.composition = composition;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getComposition() {
        return composition;
    }

    public void setComposition(List<String> composition) {
        this.composition = composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SoftDrink softDrink = (SoftDrink) o;
        return Objects.equals(group, softDrink.group) &&
                Objects.equals(composition, softDrink.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, composition);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\"");
        for(int i = 0; i < composition.size() - 1; i++) {
            builder.append(composition.get(i) +", ");
        }
        builder.append(composition.get(composition.size()-1));
      /*  for(String s : composition) {
            builder.append(s +", ");
        }*/
        builder.append("\"");
        return "\"" + getDrinkName() + "\", " + getDrinkPurchasePrice() + ", \"" + getGroup() + "\", " + getDrinkVolume() + ", " + builder.toString() + ", " +getDrinkAvalaibleQuantity();
    }


//"Вода минеральная Хорошо", 9.99, "минеральные воды", 0.3, "вода минеральная, лечебно-столовая", 570
}
