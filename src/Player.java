import java.util.Scanner;

public class Player {

    private RuinGuard enemy;
    public int health = 1;
    private Character chr1;
    private Character chr2;
    private String reaction = ""; //reaction chosen
    private BattleLog bl; //battle log object

    //constructor that creates character objects depending on the chosen reaction
    public Player(int el, RuinGuard enemy, BattleLog bl){
        this.bl = bl;
        this.enemy = enemy;
        health = 100;
        if(el == 1){ //vaporize
            reaction = "Vaporize";
            chr1 = new Character("Amber(Pyro)");
            chr2 = new Character("Xingqiu(Hydro)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if(el == 2) { //melt
            reaction = "Melt";
            chr1 = new Character("Amber(Pyro)");
            chr2 = new Character("Kaeya(Cryo)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if(el == 3) { //Frozen
            reaction = "Frozen";
            chr1 = new Character("Xingqiu(Hydro");
            chr2 = new Character("Kaeya(Cryo)");
            System.out.println("\nYour team is "+chr1.getFullChrName() + " and " + chr2.getFullChrName() +"\n");
        }else if(el == 4) { //OverLoaded
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
    //returns player health as an integer
    public int getHealth(){
        return health;
    }

    //returns the players health that the player sees including health bar
    public String getHP(){
        switch (health){
            case 100:
                return "■■■■■■■■■■ "+ health + "/100";
            case 95:
                return "■■■■■■■■■◧ "+ health + "/100";
            case 90:
                return "■■■■■■■■■□ "+ health + "/100";
            case 85:
                return "■■■■■■■■◧□ "+ health + "/100";
            case 80:
                return "■■■■■■■■□□ "+ health + "/100";
            case 75:
                return "■■■■■■■◧□□ "+ health + "/100";
            case 70:
                return "■■■■■■■□□□ "+ health + "/100";
            case 65:
                return "■■■■■■◧□□□ "+ health + "/100";
            case 60:
                return "■■■■■■□□□□ "+ health + "/100";
            case 55:
                return "■■■■■◧□□□□ "+ health + "/100";
            case 50:
                return "■■■■■□□□□□ "+ health + "/100";
            case 45:
                return "■■■■◧□□□□□ "+ health + "/100";
            case 40:
                return "■■■■□□□□□□ "+ health + "/100";
            case 35:
                return "■■■◧□□□□□□ "+ health + "/100";
            case 30:
                return "■■■□□□□□□□ "+ health + "/100";
            case 25:
                return "■■◧□□□□□□□ "+ health + "/100";
            case 20:
                return "■■□□□□□□□□ "+ health + "/100";
            case 15:
                return "■◧□□□□□□□□ "+ health + "/100";
            case 10:
                return "■□□□□□□□□□ "+ health + "/100";
            case 5:
                return "◧□□□□□□□□□ "+ health + "/100";
        }
        return "";
    }

    //used to damage the player and subtract health
    public void damage(int dmg){
        if(health-dmg>=0) {
            health -= dmg;
        }else {
            health = 0;
        }
    }

    //The player data printed at the beginning of the turn
    public void turnInfo(){
        System.out.println("\nTurn " + bl.getTurn() + "\n-------------------------" + "\n" +
                "Reaction: " + this.reaction + " (" + chr1.getChrName() + " + " + chr2.getChrName() + ")\n" +
                chr1.getFullChrName() + " Burst: " + chr1.burstCheck() + " Skill: " + chr1.skillCheck() + "\n" +
                chr2.getFullChrName() + " Burst: " + chr2.burstCheck() + " Skill: " + chr2.skillCheck() + "\n" +
                "\nPlayer HP: " + this.getHP() + "\n\n" + enemy);

    }

    //starts the combat phase were the player chooses which chr and attacks to use
    public void combat(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\n---Character 1---\nwho acts first? [1] " + chr1.getChrName() + " [2] " + chr2.getChrName());
        String input = scn.nextLine();
        switch (input){
            case "1":
                chr1.attack(enemy);
                System.out.println("---Character 2---\n");
                if(enemy.getHealth()!=0&&health!=0){
                    chr2.attack(enemy);
                }
                enemy.turnFinshed(this);
                break;
            case "2":
                chr2.attack(enemy);
                System.out.println("---Character 2---\n");
                chr1.attack(enemy);
                enemy.turnFinshed(this);
                break;
            default:
                System.out.println("invalid input please try again");
                this.combat();
        }
    }

}
