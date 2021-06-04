/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package watches;

/**
 *
 * @author megaa
 */
public class Watch implements WatchInterface
{
    String brand;
    int price;
    
    public Watch(String brand_, int price_)
    {
        brand = brand_;
        price = price_;
    }
    
    @Override
    public void set_brand(String brand_)
    {
        brand = brand_;
    }
    @Override
    public void set_price(int price_)
    {
        price = price_;
    }
    @Override
    public void set_brand_and_price(String brand_, int price_)
    {
        brand = brand_;
        price = price_;
    }
    
    @Override
    public String get_brand()
    {
        return brand;
    }
    @Override
    public int get_price()
    {
        return price;
    }
    @Override
    public void print()
    {
        System.out.println("Brand: " + brand + "\nPrice: " + price);
    }
}
