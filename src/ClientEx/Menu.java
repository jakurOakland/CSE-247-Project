/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientEx;

import java.util.HashMap;

/**
 * A class for holding collections of MenuItems
 * @author Justin Kur
 */

public class Menu {
    
    public HashMap<Integer, MenuItem> items;
    
    public Menu() {
        items = new HashMap<>();
    }
    
    public void add(MenuItem item) {
        items.put(item.getIndex(), item);
    }
}
