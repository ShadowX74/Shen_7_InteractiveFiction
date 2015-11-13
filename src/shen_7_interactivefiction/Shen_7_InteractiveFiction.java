/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shen_7_interactivefiction;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ShadowX
 */
public class Shen_7_InteractiveFiction {

    /**
     * @param args the command line arguments
     */
    
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static boolean again = true;
    static int health = 80;
    static int supplies = 0;
    static int moreSupplies;
    static int lumber = 0;
    static int moreLumber;
    static int ducttape = 0;
    static int moreTape;
    static int people = 0;
    static int shelter = 0;
    static int winConditions = 0;
    static boolean win = false;
    static boolean carpenter = false;
    static String input;
    
    public static void main(String[] args) {
        intro();
        while (again) {
            beginning();
            game();
            health = 80;
            supplies = 0;
            lumber = 0;
            ducttape = 0;
            people = 0;
            shelter = 0;
            winConditions = 0;
            win = false;
            carpenter = false;
            input = "";
        }
        exit();
    }
    
    private static void intro() {
        System.out.println("Welcome. This game is going to be brutal. Thank you for your time.");
        System.out.println("You wake up to the sound of the pounding surf. You look around, and the events of last night slowly come back to you.");
    }
    
    private static void beginning() {
        shipOrIsland();
    }
    
    private static void shipOrIsland() {
        System.out.println("You spot your ship, wrecked to one side on a reef. You seem to be stranded on an island. What will you do?");
        input = scan.nextLine();
        input = scan.nextLine();
        if (checkInput("island")) {
            moreSupplies = rand.nextInt(4);
            supplies = supplies + moreSupplies;
            ducttape = ducttape + 1;
            lumber = lumber + 2;
            System.out.println("(+ " + moreSupplies + "Supplies) (+ 1 Duct Tape) (+ 2 Lumber) You wander deeper into the island, and find some strange fruit and building materials.");
            shelterOrFood();
        } else if (checkInput("ship")) {
            helpOrSelf();
        } else {
            System.out.println("You wander around, indecisive. You soon pass out. (- 10 Health)");
            health = health - 10;
            health();
            if (health != 0) {
                shipOrIsland();
            }
        }
    }
    
    private static void shelterOrFood() {
        System.out.println("You realize it is getting late. You could start building a shelter from the surrounding foliage, or keep looking for food.");
        if (checkInput("shelter")) {
            firstShelter();
        } else if (checkInput("food")) {
             moarFood();
        } else {
            System.out.println("Not valid. Try again");
            shelterOrFood();
        }
    }
    
    private static void helpOrSelf() {
        System.out.println("You enter the ship, and are presented with a fork in the corridor. To one side, you hear cries for help. To the other, you can see the supply storage.");
        input = scan.nextLine();
        if (checkInput("help")) {
            saveOne();
        } else if (checkInput("suppl")) {
            shipSupplies();
        } else {
            System.out.println("Not valid. Try again");
            helpOrSelf();
        }
    }
    
    public static void saveOne() {
        System.out.println("You enter the cabin, and see the carpenter and a crewman unconscious to the left. There are also five crewmen to the right trapped under a timber beam.");
        System.out.println("The ship is now tilting dangerously and you only have time to save one group.");
        if (checkInput("left")) {
            carpenter = true;
            people = people + 1;
            System.out.println("You rescue them and successfully escape.");
            afterShip();
        } else if (checkInput("right")) {
            people = people + 5;
            System.out.println("You rescue them and successfully escape.");
            afterShip();
        } else {
            System.out.println("Not valid. Try again");
            saveOne();
        }
    }
    
    public static void shipSupplies() {
        moreSupplies = rand.nextInt(4) + 2;
        supplies = supplies + moreSupplies;
        ducttape = ducttape + 1;
        moreLumber = rand.nextInt(2) + 2;
        lumber = lumber + moreLumber;
        System.out.println("(+ " + moreSupplies + "Supplies) (+ 1 Duct tape) (+ " + moreLumber + "Lumber) You find some supplies. The ship is now tilting dangerously, so you run.");
        afterShip();
    }
    
    public static void afterShip() {
        System.out.println("After escaping the ship, you have a little bit of time to explore the island. Do you look for food or build a shelter?");
        if (checkInput("shelter")) {
            firstShelter();
        } else if (checkInput("food")) {
            moarFood();
        } else {
            System.out.println("Not valid. Try again");
            shelterOrFood();
        }
    }
    
    private static void firstShelter() {
        lumber = lumber - 2;
        ducttape = ducttape - 1;
        shelter = shelter + 1;
        System.out.println("(- 2 Lumber) (- 1 Duct Tape) (+ 1 Shelter) You now have a small shelter. It is late, so you fall asleep.");
        game();
    }
    
    private static void moarFood() {
        moreSupplies = rand.nextInt(3) + 2;
        if (people >= 5) {
            moreSupplies = rand.nextInt(4) + 4;
        }
        System.out.println("(+ " + moreSupplies + ") You find more food, but now it is late and you need to sleep.");
        game();
    }
    
    
    
    private static void game() {
        while ((winConditions < 10) & (health > 0)) {
        //random event
    }
        playAgain();
    }
    
    private static void health() {
        System.out.println("You have " + health + " health.");
    }
    
    private static void checkAll() {
        System.out.println("You have " + health + " health.");
        System.out.println("You have " + supplies + " supplies.");
        System.out.println("You have " + lumber + " lumber.");
        System.out.println("You have " + ducttape + " ducttape.");
        System.out.println("You have " + people + " people.");
        System.out.println("You have " + shelter + " shelter.");
    }
    
    private static boolean checkInput(String check){
        return input.toLowerCase().contains(check);
    }
      
    private static void playAgain() {
        System.out.println("Game over.");
        System.out.println("Would you like to play again? (Y/N)");
        again = scan.next().toUpperCase().contains("Y");
    }
    
    private static void exit() {
        System.out.println("Thank you for playing! Please play again soon!");
    }
}
