package com.testtask.shop.dao;

import com.testtask.shop.drinks.Drink;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Павло on 22.09.2017.
 */
public interface ProductDAO {
    List<Drink> readAllDrinks() throws IOException;
    void writeDrink(Drink drink) throws IOException;
    void rewriteAllDrinks(List<Drink> drinks) throws IOException;
}
