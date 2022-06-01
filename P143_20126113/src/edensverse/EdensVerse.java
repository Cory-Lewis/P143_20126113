/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edensverse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
        EdensVerseUI mainUI = new EdensVerseUI();
        GameController gameController = new GameController();
        gameController.setUI(mainUI);
        Scanner scan = new Scanner(System.in);
        
        mainUI.setVisible(true);
        
        File characterSave = new File("./src/edensverse/charSave.txt");
        
        if (characterSave.length() == 0)
        {
            String charName;
            
            JOptionPane.showMessageDialog(null, "No character save was found. Creating new save...");
            
            charName = (String)JOptionPane.showInputDialog(null, "Please enter a name for the new character", "Naming your Character", JOptionPane.PLAIN_MESSAGE, null, null, null);
            
            Adventurer playerChar = new Adventurer(charName, 60, 30, 6);
            gameController.setPlayerCharacter(playerChar);
            gameController.saveCharacterData();
        }
        else if (characterSave.length() > 0)
        {
            String userInput;
            String charName;
            
            int n = JOptionPane.showConfirmDialog(null, "Character save data found. Would you like to load this character? (y/n)\n"
                    + "*Note*: declining to load your character will create a new save and overwrite your past save.", "Load Character", JOptionPane.YES_NO_OPTION);
                
            if(n == 0)
            {
                gameController.loadCharacterData();
                gameController.printToUI("You come back to your senses, the fight isn't over yet... The next challenger approaches...");
            }
            else if(n == 1)
            {
                charName = (String)JOptionPane.showInputDialog(null, "Please enter a name for the new character", "Naming your Character", JOptionPane.PLAIN_MESSAGE, null, null, null);

                Adventurer playerChar = new Adventurer(charName, 60, 30, 6);
                gameController.setPlayerCharacter(playerChar);
                gameController.saveCharacterData();
                
                JOptionPane.showMessageDialog(null, gameController.readFile("./src/edensverse/entry_pt1.txt") + "\nPress ok to continue...\n");

                JOptionPane.showMessageDialog(null, gameController.getPlayerCharacter().getName() + gameController.readFile("./src/edensverse/entry_pt2.txt") + "\nPress ok to continue...\n");

                JOptionPane.showMessageDialog(null, gameController.readFile("./src/edensverse/entry_pt3.txt") + "\nPress ok to continue...\n");

            }
        }
        
        gameController.printToUI("*System Notice*\nSkill Info:");
        gameController.printToUI("Slash - a basic attack with no cooldown");
        gameController.printToUI("Souleater - an attack that drains the HP of you enemies and gives it to you. 5 turn cooldown");
        gameController.printToUI("Lacerate - Inflicts enemies with a bleed debuff that does 5 damage each turn for 3 turns. 3 turn cooldown");
        gameController.printToUI("Potion - Uses a potion that restores 1/3rd of your max HP");
        gameController.printToUI("Quit - Saves your character info and quits the game\n");
        
        gameController.createMonsters();
        gameController.battle();
        
        AdventureGear bossDrop = new AdventureGear("Adamantite", "A hunk of green metal that can be used to improve a weapon", "weapon", 10, 15);
        BossEnemy mimic = new BossEnemy("Shadow Self", gameController.getPlayerCharacter().getMaxHealth(), gameController.getPlayerCharacter().getStrength() - 18, 50, bossDrop);
        
        gameController.bossBattle(mimic);
        
        JOptionPane.showMessageDialog(null, "You emerge from your trial victorious. You find a ladder to the surface behind where your shadow self was.\n"
                + "You climb the ladder, greeted by blinding daylight. Freedom at last"
                + "To be continued...\n" + "Thanks for playing the Eden's Verse Demo!\nRe-launch the game to either continue your adventure or start anew!");
        
        gameController.saveCharacterData();
    }
    
}
 