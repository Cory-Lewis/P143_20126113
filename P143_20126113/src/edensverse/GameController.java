/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edensverse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agirp
 */
public class GameController 
{
    private Adventurer playerCharacter;
    public ArrayList<Enemy> enemyList = new ArrayList<>();
    
    /**
     * @return the playerCharacter
     */
    public Adventurer getPlayerCharacter() 
    {
        return playerCharacter;
    }

    /**
     * @param playerCharacter the playerCharacter to set
     */
    public void setPlayerCharacter(Adventurer playerCharacter) 
    {
        this.playerCharacter = playerCharacter;
    }
    
    public void saveCharacterData() throws FileNotFoundException, IOException
    {
        System.out.println("Saving...");
        FileOutputStream fileOut = new FileOutputStream("./src/edensverse/charSave.txt");
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(this.getPlayerCharacter());
        objOut.flush();
        System.out.println("Save successful!\n");
    }
    
    public void loadCharacterData() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        System.out.println("Loading...");
        FileInputStream fileIn = new FileInputStream("./src/edensverse/charSave.txt");
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        this.playerCharacter = (Adventurer) objIn.readObject();
        System.out.println("Load successful!\n");
    }
    
    public String readFile(String filePath) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuffer fileString = new StringBuffer();
        String currentLine;
        
        try {
            while((currentLine = reader.readLine()) != null)
            {
                fileString.append(currentLine + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        reader.close(); 
        
        return fileString.toString();
    }
    
    //use random numbers to generate a unique list of monsters
    public void createMonsters(int numOfMonsters)
    {
        Random rand = new Random();
        int monsterCode = 0;
        
        for(int i = 0; i < numOfMonsters +1; i++)
        {
            monsterCode = rand.nextInt(3);
            
            if (monsterCode == 0)
            {
                enemyList.add(new Enemy("Coerl", 40, 10, 15));
            }
            else if (monsterCode == 1)
            {
                enemyList.add(new Enemy("Skeleton", 50, 15, 10));
            }
            else
            {
                enemyList.add(new Enemy("Giant Rat", 30, 14, 15));
            }
        }
    }
    
    //iterate through the list of monsters and fight, terminate the program if the user dies.
    @SuppressWarnings("empty-statement")
    public void battle(Scanner scan) throws IOException
    {
        String userInput;
        //cooldowns and damage for hard-hitting moves
        int lacerateCounter = 0;
        int lacerateDamage = 5;
        int soulEaterCounter = 0;
        
        for(int i = 0; i < this.enemyList.size(); i++)
        {
            System.out.println("A " + this.enemyList.get(i).getName() + " approaches!");
            lacerateDamage = 0;
            
            while(this.playerCharacter.getHealthPoints() > 0 && this.enemyList.get(i).getHealthPoints() > 0)
            {
                System.out.println(this.playerCharacter.getName() + " HP: " + this.playerCharacter.getHealthPoints() + "                " + this.enemyList.get(i).getName() + " HP: " + this.enemyList.get(i).getHealthPoints());
                System.out.println("Options:    Slash   |   Souleater   |   Lacerate   |   potion x" + this.playerCharacter.getInventory().size() + " |   Quit");
                boolean quitBool;
                
                do
                {
                    quitBool = false;
                    userInput = scan.nextLine();
                    
                    //check which option the user chose
                    switch(userInput.toLowerCase())
                    {
                        case "slash":
                            System.out.println("\n" + this.playerCharacter.getName() + " uses slash on the " + this.enemyList.get(i).getName());
                            this.playerCharacter.attack(this.enemyList.get(i));
                            quitBool = true;
                            break;
                        
                        case "souleater":
                            if (soulEaterCounter == 0)
                            {
                                this.playerCharacter.soulEater(this.enemyList.get(i));
                                //set the souleater cooldown to 5
                                soulEaterCounter = 5;
                                System.out.println("\nYou may use souleater in 5 turns\n");
                                quitBool = true;
                                break;
                            }
                            else if (soulEaterCounter > 0)
                            {
                                System.out.println("\nYou cannot use souleater yet... You may use it in " + soulEaterCounter + " turns.\n");
                                quitBool = true;
                                break;
                            }    
                        case "lacerate":
                            if (lacerateCounter == 0)
                            {
                                lacerateCounter = 3;
                                lacerateDamage = 5;
                                this.playerCharacter.lacerate(this.enemyList.get(i));
                                System.out.println("You may use lacerate again in 3 turns.\n");
                                quitBool = true;
                                break;
                            }
                            else if(lacerateCounter > 0)
                            {
                                System.out.println("You cannot use lacerate yet... You may use it in " + lacerateCounter + " turns.\n");
                                quitBool = true;
                                break;
                            }
                        case "potion":
                            this.playerCharacter.usePotion();
                            quitBool = true;
                            break;    
                        case "quit":
                            this.saveCharacterData();
                            System.out.println("Quitting Game...");
                            System.exit(0);    
                        default:
                            System.out.println("You did not enter a valid option, please try again.\n");
                    }
                }while(quitBool == false);
                
                if(soulEaterCounter > 0)
                {
                    soulEaterCounter -= 1;
                }
                
                if (lacerateCounter > 0 && lacerateDamage != 0)
                {
                    System.out.println("The enemy suffers 5 bleed damage.\n");
                    enemyList.get(i).setHealthPoints(this.enemyList.get(i).getHealthPoints() - 5);
                    lacerateCounter -= 1;
                }
                else if(lacerateCounter > 0 && lacerateDamage == 0)
                {
                    lacerateCounter -= 1;
                }
                
                
                if (this.enemyList.get(i).getHealthPoints() <= 0)
                {
                    //drop loot on enemy death
                    this.enemyList.get(i).dropItem(this.playerCharacter);
                    this.playerCharacter.updateLvl();
                    System.out.println("\n");
                    break;
                }
                else
                {
                    //enemy attacks player
                    System.out.println("The " +this.enemyList.get(i).getName() + " attacks " + this.playerCharacter.getName());
                    this.enemyList.get(i).attack(this.playerCharacter);
                }   
            }
            
            if (this.playerCharacter.getHealthPoints() <= 0)
                {
                    System.out.println("Oh dear, you died! Try again next time.");
                    System.out.println("Quitting game...");
                    System.exit(0);
                }
            
        }
        
        System.out.println("The onslaught of monsters seems to be over for now. Seeing a large wooden door ahead, you push it open and continue into the dark.");
    }

    
    public void bossBattle(BossEnemy mimic, Scanner scan) throws IOException
    {
        System.out.println("From the shadows you final trial appears! This face looks familiar... Because it's yours!\n");
        
        String userInput;
        //cooldowns and damage for hard-hitting moves
        int lacerateCounter = 0;
        int lacerateDamage = 5;
        int soulEaterCounter = 0;
        
        while(this.playerCharacter.getHealthPoints() > 0 && mimic.getHealthPoints() > 0)
            {
                System.out.println(this.playerCharacter.getName() + " HP: " + this.playerCharacter.getHealthPoints() + "                " + mimic.getName() + " HP: " + mimic.getHealthPoints());
                System.out.println("Options:    Slash   |   Souleater   |   Lacerate   |   potion x" + this.playerCharacter.getInventory().size() + " |   Quit");
                boolean quitBool;
                
                do
                {
                    quitBool = false;
                    userInput = scan.nextLine();
                    
                    //check which option the user chose
                    switch(userInput.toLowerCase())
                    {
                        case "slash":
                            System.out.println("\n" + this.playerCharacter.getName() + " uses slash on the " + mimic.getName());
                            this.playerCharacter.attack(mimic);
                            quitBool = true;
                            break;
                        
                        case "souleater":
                            if (soulEaterCounter == 0)
                            {
                                this.playerCharacter.soulEater(mimic);
                                //set the souleater cooldown to 5
                                soulEaterCounter = 5;
                                System.out.println("\nYou may use souleater in 5 turns\n");
                                quitBool = true;
                                break;
                            }
                            else if (soulEaterCounter > 0)
                            {
                                System.out.println("\nYou cannot use souleater yet... You may use it in " + soulEaterCounter + " turns.\n");
                                quitBool = true;
                                break;
                            }    
                        case "lacerate":
                            if (lacerateCounter == 0)
                            {
                                lacerateCounter = 3;
                                lacerateDamage = 5;
                                this.playerCharacter.lacerate(mimic);
                                System.out.println("You may use lacerate again in 3 turns.\n");
                                quitBool = true;
                                break;
                            }
                            else if(lacerateCounter > 0)
                            {
                                System.out.println("You cannot use lacerate yet... You may use it in " + lacerateCounter + " turns.\n");
                                quitBool = true;
                                break;
                            }
                        case "potion":
                            this.playerCharacter.usePotion();
                            quitBool = true;
                            break;    
                        case "quit":
                            this.saveCharacterData();
                            System.out.println("Quitting Game...");
                            System.exit(0);    
                        default:
                            System.out.println("You did not enter a valid option, please try again.\n");
                    }
                }while(quitBool == false);
                
                if(soulEaterCounter > 0)
                {
                    soulEaterCounter -= 1;
                }
                
                if (lacerateCounter > 0 && lacerateDamage != 0)
                {
                    System.out.println("The enemy suffers 5 bleed damage.\n");
                    mimic.setHealthPoints(mimic.getHealthPoints() - 5);
                    lacerateCounter -= 1;
                }
                else if(lacerateCounter > 0 && lacerateDamage == 0)
                {
                    lacerateCounter -= 1;
                }
                
                
                if (mimic.getHealthPoints() <= 0)
                {
                    //drop loot on enemy death
                    mimic.dropItem(this.playerCharacter);
                    this.playerCharacter.updateLvl();
                    System.out.println("\n");
                    break;
                }
                else
                {
                    //enemy attacks player
                    System.out.println("The " + mimic.getName() + " attacks " + this.playerCharacter.getName());
                    mimic.attack(this.playerCharacter);
                }   
            }
            
            if (this.playerCharacter.getHealthPoints() <= 0)
                {
                    System.out.println("Oh dear, you died! Try again next time.");
                    System.out.println("Quitting game...");
                    System.exit(0);
                }
            
        }
}
