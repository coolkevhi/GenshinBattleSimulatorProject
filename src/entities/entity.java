package entities;

import ui.BattleLog;

public abstract class entity {
    private BattleLog bl = new BattleLog();
    private int health;
    private int maxHealth;

    //uses add method from battelog class to add a action to the battle log
    public void blAdd(String action){
        bl.add(action);
    }

    //uses inc method from battelog class to increment a turn
    public void blInc(){
        bl.incTurn();
    }

    //uses inc method from battelog class to increment a turn
    public int blTurn(){
        return bl.getTurn();
    }

    //returns health as an integer
    public abstract int getHealth();

    //used to damage the entity and subtract health
    public abstract void damage(int dmg);

    //returns the health that the player sees including health bar
    public String getHP(int health, int maxHealth){
        String hp = "";
        int temp = health/10;
        int tempMax = maxHealth/10;
        for (int i = temp; i>0; i--){
            hp = hp + "■";
        }
        if (health % 10 != 0){
            hp = hp + "◧";
        }
        for (int i = tempMax-hp.length(); i>0; i--){
            hp = hp + "□";
        }
        return hp + " " + health + "/" + maxHealth;
    }

}
