package com.testtask.shop.dao.impl;

import com.testtask.shop.dao.ProductDAO;
import com.testtask.shop.drinks.AlcoholicDrink;
import com.testtask.shop.drinks.Drink;
import com.testtask.shop.drinks.SoftDrink;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Павло on 22.09.2017.
 */
public class ProductDAOImpl implements ProductDAO {

    private File file;
    private static ProductDAOImpl productDAO;
    private ProductDAOImpl(File file) {
        this.file = file;
    }

    @Override
    public List<Drink> readAllDrinks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> drinks = new ArrayList<>();
        String s;
        while((s = reader.readLine()) != null) {
            drinks.add(s);
        }
        reader.close();
        return parseDrinks(drinks);
    }

    private List<Drink> parseDrinks(List<String> drinks) {
        Iterator<String> drinksIterator = drinks.iterator();
        List<Drink> drinkList = new ArrayList<>(drinks.size());
        while(drinksIterator.hasNext()) {
            String[] components = drinksIterator.next().split(",");
            if(components[components.length - 2].endsWith("%")) {
                drinkList.add(builAlcoholicDring(components));
            } else {
                drinkList.add(buildSoftDrink(components));
            }
        }
        return drinkList;
    }

    private AlcoholicDrink builAlcoholicDring(String[] components) {
        AlcoholicDrink drink = new AlcoholicDrink();
        drink.setDrinkName(components[0].replace("\"", "").trim());
        drink.setDrinkPurchasePrice(new Double(components[1].trim()));
        drink.setClassification(components[2].replace("\"", "").trim());
        drink.setDrinkVolume(new Double(components[3].trim()));
        drink.setGrade(new Double(components[4].trim().replace("%", "")));
        drink.setDrinkAvalaibleQuantity(new Integer(components[5].trim()));
        System.out.println(drink);
        return drink;
    }

    private SoftDrink buildSoftDrink(String[] components) {
        SoftDrink drink = new SoftDrink();
        drink.setDrinkName(components[0].replace("\"", "").trim());
        drink.setDrinkPurchasePrice(new Double(components[1].trim()));
        drink.setGroup(components[2].replace("\"", "").trim());
        drink.setDrinkVolume(new Double(components[3].trim()));
        int i = 4;
        List<String> composition = new ArrayList<>();
        while(!components[i].endsWith("\"")) {
            composition.add(components[i].replace("\"", "").trim());
            i++;
        }
        composition.add(components[i].replace("\"", "").trim());
        drink.setComposition(composition);
        drink.setDrinkAvalaibleQuantity(new Integer(components[i+1].trim()));
        System.out.println(drink);
        return drink;
    }


    @Override
    public void writeDrink(Drink drink) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(drink + "/n");
        writer.close();
    }

    @Override
    public void rewriteAllDrinks(List<Drink> drinks) throws IOException {
        this.file.delete();
        this.file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Iterator<Drink> iterator = drinks.iterator();
        while (iterator.hasNext()) {
            writer.write(iterator.next().toString() + "\n");
        }
        writer.close();
    }

    public synchronized static ProductDAO getInctance(File f) {
         if(productDAO == null) {
             productDAO = new ProductDAOImpl(f);
         }
         return productDAO;
    }

}
