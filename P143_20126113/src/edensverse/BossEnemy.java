/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;

import java.util.Scanner;

/**
 *
 * @author Agirp
 */
public class BossEnemy extends Enemy 
{
    private String name;
    private int healthPoints;
    private int strength;
    private int xpRewarded;
    private AdventureGear itemDrop;

    public BossEnemy(String name, int healthPoints, int strength, int xpRewarded, AdventureGear itemDrop) 
    {
        super(name, healthPoints, strength, xpRewarded);
        this.itemDrop = itemDrop;
    }

    @Override
    protected void dropItem(Adventurer adventurer)
    {
        System.out.println("You prevailed! For your efforts, you are rewarded with an " + this.itemDrop);
        System.out.println("You absorb the " + this.itemDrop + ". Your power grows...");
        
        
        if (null == this.itemDrop.getGearType())
        {
            System.out.println("There was some kind of error in equipping the item...");
        }
        else //check whether item is a weapon or armor and use the correct equip function
        switch (this.itemDrop.getGearType()) {
            case "weapon":
                adventurer.upgradeWeapon(itemDrop);
                break;
            case "armor":
                adventurer.upgradeArmor(itemDrop);
                break;
            default:
                System.out.println("There was some kind of error in equipping the item...");
                break;
        }
   
    }
    
    //reset health of boss to half and increase it's stats slightly
    public void enrage()
    {
        this.setHealthPoints(this.getMaxHealth() / 2);
        this.setStrength(this.getStrength() +10);
    }

    
}
