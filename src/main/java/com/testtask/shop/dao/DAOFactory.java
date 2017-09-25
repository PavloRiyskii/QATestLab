package com.testtask.shop.dao;

import com.testtask.shop.dao.impl.ProductDAOImpl;

import java.io.File;

/**
 * Created by Павло on 22.09.2017.
 */
public class DAOFactory {

    public static ProductDAO getProductDao(File f) {
        return ProductDAOImpl.getInctance(f);
    }
}
