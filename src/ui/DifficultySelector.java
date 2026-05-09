package ui;

import java.util.Scanner;

public class DifficultySelector {

    private static String difficulty = "beginner"; //current difficulty automatically set to beginner
    private static Scanner scn = new Scanner(System.in);

    //goes to set difficulty screen
    public static void difficultyScreen(){
        System.out.println("\n----Set Difficulty----\n**difficulty affects Ruin Guards hp**");
        System.out.println("Current Difficulty: " + difficulty);
        System.out.println("[1] beginner\n[2] easy\n[3] medium\n[4] hard\n[5] Back to main menu");
        String input = scn.nextLine();
        switch (input){
            case "1":
                difficulty = "beginner";
                System.out.println("difficulty set to " + difficulty);
                difficultyScreen();
                break;
            case "2":
                difficulty = "easy";
                System.out.println("difficulty set to " + difficulty);
                difficultyScreen();
                break;
            case "3":
                difficulty = "medium";
                System.out.println("difficulty set to " + difficulty);
                difficultyScreen();
                break;
            case "4":
                difficulty = "hard";
                System.out.println("difficulty set to " + difficulty);
                difficultyScreen();
                break;
            case "5":
                Menus.mainMenu();
                break;
            default:
                System.out.println("invalid input please try again!");
                difficultyScreen();
        }
    }

    //returns Ruin Guards MaxHP depending on difficulty
    public static int getRuinGuardMaxHP(){
        if (difficulty=="easy"){
            return 300;
        }else if (difficulty=="medium"){
            return 400;
        }else if (difficulty=="hard"){
            return 500;
        }else if (difficulty=="beginner"){
            return 200;
        }
        return -1;
    }

}
