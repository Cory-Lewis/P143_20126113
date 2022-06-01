/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Agirp
 */
public class Enemy extends Eukrasian
{
    private int xpRewarded;
    
    public Enemy(String name, int healthPoints, int strength, int xpRewarded)
    {
        super(name, healthPoints, healthPoints, strength);
        this.xpRewarded = xpRewarded;
    }
    
    /**
     * @return the xpRewarded
     */
    public int getXpRewarded() {
        return xpRewarded;
    }

    /**
     * @param xpRewarded the xpRewarded to set
     */
    public void setXpRewarded(int xpRewarded) {
        this.xpRewarded = xpRewarded;
    }
    
    //use random number generation to determine what loot the user gets once they kill a monster
    protected void dropItem(Adventurer adventurer)
    {
        Random rand = new Random();
        int itemRoll = rand.nextInt(20) + 1;
        HealthPotion potion = new HealthPotion();
        String userInput = null;
        boolean exitBool = false;
        
        System.out.println("You have slayed the " + this.getName() + "! You gain " + this.xpRewarded + " xp.");
        
        adventurer.setXp(adventurer.getXp() + this.getXpRewarded());
        
        if (itemRoll > 1 && itemRoll <= 9)
        {
            System.out.println("You recieved one potion for your efforts in battle.");
            adventurer.addToInventory(potion);
        }
        else
        {
            System.out.println("Fortune does not smile upon you today, you recieve nothing...");
        }
    }
    
}
