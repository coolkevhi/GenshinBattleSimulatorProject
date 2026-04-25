public class RuinGuard {

    public int health = 1;
    public String aura = "none";
    private double auraGU = 0.0;
    private boolean salvoIncoming = false; //attack turn before it happens
    private boolean blocked = false;  //blocked by reaction or amber
    private boolean coreExposed = false; //turn during exposed
    private BattleLog bl;

    public RuinGuard(BattleLog bl){
        health = 200;
        this.bl = bl;
    }

    public int getHealth(){
        return health;
    }

    public void attack(Player pl) {
        if (!blocked) {
            if (salvoIncoming) {
                System.out.println("The Ruin Guard fires Salvo!\n");
                System.out.println("Missles hit! 15 damage");
                System.out.println("Core Exposed!");
                System.out.println("your next turn now does double damage!");
                pl.damage(15);
                coreExposed = true;
                salvoIncoming = false;
            } else {
                int num = (int) (Math.random() * 101);
                if (num <= 87) {
                    System.out.println("The Ruin Guard Stomps! 10 damage");
                    pl.damage(10);
                } else {
                    System.out.println("The Ruin Guard does spin attack! 20 damage");
                    pl.damage(20);
                }
                int num2 = (int) (Math.random() * 101);
                if (num2 <= 33) {
                    salvoIncoming = true;
                    System.out.println("The Ruin Guard puts its arms into the ground");
                    System.out.println("Salvo incoming!!!");
                    //incoming
                }
            }
        }else{
            blocked=false;
            if(salvoIncoming) {
                System.out.println("The Ruin Guard fires Salvo!\n");
                System.out.println("Amber hits Ruin Guards eye and stunned it! (50% chance, success)");
                System.out.println("Missles destroyed! 0 damage");
                System.out.println("Core Exposed!");
                System.out.println("your next turn now does double damage!");
                salvoIncoming = false;
                coreExposed = true;
            }else{
                System.out.println("Amber hits Ruin Guards eye and stunned it! (33% chance, success)");
            }
        }
    }

    public void damage(int dmg){
        if (coreExposed) {
            if((health-dmg*2)>=0) {
                health -= dmg * 2;
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

    public void amberAttacks(){
        int num3 = (int)(Math.random()*101);
        if(salvoIncoming){
            if(num3<=50){
                blocked = true;
            }
        }else{
            if(num3<=33){
                blocked = true;
            }
        }
    }

    public String getAura(){
        return aura;
    }

    public void setAura(String aura){
        this.aura = aura;
    }

    public void turnFinshed(Player pl){

        coreExposed = false;

        if(auraGU!=0.0){
            if(auraGU-0.5>=0.5){
                System.out.print("\nRuin Guard aura decay 0.5");
                auraGU -= 0.5;
            }else{
                System.out.print("\nRuin Guard aura decay 0.5 No Remaining");
                auraGU = 0.0;
            }
        }
        if(auraGU==0.0){
            aura = "none";
        }
        if((pl.getHealth() != 0) && (health != 0)) {
            this.attack(pl);
            bl.incTurn();
        }
    }

    public boolean isCoreExposed(){
        return coreExposed;
    }

    public String toString(){
        return "Ruin Guard HP: " + health + "/200" + "\nAura: " + aura;
    }

}
