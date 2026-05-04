public class RuinGuard {

    private int health = 1;
    private String aura = "none";
    private double auraGU = 0.0;
    private boolean salvoIncoming = false; //attack turn before it happens
    private boolean amberBlocked = false;  //blocked by amber
    private boolean freezeBlocked = false;  //blocked by freeze
    private boolean electroBlocked = false;  //blocked by electro charge
    private boolean coreExposed = false; //turn during exposed
    private BattleLog bl;

    //constructs the Ruin Guard enemy object and sets it with 200hp
    public RuinGuard(BattleLog bl){
        health = 200;
        this.bl = bl;
    }

    //returns Ruin Guards health as an integer
    public int getHealth(){
        return health;
    }

    //returns the Ruin Guards health that the player sees including health bar
    public String getHP(){
        switch (health){
            case 200:
                return "■■■■■■■■■■■■■■■■■■■■ "+ health + "/200";
            case 195:
                return "■■■■■■■■■■■■■■■■■■■◧ "+ health + "/200";
            case 190:
                return "■■■■■■■■■■■■■■■■■■■□ "+ health + "/200";
            case 185:
                return "■■■■■■■■■■■■■■■■■■◧□ "+ health + "/200";
            case 180:
                return "■■■■■■■■■■■■■■■■■■□□ "+ health + "/200";
            case 175:
                return "■■■■■■■■■■■■■■■■■◧□□ "+ health + "/200";
            case 170:
                return "■■■■■■■■■■■■■■■■■□□□ "+ health + "/200";
            case 165:
                return "■■■■■■■■■■■■■■■■◧□□□ "+ health + "/200";
            case 160:
                return "■■■■■■■■■■■■■■■■□□□□ "+ health + "/200";
            case 155:
                return "■■■■■■■■■■■■■■■◧□□□□ "+ health + "/200";
            case 150:
                return "■■■■■■■■■■■■■■■□□□□□ "+ health + "/200";
            case 145:
                return "■■■■■■■■■■■■■■◧□□□□□ "+ health + "/200";
            case 140:
                return "■■■■■■■■■■■■■■□□□□□□ "+ health + "/200";
            case 135:
                return "■■■■■■■■■■■■■◧□□□□□□ "+ health + "/200";
            case 130:
                return "■■■■■■■■■■■■■□□□□□□□ "+ health + "/200";
            case 125:
                return "■■■■■■■■■■■■◧□□□□□□□ "+ health + "/200";
            case 120:
                return "■■■■■■■■■■■■□□□□□□□□ "+ health + "/200";
            case 115:
                return "■■■■■■■■■■■◧□□□□□□□□ "+ health + "/200";
            case 110:
                return "■■■■■■■■■■■□□□□□□□□□ "+ health + "/200";
            case 105:
                return "■■■■■■■■■■◧□□□□□□□□□ "+ health + "/200";
            case 100:
                return "■■■■■■■■■□□□□□□□□□□ "+ health + "/200";
            case 95:
                return "■■■■■■■■◧□□□□□□□□□□ "+ health + "/200";
            case 90:
                return "■■■■■■■■□□□□□□□□□□□ "+ health + "/200";
            case 85:
                return "■■■■■■■■◧□□□□□□□□□□□ "+ health + "/200";
            case 80:
                return "■■■■■■■■□□□□□□□□□□□□ "+ health + "/200";
            case 75:
                return "■■■■■■■◧□□□□□□□□□□□□ "+ health + "/200";
            case 70:
                return "■■■■■■■□□□□□□□□□□□□□ "+ health + "/200";
            case 65:
                return "■■■■■■◧□□□□□□□□□□□□□ "+ health + "/200";
            case 60:
                return "■■■■■■□□□□□□□□□□□□□□ "+ health + "/200";
            case 55:
                return "■■■■■◧□□□□□□□□□□□□□□ "+ health + "/200";
            case 50:
                return "■■■■■□□□□□□□□□□□□□□□ "+ health + "/200";
            case 45:
                return "■■■■◧□□□□□□□□□□□□□□□ "+ health + "/200";
            case 40:
                return "■■■■□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 35:
                return "■■■◧□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 30:
                return "■■■□□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 25:
                return "■■◧□□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 20:
                return "■■□□□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 15:
                return "■◧□□□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 10:
                return "■□□□□□□□□□□□□□□□□□□□ "+ health + "/200";
            case 5:
                return "◧□□□□□□□□□□□□□□□□□□□ "+ health + "/200";
        }
        return "";
    }

    //Ruin Guards attack phase
    public void attack(Player pl) {
        //checks if external factor is blocking it
        if (!amberBlocked && !freezeBlocked) {
            if (salvoIncoming) {
                if (!electroBlocked) {
                    bl.add("Ruin Guard fired Salvo");
                    bl.add("Missles hit for 15 damage");
                    bl.add("core exposed for double damage");
                    System.out.println("The Ruin Guard fires Salvo!\n");
                    System.out.println("Missles hit! 15 damage");
                    System.out.println("Core Exposed!");
                    System.out.println("your next turn now does double damage!");
                    pl.damage(15);
                    coreExposed = true;
                    salvoIncoming = false;
                }else{
                    electroBlocked = false;
                    bl.add("Ruin Guard fired Salvo");
                    bl.add("Missles blocked 0 damage");
                    bl.add("core exposed for double damage");
                    System.out.println("The Ruin Guard fires Salvo!\n");
                    System.out.println("elctro charged is active and stunned it!");
                    System.out.println("Missles destroyed! 0 damage");
                    System.out.println("Core Exposed!");
                    System.out.println("your next turn now does double damage!");
                    salvoIncoming = false;
                    coreExposed = true;
                }
            } else {
                int num = (int) (Math.random() * 101);
                //87% chance of Ruin Guard using stomp attack and 13% of spin
                if (num <= 87) {
                    bl.add("Ruin Guard stomps for 10 damage");
                    System.out.println("The Ruin Guard Stomps! 10 damage");
                    pl.damage(10);
                } else {
                    bl.add("Ruin Guard spins for 20 damage");
                    System.out.println("The Ruin Guard does spin attack! 20 damage");
                    pl.damage(20);
                }
                //extra 33% chance of salvo happening
                int num2 = (int) (Math.random() * 101);
                //preperation phase
                if (num2 <= 33) {
                    salvoIncoming = true;
                    bl.add("Ruin Guard prepares for salvo");
                    System.out.println("The Ruin Guard puts its arms into the ground");
                    System.out.println("Salvo incoming!!!");
                    //incoming
                }
            }
        }else{
            //different block and stun checks
            if(salvoIncoming && amberBlocked) {
                amberBlocked = false;
                bl.add("Ruin Guard fired Salvo");
                bl.add("Missles blocked 0 damage");
                bl.add("core exposed for double damage");
                System.out.println("The Ruin Guard fires Salvo!\n");
                System.out.println("Amber hits Ruin Guards eye and stunned it! (50% chance, success)");
                System.out.println("Missles destroyed! 0 damage");
                System.out.println("Core Exposed!");
                System.out.println("your next turn now does double damage!");
                salvoIncoming = false;
                coreExposed = true;
            }else if(salvoIncoming && freezeBlocked) {
                freezeBlocked = false;
                auraGU = 0;
                bl.add("Ruin Guard can't fire Salvo frozen");
                bl.add("frozen 0 damage");
                bl.add("core exposed for double damage");
                System.out.println("Ruin Guard can't fire Salvo!\n");
                System.out.println("Ruin Guard is stuck frozen!");
                System.out.println("Frozen! 0 damage");
                System.out.println("Frozen wears off");
                System.out.println("Core Exposed!");
                System.out.println("your next turn now does double damage!");
                salvoIncoming = false;
                coreExposed = true;
            }else if(!salvoIncoming && freezeBlocked) {
                freezeBlocked=false;
                auraGU = 0;
                bl.add("Ruin Guard frozen can't move!");
                System.out.println("Ruin Guard frozen can't move!");
                System.out.println("Frozen wears off");
            }else{
                amberBlocked=false;
                bl.add("Amber hit the eye and stunned it");
                System.out.println("Amber hits Ruin Guards eye and stunned it! (33% chance, success)");
            }
        }
    }

    //damages Ruin Guard and subtracts health also detecting if core is exposed for double damage
    public void damage(double dmg){
        if (coreExposed) {
            if((health-dmg*2.0)>=0.0) {
                health -= dmg * 2.0;
            }else {
                health = 0;
            }
        }else{
            if (health-dmg>=0) {
                health -= dmg;
            }else {
                health = 0;
            }
        }
    }

    //when amber attacks checks for stun
    public void amberAttacks(){
        int num3 = (int)(Math.random()*101);
        //when salvo 50% chance of stunning
        if(salvoIncoming){
            if(num3<=50){
                amberBlocked = true;
            }
            //no salvo 33% chance of stunning
        }else{
            if(num3<=33){
                amberBlocked = true;
            }
        }
    }

    //Freezes the Ruin Guard when using Frozen reaction
    public void freeze(){
        freezeBlocked = true;
    }

    //Electro charges the Ruin Guard when using the electro charge reaction
    public void addElectroCharged(){
        electroBlocked = true;
    }

    //returns current aura placed on Ruin Guard
    public String getAura(){
        return aura;
    }

    //Sets current Aura on Ruin Guard
    public void setAura(String aura){
        this.aura = aura;
    }

    //Subtracts certain amount of auraGU
    public void loseAuraGU(double auraGU){
        if(this.auraGU-auraGU>0){
            this.auraGU = auraGU;
        }else{
            this.auraGU = 0;
        }

    }

    //returns current AuraGU of the Ruin Guard
    public double getAuraGU(){
        return auraGU;
    }

    //Adds AuraGU to the Ruin Guard
    public void addAura(double auraGU){
        if ((this.auraGU + auraGU)<=4) {
            this.auraGU += auraGU;
        }else{
            this.auraGU = 4.0;
        }
    }

    //Used to enter the end phase of the turn decaying auraGU and moving on to the next turn
    public void turnFinshed(Player pl){

        //resets core after salvo
        coreExposed = false;

        //decays auraGU
        if(auraGU!=0.0){
            if(auraGU-0.5>=0.5){
                System.out.print("\nRuin Guard aura decay 0.5 now " + auraGU + "\n");
                bl.add("Ruin Guard aura decay 0.5 now " + auraGU);
                auraGU -= 0.5;
            }else{
                System.out.print("\nRuin Guard aura decay 0.5 No Remaining\n");
                bl.add("Ruin Guard aura decay 0.5 now " + auraGU);
                auraGU = 0.0;
            }
        }
        if(auraGU==0.0){
            aura = "none";
            electroBlocked = false;
        }
        //restarts turn if battle is still happening
        if((pl.getHealth() != 0) && (health != 0)) {
            this.attack(pl);
            bl.incTurn();
        }
    }

    //returns if core exposed
    public boolean isCoreExposed(){
        return coreExposed;
    }

    //returns the printable hp of the Ruin Guard
    public String toString(){
        return "Ruin Guard HP: " + this.getHP() + "\nAura: " + aura;
    }

}
