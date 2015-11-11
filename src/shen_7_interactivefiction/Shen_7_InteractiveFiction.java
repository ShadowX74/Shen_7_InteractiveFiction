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
    }
    
    private static void beginning() {
       System.out.println("You wake up to the sound of the pounding surf. You look around, and the events of last night slowly come back to you.");
       System.out.println("You spot your ship, wrecked to one side on a reef. You seem to be stranded on an island. What will you do?");
       input = scan.nextLine();
       input = scan.nextLine();
       if (checkInput("island")) {
           
       } else if (checkInput("ship")) {
           System.out.println("You enter the ship, and are presented with a fork in the corridor. To one side, you hear cries for help. To the other, you can see the supply storage.");
           input = scan.nextLine();
       } else {
           System.out.println("You wander around, indecisive. You soon pass out.");
       }
    }
    
    private static void game() {
        playAgain();
    }
    
    private static void health() {
        System.out.println("You have " + health + " health.");
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
