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
    static int lumber = 0;
    static int ducttape = 0;
    static int people = 0;
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
            winConditions = 0;
            win = false;
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
        supplies = supplies + rand.nextInt(4);
        ducttape = ducttape + 1;
        lumber = lumber + 2;
        System.out.println("(+ 1-3 Supplies) (+ 1 Duct Tape) (+ 2 Lumber) You wander deeper into the island, and find some strange fruit and building materials.");
        System.out.println("You realize it is getting late. You could start building a shelter from the surrounding foliage, or keep looking for food.");
        if (checkInput("shelter")) {
            
        } else if (checkInput("food")) {
             
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
             
        } else {
            System.out.println("Not valid. Try again");
            helpOrSelf();
        }
    }
    
    public static void saveOne() {
        System.out.println("You enter the cabin, and see the carpenter and a crewman unconscious to the left. There are also five crewmen to the right trapped under a timber beam.");
        System.out.println("The ship is now tilting dangerously and you only have time to save one group.");
    }
    
    public static void shipSupplies() {
        supplies = supplies + rand.nextInt(4) + 2;
        ducttape = ducttape + 1;
        lumber = lumber + rand.nextInt(2) + 2;
        System.out.println("(+ 2-5 Supplies) (+ 1 Duct tape) (+ 2-3 Lumber) You find some supplies. The ship is now tilting dangerously, so you run.");
    }
    
    public static void afterShip() {
        System.out.println("After escaping the ship, you have a little bit of time to explore the island. Do you look for food or build a shelter?");
    }
    
    private static void game() {
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
