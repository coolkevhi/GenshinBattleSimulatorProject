package ui;

import java.util.ArrayList;

public class BattleLog {

    private static ArrayList<String> batteLog = new ArrayList<>();
    private static int turn = 1;
    private static int i = 1;

    public BattleLog(){}

    //increments the current turn number
    public void incTurn(){
        turn++;
    }

    //adds a certain item to the battle log
    public void add(String action){
        if(i<10) {
            batteLog.add("0" + i + ". " + "turn " + turn + " " + action + "\n");
        }else{
            batteLog.add(i + ". " + "turn " + turn + " " + action + "\n");
        }
        i++;
    }

    //prints the battle log array list
    public void print(){
        for(String action : batteLog){
            System.out.print(action);
        }
    }

    //clears and resets the battle log
    public void clear(){
        turn = 1;
        batteLog.clear();
        batteLog.add("\n--------Battle Log --------\n");
    }

    //return current turn number
    public int getTurn(){
        return turn;
    }
}
