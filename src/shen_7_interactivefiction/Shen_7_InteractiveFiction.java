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
    //stats
    static int health = 80;
    static int supplies = 0;
    static int moreSupplies;
    static int lumber = 0;
    static int moreLumber;
    static int ducttape = 0;
    static int moreTape;
    static int people = 0;
    static int shelter = 0;
    static int weapons = 0;
    static int moreWeapons = 0;
    static boolean sick = false;
    static int sickDays = 0;
    static int winConditions = 0;
    static boolean win = false;
    static boolean carpenter = false;
    static String input;
    static int inputNum;

    public static void main(String[] args) {
        intro();
        while (again) {
            //resets all stats
            health = 80;
            supplies = 0;
            lumber = 0;
            ducttape = 0;
            people = 0;
            shelter = 0;
            weapons = 0;
            winConditions = 0;
            win = false;
            carpenter = false;
            input = "";
            beginning();
            game();
        }
        exit();
    }

    //beginning collection of supplies
    private static void intro() {
        System.out.println("Welcome. This game is going to be brutal. Thank you for your time.");
    }

    private static void beginning() { //first cascade of choices to get resources before reaching the hub
        System.out.println("You wake up to the sound of the pounding surf. You look around, and the events of last night slowly come back to you.");
        shipOrIsland();
        //first choice
    }

    private static void shipOrIsland() {
        System.out.println("You spot your ship, wrecked to one side on a reef. You seem to be stranded on an island. What will you do?");
        input = scan.nextLine();
        input = scan.nextLine();
        //compiler removes first input when the game restarts, so there are two
        if (checkInput("island")) { //checks which path to go down
            // adds supplies beforehand so player cannot exploit
            moreSupplies = rand.nextInt(4);
            supplies = supplies + moreSupplies;
            ducttape = ducttape + 1;
            lumber = lumber + 2;
            System.out.println("(+ " + moreSupplies + " Supplies) (+ 1 Duct Tape) (+ 2 Lumber) You wander deeper into the island, and find some strange fruit and building materials.");
            shelterOrFood();
        } else if (checkInput("ship")) {
            helpOrSelf();
        } else {
            System.out.println("You wander around, indecisive. You soon pass out. (- 10 Health)");
            health -= 10;
            health();
            //when health is zero, player loses
            if (health != 0) {
                shipOrIsland();
            }
        }
    }

    private static void shelterOrFood() {
        //next choice path
        System.out.println("You realize it is getting late. You could start building a shelter from the surrounding foliage, or keep looking for food.");
        input = scan.nextLine();
        if (checkInput("shelter")) {
            firstShelter();
        } else if (checkInput("food")) {
            moarFood();
        } else {
            System.out.println("Not valid. Try again");
            shelterOrFood();
            //invalid input restarts choice
        }
    }

    private static void helpOrSelf() {
        //next choice path
        System.out.println("You enter the ship, and are presented with a fork in the corridor. To one side, you hear cries for help. To the other, you can see the supply storage.");
        input = scan.nextLine();
        if (checkInput("help")) {
            saveOne();
        } else if (checkInput("suppl")) {
            shipSupplies();
        } else {
            System.out.println("Not valid. Try again");
            helpOrSelf();
            //invalid input restarts choice
        }
    }

    private static void saveOne() {
        System.out.println("You enter the cabin, and see the carpenter and a crewman unconscious to the left. There are also five crewmen to the right trapped under a timber beam.");
        System.out.println("The ship is now tilting dangerously and you only have time to save one group.");
        input = scan.nextLine();
        if (checkInput("left")) {
            //special person
            carpenter = true;
            //stat increases
            people = people + 2;
            System.out.println("(+ 2 People) (+ Carpenter) You rescue them and successfully escape.");
            afterShip();
        } else if (checkInput("right")) {
            //more ppl
            people = people + 5;
            System.out.println("(+ 5 People) You rescue them and successfully escape.");
            afterShip();
        } else {
            System.out.println("Not valid. Try again");
            saveOne();
            //invalid input restarts choice
        }
    }

    private static void shipSupplies() {
        //stat stuff so that the line prints out right
        moreSupplies = rand.nextInt(4) + 2;
        supplies = supplies + moreSupplies;
        ducttape = ducttape + 1;
        moreLumber = rand.nextInt(2) + 2;
        lumber = lumber + moreLumber;
        weapons = weapons + 2;
        //prints exactly how much the player got
        System.out.println("(+ " + moreSupplies + " Supplies) (+ 1 Duct tape) (+ " + moreLumber + " Lumber) (+ 2 Weapons) You find some supplies. The ship is now tilting dangerously, so you run.");
        afterShip();
    }

    private static void afterShip() {
        System.out.println("After escaping the ship, you have a little bit of time to explore the island. Do you look for food or build a shelter?");
        input = scan.nextLine();
        //next choice
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
        //basic shelter, less materials
        System.out.println("(- 2 Lumber) (- 1 Duct Tape) (+ 1 Shelter) You now have a small shelter. It is late, so you fall asleep.");
        game(); //to the main game or hub, beginning is done
    }

    private static void moarFood() {
        moreSupplies = rand.nextInt(3) + 2;
        //more supplies for more people
        if (people >= 5) {
            moreSupplies = rand.nextInt(4) + 4;
        }
        supplies = supplies + moreSupplies;
        System.out.println("(+ " + moreSupplies + " Supplies" + ") You find more food, but now it is late and you need to sleep.");
        game(); //to the main game or hub, beginning is done
    }

    //end of beginning, now random events
    private static void game() {
        hub();
        playAgain();
    }

    private static void hub() {
        int day = 1;
        if (sick == true) { //if you are sick, you lose health
            sickDays = sickDays++;
            System.out.println("You are sick. (- 10 Health)");
            health = health - 10;
        } else {
            sickDays = 0;
        }
        System.out.println(health);
        while ((winConditions < 10) & (health > 0)) { //while you have not won and are still alive
            day++; //prints out what day it is
            System.out.println("It is Day " + day);
            moreSupplies = people + 1;
            supplies = supplies - moreSupplies;
            if (supplies <= -1) {
                health = health - 10;
                //loses health if no supplies
                supplies = 0;
            }
            System.out.println("(- " + moreSupplies + " Supplies)");
            checkAll(); //prints supplies
            int event = rand.nextInt(13); //event number
            if (sick == true) {
                System.out.println("You are sick and cannot do anything today."); //if sick
            } else if (event <= 4) { //randomized events
                normalDay();
            } else if (event == 5) {
                wreckedShip();
            } else if (event == 6) {
                sunkenShip();
            } else if (event == 7) {
               storm();
            } else if (event == 8) {
                rain();
            } else if (event == 9) {
                wildBoar();
            } else if (event == 10) {
                
            } else if (event == 11) {
                
            }
            //random events
        }
    }

    private static void normalDay() { // day with no events
        System.out.println("You wake up bright and early the next day, ready to forage for supplies or build more shelter.");
        input = scan.nextLine();
        if (checkInput("food")) {//more supplies
            moreSupplies = rand.nextInt(3) + 2;
            //more supplies for more people
            for (int i = 0; i < people; i = i + 2) {
                moreSupplies++;
            }
            supplies = supplies + moreSupplies;
            System.out.println("(+ " + moreSupplies + " Supplies" + ") You find more food.");
            game(); //to the hub
        } else if (checkInput("shelter")) {
            int shelter1 = 0;//how much your supplies will allow
            int shelter2 = 0;
            for (int i = 0; i < lumber; i = i + 2) {
                shelter1++;
            }
            for (int i = 0; i < ducttape; i++) {
                shelter2++;
            }
            if (shelter1 > shelter2) {
                System.out.println("You can build " + shelter2 + " shelter.");
            } else {
                System.out.println("You can build " + shelter1 + " shelter.");
            }
            System.out.println("How much shelter do you want to build?");
            inputNum = scan.nextInt();//gives choice to preserve some supplies
            if (inputNum <= shelter1 & inputNum >= 0) {
                shelter = shelter + inputNum;
                System.out.println("(+ " + inputNum + " Shelter) You build more shelter.");
            } else {
                System.out.println("Invalid number");
                normalDay();
            }//resets
        } else {
            System.out.println("Invalid input, try again");
            normalDay();
        }//resets //then back to hub
    }

    private static void wreckedShip() {
        System.out.println("");
        winConditions++;
    }

    private static void sunkenShip() {
        System.out.println("");
        winConditions++;
    }
    
    private static void storm() {
        System.out.println("");
        winConditions++;
    }

    private static void rain() {
        System.out.println("It starts raining.");
        if (shelter > 0) {
            System.out.println("You shelter your shelter, and wait out the rain.");
        } else if (people > 2) {
            System.out.println("You huddle with your friends, and stave off hypothermia, but get sick. (Sick)");
            sick = true;
        } else {
            System.out.println("You get hypothermia and get sick. (- 10 Health) (Sick)");
            health = health - 10;
            sick = true;
        }
        winConditions++;
    }

    private static void wildBoar() {
        System.out.println("");
        winConditions++;
    }

    //functionality methods
    private static void health() {
        //checks health
        System.out.println("You have " + health + " health.");
    }

    private static void checkAll() {
        //tells user what their stats are
        System.out.println("You have " + health + " health.");
        System.out.println("You have " + supplies + " supplies.");
        System.out.println("You have " + lumber + " lumber.");
        System.out.println("You have " + ducttape + " ducttape.");
        System.out.println("You have " + people + " people.");
        System.out.println("You have " + shelter + " shelter.");
        System.out.println("You have " + weapons + " weapons.");
    }

    private static boolean checkInput(String check) {
        //checks user input with my choice
        return input.toLowerCase().contains(check);
    }

    private static void playAgain() {
        //play again
        System.out.println("Game over.");
        System.out.println("Would you like to play again? (Y/N)");
        again = scan.next().toUpperCase().contains("Y"); //so it still regisers if user types y or yes
    }

    private static void exit() {
        System.out.println("Thank you for playing! Please play again soon!"); //exit
    }
}