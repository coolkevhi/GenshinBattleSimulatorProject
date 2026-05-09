package characters;

import entities.enemy.RuinGuard;
import ui.BattleLog;
import formulas.Reaction;

import java.util.Scanner;

public class Character {

    String[] characters = {"Amber(Pyro)", "Xingqiu(Hydro)", "Kaeya(Cryo)", "Lisa(Electro)"};

    private String name = "";
    private String element = ""; //element of the chr
    private int energy = 0; //current burst energy
    private int maxEnergy = 0; //max burst energy
    private int skillCooldown = 0; //amount of turns skill is on cooldown
    private BattleLog bl = new BattleLog();

    //constructor that finds the chr and sets the name, element, energy, and max energy
    public Character(String name){
        for (String chr : characters){
            int para = chr.indexOf("(") + 1;
            if (chr.contains(name)){
                 this.name = name;
                 element = chr.substring(para, chr.length()-1);
                 if (this.name == "Xingqiu(Hydro)"){
                     maxEnergy = 80;
                 }else {
                     maxEnergy = 60;
                 }
            }
        }
    }

    //basic attack for all chrs that does 10dmg and adds 5 burst energy
    public void normalAtk(RuinGuard enemy){
        enemy.damage(10);
        //when amber does normal attack has chance to stun Ruin Guard
        if (this.name.equals("Amber(Pyro)")){
            enemy.amberAttacks();
        }
        if (energy<maxEnergy){
            if (energy+5>maxEnergy){
                energy = maxEnergy;
            }else {
                energy += 5;
            }
        }
    }

    //skill attack that does 20dmg and adds 10 burst energy
    //sets aura depending on chr element or does reaction if another aura is already set
    public void skillAtk(RuinGuard enemy) {
        int dmg = 20;
        skillCooldown = 2;
        if (enemy.getAura().equals("none")) {
            enemy.setAura(element);
            enemy.addAura(2.0);
            enemy.damage(dmg);
        }else {
            Reaction r = new Reaction(enemy,element,dmg);
            r.doReaction();
        }
        if (energy < maxEnergy) {
            if (energy + 10 > maxEnergy) {
                energy = maxEnergy;
            } else {
                energy += 10;
            }
        }
    }

    //burst attack that does 30dmg if max burst energy is reached and then resets it after
    //sets aura depending on chr element or does reaction if another aura is already set
    public void burstAtk(RuinGuard enemy) {
        int dmg = 30;
        if (enemy.getAura().equals("none")) {
            enemy.setAura(element);
            enemy.addAura(2.0);
            enemy.damage(dmg);
        }else {
            Reaction r = new Reaction(enemy,element,dmg);
            r.doReaction();
        }
    }

    //returns just chr name
    public String getChrName(){
        int para = name.indexOf("(");
        return name.substring(0, para);
    }

    //returns chr name + element
    public String getFullChrName(){
        return name;
    }

    //checks if skill is on cooldown
    public String skillCheck(){
        if (skillCooldown==0){
            return "ready";
        }else {
            return "on cooldown";
        }
    }

    //checks if burst energy is reached to be able to do burst attack
    public String burstCheck() {
        return energy + "/" + maxEnergy;
    }

    //attacking phase of this chr to decide which attack to use
    public void attack(RuinGuard enemy){
        if (skillCooldown!=0){
            skillCooldown--;
        }
        Scanner scn = new Scanner(System.in);
        if (skillCooldown!=0){
            System.out.println(this.getChrName() + " skill on cooldown\n");
        }
        System.out.println("[1] Normal Attack (physical, 10 dmg, +5 burst)");
        if (skillCooldown==0){
            System.out.println("[2] Elemental Skill (" + element + ", 2U, 20 dmg, +10 burst)");
        }
        System.out.println("[3] Elemental Burst (" + element + ", 2U, 30 dmg, need " + maxEnergy + " burst)");
        String input = scn.nextLine();
        if (input.equals("1")){
            this.normalAtk(enemy);
            bl.add(this.getChrName() + " uses Normal Attack: (physical, 10 dmg)");
            System.out.println("\n" + this.getChrName() + " uses Normal Attack: (physical, 10 dmg)");
        }else if (input.equals("2")){
            if (skillCooldown==0){
                //skill attack
                bl.add(this.getChrName() + " uses Elemental Skill: (" + element + ", 2U, 20 dmg)");
                bl.add(this.getChrName() + " applied 2U of " + element);
                System.out.println("\n" + this.getChrName() + " uses Elemental Skill: (" + element + ", 2U, 20 dmg)");
                System.out.println(getChrName() + " applied 2U of " + element);
                System.out.println(getChrName() + " skill on cooldown");
                this.skillAtk(enemy);

            }else {
                System.out.println(this.getChrName() + " skill on cooldown\n");
                this.attack(enemy);
            }
        }else if (input.equals("3")){
            if (energy==maxEnergy){
                energy = 0;
                //burst attack
                bl.add(this.getChrName() + " uses Elemental Burst: (" + element + ", 2U, 30 dmg)");
                bl.add(this.getChrName() + " applied 2U of " + element);
                System.out.println("\n" + this.getChrName() + " uses Elemental Burst: (" + element + ", 2U, 30 dmg)");
                System.out.println(getChrName() + " applied 2U of " + element);
                this.burstAtk(enemy);

            }else {
                System.out.println("burst still not charged\n");
                this.attack(enemy);
            }
        }else {
            System.out.println("invalid input please try again\n");
            this.attack(enemy);
        }
        if (enemy.isCoreExposed()){
            bl.add("attack did double damage");
            System.out.println("Core exposed! attack did double damage!");
        }
        System.out.println("");
    }


}
