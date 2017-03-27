package ClientEx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elijahhunt93
 */
public class MenuItem {
    private int index;
    private String food, description;
    private double price;
    
    public MenuItem(int index, String food, String description, double price){
        this.index = index;
        this.food = food;
        this.description = description;
        this.price = price;
    }
    
    
    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public String getFood(){
        return food;
    }
    public void setFood(String food){
        this.food = food;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public double getprice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    
    @Override
    public String toString(){
        return "Item number: " + index +
                "\n" + "Dish: "+ food + 
                "\n" +  "Description: " + description + 
                "\n" + "Price: " + price + "\n" + "\n";
    }
    
}
