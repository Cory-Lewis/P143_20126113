/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Agirp
 */
public class Adventurer extends Eukrasian implements Serializable
{
    private int maxHealth;
    private int characterLvl;
    private int xp;
    private AdventureGear currentWeapon;
    private AdventureGear currentArmor;
    private int damageBuff;
    
    private ArrayList<HealthPotion> inventory = new ArrayList<>();
    
    public Adventurer()
    {
        super("", 0, 0, 0);
        this.characterLvl = 1;
        this.xp = 0;
        this.currentWeapon = new AdventureGear("Darklight Greatsword", "A large sword that is as black as night", "weapon", 0, 0);
        this.currentArmor = new AdventureGear("Basic Armor", "A standard-issue set of adventurer armor", "armor", 0, 0);
    }
    
    public Adventurer(String name, int healthPoints, int strength)
    {
        super(name, healthPoints, healthPoints, strength);
        this.characterLvl = 1;
        this.xp = 0;
        this.currentWeapon = new AdventureGear("Darklight Greatsword", "A large sword that is as black as night", "weapon", 0, 0);
        this.currentArmor = new AdventureGear("Basic Armor", "A standard-issue set of adventurer armor", "armor", 0, 0);
    }

    /**
     * @return the inventory
     */
    public ArrayList<HealthPotion> getInventory() 
    {
        return this.inventory;
    }
    
    /**
     * @return the characterLvl
     */
    public int getCharacterLvl() {
        return this.characterLvl;
    }

    /**
     * @param characterLvl the characterLvl to set
     */
    public void setCharacterLvl(int characterLvl) {
        this.characterLvl = characterLvl;
    }

    /**
     * @return the xp
     */
    public int getXp() {
        return this.xp;
    }

    /**
     * @param xp the xp to set
     */
    public void setXp(int xp) {
        this.xp = xp;
    }
    
    public void updateLvl()
    {
        if (this.xp <= 10 && this.characterLvl != 1)
        {
            System.out.println("You gained a lvl! You are now lvl 1.");
            this.characterLvl = 1;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 10 && this.xp <= 20&& this.characterLvl != 2)
        {
            System.out.println("You gained a lvl! You are now lvl 2.");
            this.characterLvl = 2;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 20 && this.xp <= 40&& this.characterLvl != 3)
        {
            System.out.println("You gained a lvl! You are now lvl 3.");
            this.characterLvl = 3;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 40 && this.xp <= 60&& this.characterLvl != 4)
        {
            System.out.println("You gained a lvl! You are now lvl 4.");
            this.characterLvl = 4;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 60 && this.xp <= 80&& this.characterLvl != 5)
        {
            System.out.println("You gained a lvl! You are now lvl 5.");
            this.characterLvl = 5;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 80 && this.xp <= 100&& this.characterLvl != 6)
        {
            System.out.println("You gained a lvl! You are now lvl 6.");
            this.characterLvl = 6;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 100&& this.characterLvl != 7)
        {
            System.out.println("You gained a lvl! You are now lvl 7.");
            this.characterLvl = 7;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        
    }
    
    /**
     * @return the currentWeapon
     */
    public AdventureGear getCurrentWeapon() {
        return this.currentWeapon;
    }

    /**
     * @return the currentArmor
     */
    public AdventureGear getCurrentArmor() {
        return this.currentArmor;
    }
    
    //add any bonus stats the equipment had to the user
    public void upgradeWeapon(AdventureGear weaponUpgrade)
    {
        AdventureGear oldWeapon = new AdventureGear(this.currentWeapon.getName(), " ", "weapon", 0, 0);
        this.currentWeapon.setUpgradeLvl(this.currentWeapon.getUpgradeLvl() + 1);
        System.out.println("Your " + oldWeapon + " was upgraded to " + this.currentWeapon);
        
        this.setHealthPoints(this.getHealthPoints() + weaponUpgrade.getHealthBoost());
        this.setStrength(this.getStrength() + weaponUpgrade.getStrengthBoost());
    }
    
    //add any bonus stats the equipment had to the user
    public void upgradeArmor(AdventureGear armorUpgrade)
    {
        AdventureGear oldArmor = new AdventureGear(this.currentArmor.getName(), " ", "armor", 0, 0);
        this.currentArmor.setUpgradeLvl(this.currentArmor.getUpgradeLvl() + 1);
        System.out.println("Your " + oldArmor + " was upgraded to " + this.currentArmor);
        
        this.setHealthPoints(this.getHealthPoints() + armorUpgrade.getHealthBoost());
        this.setStrength(this.getStrength() + armorUpgrade.getStrengthBoost());
    }
    
    public void addToInventory(HealthPotion drop)
    {
        this.inventory.add(drop);
    }
    
    public void removeFromInventory()
    {
        this.inventory.remove(0);
    }
    
    public String toString()
    {
        String charSave = this.getName() + "\n";
        charSave += this.getHealthPoints() + "\n";
        charSave += this.getMaxHealth() + "\n";
        charSave += this.getStrength() + "\n";
        charSave += this.getCharacterLvl() + "\n";
        charSave += this.getXp() + "\n";
        charSave += this.getCurrentWeapon() + "\n";
        charSave += this.getCurrentArmor() + "\n";
                
        return  charSave;
    }

    public void usePotion()
    {
        if (this.inventory.size() >= 1)
        {
            this.inventory.get(0).activatePotion(this);
            this.removeFromInventory(); 
        }
        else
        {
            System.out.println("\nUh oh... You dont seem to have any potions!\n");
        }
    }

    

}
