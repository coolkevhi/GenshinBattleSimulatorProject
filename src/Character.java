import java.util.Scanner;

public class Character {

    String[] characters = {"Amber(Pyro)", "Xingqiu(Hydro)", "Kaeya(Cryo)", "Lisa(Electro)"};

    private String name = "";
    private String element = "";
    private int energy = 0;
    private int maxEnergy = 0;
    private boolean skillCooldown = false;
    private BattleLog bl = new BattleLog();

    public Character(String name){
        for(String chr : characters){
            int para = chr.indexOf("(") + 1;
            if(chr.contains(name)){
                 this.name = name;
                 element = chr.substring(para, chr.length()-1);
                 if(this.name == "Xingqiu(Hydro)"){
                     maxEnergy = 80;
                 }else{
                     maxEnergy = 60;
                 }
            }
        }
    }

    public void normalAtk(RuinGuard enemy){
        enemy.damage(10);
        if(this.name=="Amber(Pyro)"){
            enemy.amberAttacks();
        }
        if(energy<maxEnergy){
            if(energy+5>maxEnergy){
                energy = maxEnergy;
            }else{
                energy += 5;
            }
        }
    }

    public void skillAtk(RuinGuard enemy) {
        enemy.damage(20);
        if (enemy.getAura().equals("none")) {
            enemy.setAura(element);
            enemy.addAura(2.0);


        }
        if (energy < maxEnergy) {
            if (energy + 10 > maxEnergy) {
                energy = maxEnergy;
            } else {
                energy += 10;
            }
        }
    }

    public String getChrName(){
        int para = name.indexOf("(");
        return name.substring(0, para);
    }

    public String getFullChrName(){
        return name;
    }

    public String skillCheck(){
        if(!skillCooldown){
            return "ready";
        }else{
            return "on cooldown";
        }
    }

    public String burstCheck() {
        return energy + "/" + maxEnergy;
    }

    public void attack(RuinGuard enemy){
        Scanner scn = new Scanner(System.in);
        if(skillCooldown){
            System.out.println(this.getChrName() + " skill on cooldown\n");
        }
        System.out.println("[1] Normal Attack (physical, 10 dmg, +5 burst)");
        if(!skillCooldown){
            System.out.println("[2] Elemental Skill (" + element + ", 2U, 20 dmg, +10 burst) (WIP)");
        }
        System.out.println("[3] Elemental Burst (" + element + ", 2U, 30 dmg, need " + maxEnergy + " burst) (WIP)");
        String input = scn.nextLine();
        if(input.equals("1")){
            this.normalAtk(enemy);
            bl.add(this.getChrName() + " uses Normal Attack: (physical, 10 dmg)");
            System.out.println("\n" + this.getChrName() + " uses Normal Attack: (physical, 10 dmg)");
        }else if(input.equals("2")){
            if(!skillCooldown){
                //skill attack
                bl.add(this.getChrName() + " uses Elemental Skill: (" + element + ", 2U, 20 dmg)");
                bl.add(this.getChrName() + " applied 2U of " + element);
                System.out.println("\n" + this.getChrName() + " uses Elemental Skill: (" + element + ", 2U, 20 dmg) (WIP)");
                System.out.println(getChrName() + " applied 2U of " + element);
            }else{
                System.out.println(this.getChrName() + " skill on cooldown\n");
                this.attack(enemy);
            }
        }else if(input.equals("3")){
            if(energy==maxEnergy){
                energy = 0;
                //burst attack
                bl.add(this.getChrName() + " uses Elemental Burst: (" + element + ", 2U, 30 dmg)");
                bl.add(this.getChrName() + " applied 2U of " + element);
                System.out.println("\n" + this.getChrName() + " uses Elemental Burst: (" + element + ", 2U, 30 dmg) (WIP)");
                System.out.println(getChrName() + " applied 2U of " + element);

            }else{
                System.out.println("burst still not charged\n");
                this.attack(enemy);
            }
        }else{
            System.out.println("invalid input please try again\n");
            this.attack(enemy);
        }
        if(enemy.isCoreExposed()){
            bl.add("attack did double damage");
            System.out.println("Core exposed! attack did double damage!");
        }
        System.out.println("");
    }


}
