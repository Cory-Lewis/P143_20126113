/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edensverse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Agirp
 */
public class EdensVerse {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException 
    {
        GameController gameController = new GameController();
        Scanner scan = new Scanner(System.in);
        
        File characterSave = new File("./src/edensverse/charSave.txt");
        
        if (characterSave.length() == 0)
        {
            String charName;
            
            System.out.println("No character save was found. Creating new save...");
            System.out.println("Please enter a name for your character: ");
            
            charName = scan.next();
            
            Adventurer playerChar = new Adventurer(charName, 60, 30);
            gameController.setPlayerCharacter(playerChar);
            gameController.saveCharacterData();
        }
        else if (characterSave.length() > 0)
        {
            String userInput;
            String charName;
                    
            System.out.println("Character save data found. Would you like to load this character? (y/n)");
            System.out.println("*Note*: declining to load your character will create a new save and overwrite your past save.");
            
            
            do
            {
                userInput = scan.next();
                scan.nextLine();
                
                if(userInput.equalsIgnoreCase("y"))
                {
                    gameController.loadCharacterData();
                    break;
                }
                else if(userInput.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter a name for the new character");
                    
                    charName = scan.nextLine();
            
                    Adventurer playerChar = new Adventurer(charName, 60, 30);
                    gameController.setPlayerCharacter(playerChar);
                    gameController.saveCharacterData();
                    break;
                }
                else
                {
                    System.out.println("Incorrect input detected. Please answer with \"y\" or \"n\".");
                }
            }
            while(true);
        }
        
        System.out.println(gameController.readFile("./src/edensverse/entry_pt1.txt"));
        System.out.println("Press enter to continue...");
        scan.nextLine();
        
        System.out.println(gameController.getPlayerCharacter().getName() + gameController.readFile("./src/edensverse/entry_pt2.txt"));
        System.out.println("Press enter to continue...");
        scan.nextLine();
        
        System.out.println(gameController.readFile("./src/edensverse/entry_pt3.txt"));
        System.out.println("Press enter to continue...");
        scan.nextLine();
        
        System.out.println("*System Notice*\nSkill Info:");
        System.out.println("Slash - a basic attack with no cooldown");
        System.out.println("Souleater - an attack that drains the HP of you enemies and gives it to you. 5 turn cooldown");
        System.out.println("Lacerate - Inflicts enemies with a bleed debuff that does 5 damage each turn for 3 turns. 3 turn cooldown");
        System.out.println("Potion - Uses a potion that restores 1/3rd of your max HP");
        System.out.println("Quit - Saves your character info and quits the game\n");
        
        gameController.createMonsters(4);
        gameController.battle(scan);
        
        AdventureGear bossDrop = new AdventureGear("Adamantite", "A hunk of green metal that can be used to improve a weapon", "weapon", 10, 15);
        BossEnemy mimic = new BossEnemy("Shadow Self", gameController.getPlayerCharacter().getMaxHealth(), gameController.getPlayerCharacter().getStrength() - 18, 50, bossDrop);
        
        gameController.bossBattle(mimic, scan);
        
        System.out.println("You emerge from your trial victorious. You find a ladder to the surface behind where your shadow self was.");
        System.out.println("You climb the ladder, greeted by blinding daylight. Freedom at last");
        System.out.println("To be continued...\n");
        
        gameController.saveCharacterData();
        
        System.out.println("Thanks for playing the Eden's Verse Demo!");
    }
    
}
 