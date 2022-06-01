/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;
import java.io.Serializable;
import javax.lang.model.SourceVersion;

/**
 *
 * @author Agirp
 */

public class AdventureGear extends Item implements Serializable
{
    private String name;
    private String gearType;
    private int healthBoost;
    private int strengthBoost;
    private int defenseBoost;
    private int upgradeLvl;
    
    public AdventureGear()
    {
        super("", "");
        this.gearType = "";
        this.healthBoost = 0;
        this.strengthBoost = 0;
        this.defenseBoost = 0;
        this.upgradeLvl = 0;
    }

    public AdventureGear(String name, String description, String gearType, int healthBoost, int strengthBoost)
    {
        super(name, description);
        this.gearType = gearType;
        this.healthBoost = healthBoost;
        this.strengthBoost = strengthBoost;
        this.defenseBoost = defenseBoost;
        this.upgradeLvl = 0;
    }
    
    /**
     * @return the upgradeLvl
     */
    public int getUpgradeLvl() {
        return upgradeLvl;
    }

    /**
     * @param upgradeLvl the upgradeLvl to set
     */
    public void setUpgradeLvl(int upgradeLvl) {
        this.upgradeLvl = upgradeLvl;
    }
    
    /**
     * @return the healthBoost
     */
    public int getHealthBoost() {
        return healthBoost;
    }

    /**
     * @param healthBoost the healthBoost to set
     */
    public void setHealthBoost(int healthBoost) {
        this.healthBoost = healthBoost;
    }

    /**
     * @return the strengthBoost
     */
    public int getStrengthBoost() {
        return strengthBoost;
    }

    /**
     * @param strengthBoost the strengthBoost to set
     */
    public void setStrengthBoost(int strengthBoost) {
        this.strengthBoost = strengthBoost;
    }

    /**
     * @return the defenseBoost
     */
    public int getDefenseBoost() {
        return defenseBoost;
    }

    /**
     * @param defenseBoost the defenseBoost to set
     */
    public void setDefenseBoost(int defenseBoost) {
        this.defenseBoost = defenseBoost;
    }

    /**
     * @return the gearType
     */
    public String getGearType() {
        return gearType;
    }

    /**
     * @param gearType the gearType to set
     */
    public void setGearType(String gearType) {
        this.gearType = gearType;
    }
    
   public String toString()
   {
       String gearString = this.getName();
       
       if (this.upgradeLvl >= 1)
       {
           gearString += " +" + this.getUpgradeLvl();
       }
       
       return gearString;
   }

}
