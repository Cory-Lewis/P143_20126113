/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;

import java.io.Serializable;

/**
 *
 * @author Agirp
 */
public class HealthPotion extends Item implements Serializable
{
    
    public HealthPotion()
    {
        super("Health Potion", "This concoction instantly restores a third of your HP.");
    }
    
    //restore the hp of the adventurer and then remove the potion from the inventory
    public String activatePotion(Adventurer adventurer)
    {
        adventurer.setHealthPoints(adventurer.getHealthPoints() + (adventurer.getMaxHealth() / 3));
        
        return "You use a potion. It restores " + (adventurer.getMaxHealth() / 3) + " HP.";
    }
}
