/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Agirp
 */
public class Eukrasian  implements Serializable  {

    private String name;
    private int healthPoints;
    private int maxHealth;
    private int strength;
    
    public Eukrasian(String name, int healthPoints, int maxHealth, int strength)
    {
        this.name = name;
        this.healthPoints = healthPoints;
        this.maxHealth = maxHealth;
        this.strength = strength;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the healthPoints
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * @param healthPoints the healthPoints to set
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    
    /**
     * @return the maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
     /**
     * @param maxHealth the maxHealth to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int calculateDamage()
    {
        int damage = 0;
        Random rand = new Random();
        
        damage = rand.nextInt(this.getStrength() + 1);
        
        return damage;
    }
    
    //use the random number generator to calculate damage
    public String attack(Eukrasian enemy)
    {
        String attackString = "";
        int damage = this.calculateDamage();
        
        //if the attacker's dmg is higer than 0, the attack is successful
        if(damage > 0)
        {
            attackString += this.getName() + "'s attack is successful! " + damage + " dmg is dealt.\n";
            enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        }       
        else        
        {
            attackString += this.getName() + "'s strength fails them! No dmg is dealt.\n";
        }
        
        return attackString;
    }
    
    public String soulEater(Eukrasian enemy)
    {
        String soulEaterString = "";
        int damage = this.calculateDamage() + 1;
        
        soulEaterString = "\nsouleater does " + damage + " dmg. " + this.name + " gains " + damage + " HP. ";
        enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        this.setHealthPoints(this.getHealthPoints() + damage);
        
        if (this.getHealthPoints() > this.getMaxHealth())
        {
            this.setHealthPoints(this.getMaxHealth());
        }
        
        return soulEaterString;
    }
    
    public String lacerate(Eukrasian enemy)
    {
        String lacerateString = "";
        int damage = (this.calculateDamage() / 2)+ 7;
        
        lacerateString += "\nLacerate does " + damage + " dmg. " + enemy.getName() + " is afflicted with bleed.";
        enemy.setHealthPoints(enemy.getHealthPoints() - damage);
        
        return lacerateString;
    }
}
