package formulas;

import entities.enemy.RuinGuard;
import ui.BattleLog;

public class Reaction {

    private RuinGuard enemy;
    private String trigger;
    private int baseDMG;
    private BattleLog bl = new BattleLog();

    //constructor with the trigger element to the reaction
    public Reaction(RuinGuard enemy, String trigger, int dmg) {
        this.enemy = enemy;
        this.trigger = trigger;
        this.baseDMG = dmg;
    }

    //using the trigger element and aura on element it decides which reaction to use
    public void doReaction() {
        if ((trigger.equals("Hydro") || enemy.getAura().equals("Hydro"))
                && (trigger.equals("Pyro") || enemy.getAura().equals("Pyro"))) {
            this.vaporize();
        } else if ((trigger.equals("Cryo") || enemy.getAura().equals("Cryo"))
                && (trigger.equals("Pyro") || enemy.getAura().equals("Pyro"))) {
            this.melt();
        } else if ((trigger.equals("Hydro") || enemy.getAura().equals("Hydro"))
                && (trigger.equals("Cryo") || enemy.getAura().equals("Cryo"))) {
            this.frozen();
        } else if ((trigger.equals("Electro") || enemy.getAura().equals("Electro"))
                && (trigger.equals("Pyro") || enemy.getAura().equals("Pyro"))) {
            this.overload();
        } else if ((trigger.equals("Hydro") || enemy.getAura().equals("Hydro"))
                && (trigger.equals("Electro") || enemy.getAura().equals("Electro"))) {
            this.electroCharged();
        } else if ((trigger.equals("Cryo") || enemy.getAura().equals("Cryo")) && (trigger.equals("Electro") || enemy.getAura().equals("Electro"))) {
            this.superConduct();
        }
    }

    //vaporize reaction with depending on the trigger can do 2x or 1.5x
    public void vaporize() {
        if (trigger.equals("Hydro")) {
            Damage DMG = new Damage();
            int dmg;
            dmg = DMG.getDMG(baseDMG);
            DMG.setAmplifyingReaction(2); //times 2 multiplier
            enemy.loseAuraGU(4.0); // 2*2 = 4
            System.out.println(">>Vaporize! Hydro triggers on Pyro Aura!<<\nDamage: "
                    + dmg + "x2.0 =" + dmg * 2 + ". Aura Consumed");
            bl.add("Vaporize! Damage: " + dmg + "x2.0 =" + dmg * 2 + ". Aura Consumed");
            dmg = DMG.getDMG(baseDMG); //calulates dmg with mult
            enemy.damage(dmg);
        } else if (trigger.equals("Pyro")) {
            Damage DMG = new Damage();
            int dmg;
            dmg = DMG.getDMG(baseDMG);
            DMG.setAmplifyingReaction(1.5); //times 1.5 multiplier
            enemy.loseAuraGU(1.0);
            System.out.println(">>Vaporize! Pyro triggers on Hydro Aura!<<\nDamage: "
                    + dmg + "x1.5 =" + dmg * 1.5 + ". 1U of Aura Consumed");
            bl.add("Vaporize! Damage: " + dmg + "x1.5 =" + dmg * 1.5 + ". 1U of Aura Consumed");
            dmg = DMG.getDMG(baseDMG); //calulates dmg with mult
            enemy.damage(dmg);
        }
    }

    //melt reaction with depending on the trigger can do 2x or 1.5x
    public void melt() {
        if (trigger.equals("Pyro")) {
            Damage DMG = new Damage();
            int dmg;
            dmg = DMG.getDMG(baseDMG);
            DMG.setAmplifyingReaction(2); //times 2 multiplier
            enemy.loseAuraGU(4.0); // 2*2 = 4
            System.out.println(">>Melt! Pyro triggers on Cryo Aura!<<\nDamage: "
                    + dmg + "x2.0 =" + dmg * 2 + ". Aura Consumed");
            bl.add("Melt! Damage: " + dmg + "x2.0 =" + dmg * 2 + ". Aura Consumed");
            dmg = DMG.getDMG(baseDMG); //calulates dmg with mult
            enemy.damage(dmg);
        } else if (trigger.equals("Cryo")) {
            Damage DMG = new Damage();
            int dmg;
            dmg = DMG.getDMG(baseDMG);
            DMG.setAmplifyingReaction(1.5); //times 1.5 multiplier
            enemy.loseAuraGU(1.0);
            System.out.println(">>Melt! Cryo triggers on Pyro Aura!<<\nDamage: "
                    + dmg + "x1.5 =" + dmg * 1.5 + ". 1U of Aura Consumed");
            bl.add("Melt! Damage: " + dmg + "x1.5 =" + dmg * 1.5 + ". 1U Aura Consumed");
            dmg = DMG.getDMG(baseDMG); //calulates dmg with mult
            enemy.damage(dmg);
        }
    }

    //frozen reaction freezes Ruin Guard for one turn
    public void frozen() {
        enemy.freeze();
        enemy.loseAuraGU(1.0);
        System.out.println(">>Frozen! " + trigger + " triggers on " + enemy.getAura()
                + " Aura!<<\nNo extra damage Ruin Guard frozen Aura Consumed");
        bl.add("Frozen! no extra damage Ruin Guard frozen 1U of Aura Consumed");
    }

    //overload reaction does +25 damage
    public void overload() {
        Damage DMG = new Damage();
        int dmg;
        dmg = DMG.getDMG(baseDMG);
        enemy.loseAuraGU(4.1); //aura fully destroyed
        System.out.println(">>Over Loaded! " + trigger + " triggers on " + enemy.getAura()
                + " Aura!<<\nDamage: "
                + dmg + "x1.0+25 =" + (dmg + 25) + ". Aura Consumed");
        bl.add("Overloaded! Damage: " + dmg + "x1.0+25 =" + dmg + 25 + ". Aura Consumed");
        enemy.damage(dmg+25); //extra 25 damage
    }

    //electro charged reaction stops salvo
    public void electroCharged() {
        Damage DMG = new Damage();
        int dmg;
        dmg = DMG.getDMG(baseDMG); //base dmg for 20 from damage formula
        enemy.damage(dmg); //no mult
        enemy.addAura(4.0);
        enemy.addElectroCharged();
        System.out.println(">>Electro Charged! " + trigger + " triggers on " + enemy.getAura()
                + " Aura!<<\nNo extra damage Ruin Guard is now Electro Charged!");
        bl.add("Electro Charged! No extra damage Ruin Guard is now Electro Charged!");
    }

    //superconduct reaction does 1.9x
    public void superConduct() {
        Damage DMG = new Damage();
        int dmg;
        dmg = DMG.getDMG(baseDMG);
        DMG.setAmplifyingReaction(1.9); //times 1.9 multiplier
        enemy.loseAuraGU(2.0);
        System.out.println(">>Super Conduct! " + trigger + " triggers on " + enemy.getAura()
                + " Aura!<<\nDamage: "
                + dmg + "x1.9 =" + dmg*1.9 + ". Aura Consumed");
        bl.add("Super Conduct! Damage: " + dmg + "x1.9 =" + dmg * 1.5 + ". 2U of Aura Consumed");
        dmg = DMG.getDMG(baseDMG); //calulates dmg with mult
        enemy.damage(dmg);

    }

}
