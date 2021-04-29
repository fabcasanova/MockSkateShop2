/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phatsmalone.in;

import com.phatsmalone.business.Categories;
import com.phatsmalone.business.Products;
import java.util.List;

/**
 *
 * @author phatsmaloney
 */
public interface productsIN {
    
    List<Products> get(String id, int pValue, int pageNumber, String sort);
    List<Categories> getCategoryNames();
    List<Products> getProductInfo(String id);
    public int getCount(String id);
}
