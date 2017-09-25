package com.testtask.shop.shop.impl;

import com.testtask.shop.dao.DAOFactory;
import com.testtask.shop.drinks.Drink;
import com.testtask.shop.drinks.DrinkInfo;
import com.testtask.shop.shop.Shop;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Павло on 21.09.2017.
 */
public class ShopImpl implements Shop{

    private List<Drink> drinks;
    private LocalDateTime date;
    private Map<String, DrinkInfo> drinkSoldsInfo;

    public ShopImpl() throws IOException {
        this.drinks = DAOFactory.getProductDao(new File(System.getProperty("user.dir") + "\\drink.csv")).readAllDrinks();
        this.date= LocalDateTime.of(2017, 9, 22, 8, 0, 0,0);
        drinkSoldsInfo = new HashMap<String, DrinkInfo>();
    }

    @Override
    public void beginWork() {
        System.out.println("Початок роботи");
        for(int i = 0; i < 30; i++ ) {
            System.out.println("\n\n\nПочаток " + (i + 1)  + " дня");
            emulateDay();
            System.out.println("Кінець" + (i + 1) + " дня");

        }
        makeReport();
        try {
            DAOFactory.getProductDao(new File(System.getProperty("user.dir") + "\\drink.csv")).rewriteAllDrinks(this.drinks);
        } catch (IOException e) {
            System.out.println("Помилка при записі в файл");
        }
    }

    private void emulateDay() {
        for(int i = 8; i < 21; i++) {
            System.out.println("\nІде " + (i + 1) + " година-------------------------------------------");
            emulateHour();
        }
        this.date.plusHours(11);
        rebuyProducts();
    }

    private void emulateHour() {
        int buysCount = new Random().nextInt(11);
        System.out.println("Ця година матиме " + buysCount + " покупок");
        for(int i = 0; i < buysCount; i++) {
            System.out.println("Покупка № " + (i + 1));
            emulateBuy();
        }

        this.date.plusHours(1);
    }

    private void emulateBuy() {
        Drink drink = this.drinks.get(new Random().nextInt(drinks.size()));
        int quantity = new Random().nextInt(10) + 1;

        if(drink.getDrinkAvalaibleQuantity() >= quantity) {
            drink.setDrinkAvalaibleQuantity(drink.getDrinkAvalaibleQuantity() - quantity);
            double price = calculatePrice(drink, quantity);

            DrinkInfo info = this.drinkSoldsInfo.get(drink.getDrinkName());
            if(info == null) {
                info = new DrinkInfo(drink.getDrinkName(), quantity, 0, price, drink.getDrinkPurchasePrice());
            } else {
                info.setSoldQuantity(info.getSoldQuantity() + quantity);
                info.setPriceSum(info.getPriceSum() + price);
            }

            this.drinkSoldsInfo.put(drink.getDrinkName(), info);
            System.out.println("\"" + drink.getDrinkName() + "\" буде куплено у кількості " + quantity + " що коштуватиме " + String.format("%.2f",price ));
        } else {
            System.out.println("\"" + drink.getDrinkName() + "\" нажаль немає потрібної кількості " + quantity);
        }
    }

    private double calculatePrice(Drink drink, int quantity) {
        double initialPrice = drink.getDrinkPurchasePrice() ;
        if(date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY)
            return (initialPrice * quantity) * 1.15;
        else if(date.getHour() >= 18 && date.getHour() <= 20)
            return (initialPrice * quantity) * 1.08;
        else if (quantity > 2 )
            return (initialPrice * (quantity - 2)) * 1.07 + ((initialPrice * 2) * 1.10);
        else
            return (initialPrice * quantity) * 1.10;
    }


    private void rebuyProducts() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Дозакупка товару");
        Iterator<Drink> drinkIterator = this.drinks.iterator();
        while(drinkIterator.hasNext()) {
            Drink d = drinkIterator.next();
            if(d.getDrinkAvalaibleQuantity() < 10) {
                d.setDrinkAvalaibleQuantity(d.getDrinkAvalaibleQuantity() + 150);
                DrinkInfo info = this.drinkSoldsInfo.get(d.getDrinkName());
                if(info == null) {
                    info = new DrinkInfo(d.getDrinkName(), 0,150, 0.0, d.getDrinkPurchasePrice());
                } else {
                    info.setRebuyQuantity(info.getRebuyQuantity() + 150);
                }
                this.drinkSoldsInfo.put(d.getDrinkName(), info);
                System.out.println("Товар \"" + d.getDrinkName() + " дозакуплено");
            }
        }
        System.out.println("------------------------------------------------------------");
    }

    private void makeReport() {
        System.out.println("------------------------------------------------------------");
        Iterator<DrinkInfo> iter = this.drinkSoldsInfo.values().iterator();
        Double soldsSum = 0.0, drinkCost = 0.0, rebuySum = 0.0;
        while(iter.hasNext()) {
            DrinkInfo info = iter.next();
            System.out.println("Для товару \'" +  info.getDrinkName() +"\" було :");
            System.out.println("\t\tПродано " + info.getSoldQuantity() );
            System.out.println("\t\tДозакуплено " + info.getRebuyQuantity());
            System.out.println("\t\tПри закупочній ціні + " + info.getDrinkPurchasePrice());
            System.out.println("\t\tПрибуток від продаж " + String.format("%.2f",info.getPriceSum()));
            soldsSum += info.getPriceSum();
            drinkCost += info.getSoldQuantity() * info.getDrinkPurchasePrice();
            rebuySum += info.getRebuyQuantity() * info.getDrinkPurchasePrice();
        }
        System.out.println("Прибуток магазину (сума продаж - собівартість проданого товару) = " + String.format("%.2f",soldsSum) + " - " + String.format("%.2f",drinkCost) +
                " = " + String.format("%.2f",(soldsSum - drinkCost)));
        System.out.println("Витрати на дозакупку товару " + String.format("%.2f",rebuySum));
        System.out.println("------------------------------------------------------------");
    }


}
