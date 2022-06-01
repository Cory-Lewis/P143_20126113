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
    private int enemyCount;
    
    private ArrayList<HealthPotion> inventory = new ArrayList<>();
    
    public Adventurer()
    {
        super("", 0, 0, 0);
        this.characterLvl = 1;
        this.xp = 0;
        this.currentWeapon = new AdventureGear("Darklight Greatsword", "A large sword that is as black as night", "weapon", 0, 0);
        this.currentArmor = new AdventureGear("Basic Armor", "A standard-issue set of adventurer armor", "armor", 0, 0);
    }
    
    public Adventurer(String name, int healthPoints, int strength, int enemyCount)
    {
        super(name, healthPoints, healthPoints, strength);
        this.characterLvl = 1;
        this.xp = 0;
        this.currentWeapon = new AdventureGear("Darklight Greatsword", "A large sword that is as black as night", "weapon", 0, 0);
        this.currentArmor = new AdventureGear("Basic Armor", "A standard-issue set of adventurer armor", "armor", 0, 0);
        this.enemyCount = enemyCount;
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
    
    public String updateLvl()
    {
        String lvlString = "";
        
        if (this.xp <= 10 && this.characterLvl != 1)
        {
            lvlString = "You gained a lvl! You are now lvl 1.\n";
            this.characterLvl = 1;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 10 && this.xp <= 20&& this.characterLvl != 2)
        {
            lvlString = "You gained a lvl! You are now lvl 2.\n";
            this.characterLvl = 2;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 20 && this.xp <= 40&& this.characterLvl != 3)
        {
            lvlString = "You gained a lvl! You are now lvl 3.\n";
            this.characterLvl = 3;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 40 && this.xp <= 60&& this.characterLvl != 4)
        {
            lvlString = "You gained a lvl! You are now lvl 4.\n";
            this.characterLvl = 4;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 60 && this.xp <= 80&& this.characterLvl != 5)
        {
            lvlString = "You gained a lvl! You are now lvl 5.\n";
            this.characterLvl = 5;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 80 && this.xp <= 100&& this.characterLvl != 6)
        {
            lvlString = "You gained a lvl! You are now lvl 6.\n";
            this.characterLvl = 6;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        else if (this.xp > 100&& this.characterLvl != 7)
        {
            lvlString = "You gained a lvl! You are now lvl 7.\n";
            this.characterLvl = 7;
            this.setStrength(this.getStrength() +5);
            this.maxHealth +=5;
        }
        
        return lvlString;
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
    public String upgradeWeapon(AdventureGear weaponUpgrade)
    {
        AdventureGear oldWeapon = new AdventureGear(this.currentWeapon.getName(), " ", "weapon", 0, 0);
        this.currentWeapon.setUpgradeLvl(this.currentWeapon.getUpgradeLvl() + 1);
        
        this.setHealthPoints(this.getHealthPoints() + weaponUpgrade.getHealthBoost());
        this.setStrength(this.getStrength() + weaponUpgrade.getStrengthBoost());
        
        return "Your " + oldWeapon + " was upgraded to " + this.currentWeapon;
    }
    
    //add any bonus stats the equipment had to the user
    public String upgradeArmor(AdventureGear armorUpgrade)
    {
        AdventureGear oldArmor = new AdventureGear(this.currentArmor.getName(), " ", "armor", 0, 0);
        this.currentArmor.setUpgradeLvl(this.currentArmor.getUpgradeLvl() + 1);
        
        this.setHealthPoints(this.getHealthPoints() + armorUpgrade.getHealthBoost());
        this.setStrength(this.getStrength() + armorUpgrade.getStrengthBoost());
        
        return "Your " + oldArmor + " was upgraded to " + this.currentArmor;
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
        String charStats = this.getName() + "\n";
        charStats +="HP:" + this.getHealthPoints() + "\n";
        charStats += "Lvl: " + this.getCharacterLvl() + "\n";
        charStats += "Xp: " + this.getXp() + "\n";
        charStats += "Str: " + this.getStrength() + "\n";
        charStats += this.getCurrentWeapon().getName() + "\n";
                
        return  charStats;
    }

    public String usePotion()
    {
        String potionString = "";
        
        if (this.inventory.size() >= 1)
        {
            potionString = this.inventory.get(0).activatePotion(this);
            this.removeFromInventory();
        }
        else
        {
            potionString = "\nUh oh... You dont seem to have any potions!\n";
        }
        
        return potionString;
    }
    
    public int getPotionCount()
    {
        return this.inventory.size();
    }
    
    public int getEnemyCount()
    {
        return this.enemyCount;
    }
    
    public void enemyDefeated()
    {
        this.enemyCount -= 1;
    }
    

}
