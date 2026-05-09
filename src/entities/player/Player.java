package entities.player;

import entities.enemy.RuinGuard;
import entities.entity;
import characters.Character;


import java.util.Scanner;

public class Player extends entity {

    private RuinGuard enemy;
    public int health = 1;
    private Character chr1;
    private Character chr2;
    private String reaction = ""; //reaction chosen
    private int maxHealth = 1; //players max hp

    //constructor that creates character objects depending on the chosen reaction
    public Player(int el, RuinGuard enemy){
        this.enemy = enemy;
        health = 100;
        maxHealth = 100;
        if (el == 1){ //vaporize
            reaction = "Vaporize";
            chr1 = new Character("Amber(Pyro)");
            chr2 = new Character("Xingqiu(Hydro)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if (el == 2) { //melt
            reaction = "Melt";
            chr1 = new Character("Amber(Pyro)");
            chr2 = new Character("Kaeya(Cryo)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if (el == 3) { //Frozen
            reaction = "Frozen";
            chr1 = new Character("Xingqiu(Hydro");
            chr2 = new Character("Kaeya(Cryo)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if (el == 4) { //OverLoaded
            reaction = "OverLoaded";
            chr1 = new Character("Amber(Pyro)");
            chr2 = new Character("Lisa(Electro)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if(el == 5) { //ElecroCharged
            reaction = "ElecroCharged";
            chr1 = new Character("Lisa(Electro)");
            chr2 = new Character("Xingqiu(Hydro");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if(el == 6) { //SuperConduct
            reaction = "SuperConduct";
            chr1 = new Character("Lisa(Electro)");
            chr2 = new Character("Kaeya(Cryo)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }
    }

    @Override
    //used to damage the player and subtract health
    public void damage(int dmg){
        if(health-dmg>=0) {
            health -= dmg;
        }else {
            health = 0;
        }
    }

    @Override
    //returns the players health as an integer
    public int getHealth(){
        return health;
    }

    //The player data printed at the beginning of the turn
    public void turnInfo(){
        System.out.println("\nTurn " + this.blTurn() + "\n-------------------------" + "\n" +
                "formulas.Reaction: " + this.reaction + " (" + chr1.getChrName() + " + " + chr2.getChrName() + ")\n" +
                chr1.getFullChrName() + " Burst: " + chr1.burstCheck() + " Skill: " + chr1.skillCheck() + "\n" +
                chr2.getFullChrName() + " Burst: " + chr2.burstCheck() + " Skill: " + chr2.skillCheck() + "\n" +
                "\nentities.player.Player HP: " + this.getHP(health,maxHealth) + "\n\n" + enemy);

    }

    //starts the combat phase were the player chooses which chr and attacks to use
    public void combat(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\n---characters.Character 1---\nwho acts first? [1] " + chr1.getChrName() + " [2] " + chr2.getChrName());
        String input = scn.nextLine();
        switch (input){
            case "1":
                chr1.attack(enemy);
                if(enemy.getHealth()!=0&&health!=0){
                    System.out.println("---characters.Character 2---\n");
                    chr2.attack(enemy);
                }
                enemy.turnFinshed(this);
                break;
            case "2":
                chr2.attack(enemy);
                System.out.println("---characters.Character 2---\n");
                chr1.attack(enemy);
                enemy.turnFinshed(this);
                break;
            default:
                System.out.println("invalid input please try again");
                this.combat();
        }
    }

}
