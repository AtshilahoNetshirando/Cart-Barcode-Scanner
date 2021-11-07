package com.example.sunday;

import android.content.res.Resources;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Data {

    public static int[] pictures;
    public static int price_updated;
    public static int[] total_updated_price;
    public static List<String> productChecker;
    public static boolean condi_product_checker;
    public static List<String> allproducts;
    public static List<String> allprices;
    public static boolean allcondition;
    public static float totalallprice;
    public static List<Integer> quantityProducts;
    public static List<Integer> allQuantity;

    public void Reset(){
        pictures = new int[10];
        price_updated = 0;
        total_updated_price = new int[20];
        productChecker = new ArrayList<>();
        condi_product_checker = false;
        allproducts = new ArrayList<>();
        allprices = new ArrayList<>();
        allcondition = false;
        totalallprice = 0;
        quantityProducts = new ArrayList<>();
        allQuantity = new ArrayList<>();
    }

    public void saveUpdateAllPricesAdd(int index, float price){
        String pr = allprices.get(index);
        String m2 = pr.substring(1);
        float num = Float.parseFloat(m2);
        /*int n = (int) num;
        int nn =n + price;*/
        float n = num;
        float nn = n + price;
        allprices.set(index, "R"+nn);
    }
    public void saveUpdateAllPrices2(){
        //int total = 0;
        float total = 0;
        for (String pprice: allprices){
            String m2 = pprice.substring(1);
            float num = Float.parseFloat(m2);
            //int n = (int) num;
            float n = num;
            total = total + n;
        }
        totalallprice = total;
    }


    //Pictures
    public void savePicture(int pos,int number){
        pictures[pos] = number;
    }
    public int[] getPictures2(){
        return pictures;
    }

    // Updating Price
    public void saveUpdatedPrice(int u_price){
        price_updated = u_price;
    }
    public int getUpdatedPrice(){
        return price_updated;
    }


    //total updated price
    public void saveTotalUpdatedPrice(int pos1, int number1){
        total_updated_price[pos1] = number1;
    }
    public int[] getTotalUpdatedPrice(){
        return total_updated_price;
    }

    //Product Checker
    public void saveProductChecker(String p_check){
        productChecker.add(p_check);
    }
    public List<String> getProductChecker(){
        return productChecker;
    }
    public void removeProductChecker(int position){
        String re_product = allprices.get(position);
        String r_product = re_product.substring(1);
        float int_product = Float.parseFloat(r_product);
        //totalallprice = totalallprice - ((int) int_product);
        totalallprice = totalallprice - int_product;
        productChecker.remove(position);
        allQuantity.remove(position);

    }
    public void save_condition_ProductChecker(String nch){
        if (productChecker.contains(nch)){
            condi_product_checker = true;
        }
        else{
            condi_product_checker = false;
        }
    }
    public void reset_condition_ProductChecker(){
        condi_product_checker = false;
    }
    public boolean get_condition_ProductChecker(){
        return condi_product_checker;
    }

    // All
    public static void saveAllProducts(String product){
        allproducts.add(product);
    }
    public static List<String> getAllProducts(){
        return allproducts;
    }
    public static void saveAllPrices(String price){
        //int length = allproducts.size();
        //int index = length -1 ;
        /*int quantity = 0;

        int[] quantity_arrary = new int[quantityProducts.size()];

        for (int i = 0;i<quantityProducts.size();i++){
            quantity_arrary[i] = quantityProducts.get(i);
        }
        quantity = quantity_arrary[-1];

        String r_product = price.substring(1);
        float int_product = Float.parseFloat(r_product);
        float product_price = int_product * quantity;
        allprices.add("R"+product_price);*/
        allprices.add(price);
        allQuantity.add(1);

    }
    public static List<Integer> getAllQuantity(){
        return allQuantity;
    }
    public static void saveAllQuantity(int index, int qun){
        allQuantity.set(index, allQuantity.get(index)+qun);
    }
    public static List<String> getAllPrices(){
        return allprices;
    }
    public static void saveAllCondition(){
        allcondition =true;
    }
    public static boolean getAllConditon(){
        return allcondition;
    }
    public static void saveTotalAllPrice(String price){
        String r_product = price.substring(1);
        float int_product = Float.parseFloat(r_product);
        totalallprice = totalallprice + int_product;
    }

    public static float getTotalAllPrice(){
        return totalallprice;
    }

    //
    public static void saveQuantityProducts(int quantity){
        quantityProducts.add(quantity);
    }



}
