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
        System.out.println("You wake up to the sound of the pounding surf. You look around, and the events of last night slowly come back to you."); //#hero
        System.out.println("(Type your answer twice if you are not restarting)");
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
            //when health is zero, player loses #forthelose
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
    private static void game() { //#method1()
        hub();
        playAgain();
    }

    private static void hub() { //#method2()
        int day = 1;
        if (sick == true) { //if you are sick, you lose health
            sickDays = sickDays++;
            System.out.println("You are sick. (- 10 Health)");
            health = health - 10;
        } else {
            sickDays = 0;
        }
        if (sickDays == 3) { //heals after being sick for 3 days
            sick = false;
        }
        System.out.println(health);
        while ((winConditions < 5) & (health > 0)) { //while you have not won and are still alive #while
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
            int event = rand.nextInt(10); //event number
            if (sick == true) {
                System.out.println("You are sick and cannot do anything today."); //if sick
            } else if (event <= 2) { //randomized events
                normalDay();
            } else if (event == 5) {
                wreckedShip();
            } else if (event == 6) {
                sunkenShip();
            } else if (event == 7 || event == 3) {
                storm();
            } else if (event == 8 || event == 4) {
                rain();
            } else if (event == 9) {
                wildBoar();
            }
            //random events
        }
        if (winConditions == 5) { //after the player has gone through enough events #forthewin
            escape();
        }
    }

    private static void normalDay() { //day with no events #method3()
        System.out.println("You wake up bright and early the next day, ready to forage for supplies or build more shelter.");
        input = scan.nextLine();
        if (checkInput("food") || checkInput("suppl")) {//more supplies
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
        System.out.println("You spot a wrecked ship on the shore.");
        input = scan.nextLine();
        if (checkInput("ship")) { //bad choice
            System.out.println("You approach the ship, only to be scythed down by arrows. As you are dying, you spot a giant airship descending...");
            health = 0;
        } else { //survive
            System.out.println("You are wary, and don't go near.");
            winConditions++;
        }
    }

    private static void sunkenShip() {
        System.out.println("You spot a sunken ship in the shallow water.");
        input = scan.nextLine();
        if (checkInput("ship")) { //gets supplies from ship
            System.out.println("(+ 10 Supplies) (+ 5 Lumber) (+ 2 Duct Tape) (+ 2 Weapons)");
            System.out.println("You approach the ship, and find some supplies.");
            supplies = supplies + 10;
            lumber = lumber + 5;
            ducttape = ducttape + 2;
            weapons = weapons + 2;
        } else { //nothing
            System.out.println("You are wary, and don't go near.");
        }
        winConditions++;
    }

    private static void storm() { //event
        System.out.println("A giant storm sweeps through, destroying your shelter and sweeping away some supplies. (Shelter = 0) (- 4 Supplies)");
        shelter = 0;
        supplies = supplies - 4;
        winConditions++;
    }

    private static void rain() {
        System.out.println("It starts raining.");
        if (shelter > 0) { //if you have shelter
            System.out.println("You shelter your shelter, and wait out the rain.");
        } else if (people > 2) { //if you have no shelter, but people
            System.out.println("You huddle with your friends, and stave off hypothermia, but get sick. (Sick)");
            sick = true;
        } else { //has neither
            System.out.println("You get hypothermia and get sick. (- 10 Health) (Sick)");
            health = health - 10;
            sick = true;
        }
        winConditions++;
    }

    private static void wildBoar() { //#method4()
        System.out.println("As you wake up, a wild boar charges out of the bush and snorts at you."); //#enemyobject
        System.out.println("You have to make a split second choice, flee, fight or feed?");
        input = scan.nextLine();
        if (checkInput("flee")) { //instant death
            System.out.println("You flee before the boar, and it chases you down, knocking you over and mauling you to death.");
            System.out.println("You have lost."); //never turn your back to an enemy #lose
            health = 0;
        } else if (checkInput("fight")) {
            if (weapons > 0) { //has weapons
                System.out.println("You grasp your sword and flay the pig. (+ 3 Supplies)");
                supplies = supplies + 3;
                winConditions++;
            } else if (people > 0) { //no weapons, but people to help
                System.out.println("Your crewmember leaps at the boar, and kills it. However, with its last breath, the boar gores him through. (- 1 Person)");
                people = people - 1;
                winConditions++;
            } else { //no weapons
                System.out.println("You punch the boar over and over, getting bloodied, while it gores you.");
                System.out.println("You survive, but barely so. (Health = 10)");
                health = 10;
                winConditions++;
            }
        } else if (checkInput("feed")) {
            if (supplies > 0) { //if you have food
                System.out.println("(- 1 Supply) You feed the boar, and it leaves later in the day.");
                supplies = supplies - 1;
                winConditions++;
            } else { //no food, lose
                System.out.println("You have nothing to feed it with, so you offer yourself.");
                System.out.println("You have lost.");
                health = 0;
            }
        } else { //bad input
            System.out.println("Invalid input, try again");
            wildBoar();
        }
    }

    private static void escape() { //#win
        System.out.println("As you wake up the next day, you spot a ship passing by. Running to the shore, you wave and yell.");
        System.out.println("They pick you up, and are aastonished by your tale.");
        System.out.println("You grow rich and famous from your story.");
        System.out.println("You have won.");
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

    private static boolean checkInput(String check) { //#method5()
        //checks user input with my choice
        return input.toLowerCase().contains(check);
    }

    private static void playAgain() {
        //play again
        System.out.println("Game over.");
        System.out.println("Would you like to play again? (Y/N)");
        again = scan.next().toUpperCase().contains("Y"); //so it still registers if user types y or yes
    }

    private static void exit() {
        System.out.println("Thank you for playing! Please play again soon!"); //exit
    }
}

//Originality: Has randomized events #original