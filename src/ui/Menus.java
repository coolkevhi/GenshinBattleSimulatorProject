package ui;

import entities.enemy.RuinGuard;
import entities.player.Player;

import java.util.Scanner;

public class Menus {

    public static Scanner scn = new Scanner(System.in); // scanner object

    //main start menu to start or quit the program
    public static void mainMenu(){
        System.out.println("\n======Genshin Battle Simulator======\nWelcome to the sim!\n[1] Start\n[2] Set Difficulty\n[3] Quit");
        String input = scn.nextLine();
        switch (input){
            case "1":
                start();
                break;
            case "2":
                DifficultySelector.difficultyScreen();
                break;
            case "3":
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;
            default:
                System.out.println("invalid input please try again!");
                mainMenu();
        }
    }

    //after player is finished asks if they want to replay
    public static void rePlay(){
        System.out.println("\n\n[1] Play again\n[2] Back to main menu");
        String input = scn.nextLine();
        switch (input){
            case "1":
                start();
                break;
            case "2":
                mainMenu();
                break;
            default:
                System.out.println("invalid input please try again!");
                rePlay();
        }
    }

    //players choose a reaction and then there team is created and ready for combat
    public static void start(){
        System.out.println("Choose reaction\n[1] Vaporize\n[2] Melt\n[3] Frozen" +
                "\n[4] OverLoaded\n[5] ElectroCharged\n[6] SuperConduct\n[7] Back to main menu");
        String input = scn.nextLine();
        BattleLog battleLog = new BattleLog();
        RuinGuard ruinGuard = new RuinGuard(DifficultySelector.getRuinGuardMaxHP());
        battleLog.clear();
        switch (input){
            case "1":
                battle(1, ruinGuard, battleLog);
                break;
            case "2":
                battle(2, ruinGuard, battleLog);
                break;
            case "3":
                battle(3, ruinGuard, battleLog);
                break;
            case "4":
                battle(4, ruinGuard, battleLog);
                break;
            case "5":
                battle(5, ruinGuard, battleLog);
                break;
            case "6":
                battle(6, ruinGuard, battleLog);
                break;
            case "7":
                mainMenu();
                break;
            default:
                System.out.println("invalid input please try again!");
                start();
        }
    }

    //Starts the combat and keeps it going until Ruin Guard or player reaches zero hp
    public static void battle(int react, RuinGuard ruinGuard, BattleLog battleLog){
        Player player = new Player(react, ruinGuard);
        while ((player.getHealth() != 0) && (ruinGuard.getHealth() != 0)){
            player.turnInfo();
            player.combat();
        }
        if (ruinGuard.getHealth() != 0){
            System.out.println("\n=====Game Over=====");
            System.out.println("You died... play again?");
            battleLog.print();
            rePlay();
            battleLog.clear();
        }
        if (player.getHealth() != 0){
            System.out.println("\n=====Ruin Guard Defeated=====");
            System.out.println("You win!!! play again?");
            battleLog.print();
            rePlay();
            battleLog.clear();
        }
    }
}
