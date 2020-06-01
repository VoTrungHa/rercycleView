package vn.edu.ntu.votrungha.controller;

import java.util.ArrayList;

import vn.edu.ntu.votrungha.model.Product;

public interface ICartController {

    public ArrayList<Product> getListProduct();
    public boolean addToShopping(Product p);
    public ArrayList<Product>getShoppingCart();
    public void clearShoppingCart();
    public String getCountCart();

}
