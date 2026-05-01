import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        menu();

    }
    public static void menu(){
        Scanner scn = new Scanner(System.in);
        System.out.println("======Genshin Battle Simulator======\nWelcome to the sim!\n[1] Start\n[2] Quit");
        String input = scn.nextLine();
        switch (input){
            case "1":
                start();
                break;
            case "2":
                System.out.println("Thanks for playing!");
                break;
            default:
                System.out.println("invalid input please try again!");
                menu();
        }
    }
    public static void rePlay(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\n\n[1] Play again\n[2] Back to main menu");
        String input = scn.nextLine();
        switch (input){
            case "1":
                start();
                break;
            case "2":
                menu();
                break;
            default:
                System.out.println("invalid input please try again!");
                rePlay();
        }
    }

    public static void start(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Choose reaction\n[1] Vaporize\n[2] Melt\n[3] Frozen" +
                "\n[4] OverLoaded\n[5] ElectroCharged\n[6] SuperConduct\n[7] Back to main menu");
        String input = scn.nextLine();
        BattleLog battleLog = new BattleLog();
        RuinGuard ruinGuard = new RuinGuard(battleLog);
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
                menu();
                break;
            default:
                System.out.println("invalid input please try again!");
                start();
        }
    }
    public static void battle(int react, RuinGuard ruinGuard, BattleLog battleLog){
        Player player = new Player(react, ruinGuard, battleLog);
        while ((player.getHealth() != 0) && (ruinGuard.getHealth() != 0)){
            player.turnInfo();
            player.combat();
        }
        if(ruinGuard.getHealth() != 0){
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