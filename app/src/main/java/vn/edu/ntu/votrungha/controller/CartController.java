package vn.edu.ntu.votrungha.controller;
import android.app.Application;
import java.util.ArrayList;
import vn.edu.ntu.votrungha.model.Product;

public class CartController extends Application implements ICartController {

    ArrayList<Product> listProduct =new ArrayList<>();
    ArrayList<Product>shopping=new ArrayList<>();

    public CartController()
    {
        listProduct.add(new Product("Xoai",200000,"Ăn Chua ư là chua"));
        listProduct.add(new Product("Chuối",5000,"Chuối từ phương tây"));
        listProduct.add(new Product("Mận",30000,"Mận ngọt mơ say"));
        listProduct.add(new Product("Mận",30000,"Hồng si ngọt mơ say"));listProduct.add(new Product("Mận",30000,"Mận ngọt mơ say"));
    }
    @Override
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    @Override
    public boolean addToShopping(Product p) {
        if(!shopping.contains(p))
        {
            shopping.add(p);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Product> getShoppingCart() {
        return shopping;
    }

    @Override
    public void clearShoppingCart() {
        shopping.clear();
    }

    public String getCountCart()
    {
        if(shopping.size()>0)
        {
            return new Integer(shopping.size()).toString();
        }
        return "";

    }

}
