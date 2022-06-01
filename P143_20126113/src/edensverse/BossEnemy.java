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
    protected String dropItem(Adventurer adventurer)
    {
        String itemString = "";
        itemString += "You prevailed! For your efforts, you are rewarded with an " + this.itemDrop + "\n";
        itemString += "You absorb the " + this.itemDrop + ". Your power grows...\n";
        
        
        if (null == this.itemDrop.getGearType())
        {
            itemString += "There was some kind of error in equipping the item...\n";
        }
        else //check whether item is a weapon or armor and use the correct equip function
        switch (this.itemDrop.getGearType()) {
            case "weapon":
                itemString += adventurer.upgradeWeapon(itemDrop) + "\n";
                break;
            case "armor":
                itemString += adventurer.upgradeArmor(itemDrop) + "\n";
                break;
            default:
                itemString += "There was some kind of error in equipping the item...\n";
                break;
        }
        return itemString;
    }
}
