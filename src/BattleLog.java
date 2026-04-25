import java.util.ArrayList;

public class BattleLog {

    private static ArrayList<String> batteLog = new ArrayList<>();
    private static int turn = 1;

    public BattleLog(){

    }

    public void incTurn(){
        turn++;
    }

    public void add(String action){
        int i = 1;
        batteLog.add(i +". " + "turn " + turn + " " + action + "\n");
        i++;
    }

    public void print(){
        for(String action : batteLog){
            System.out.print(action);
        }
    }

    public void clear(){
        turn = 1;
        batteLog.clear();
        batteLog.add("\n--------Battle Log --------\n(battle log WIP)");
    }

    public int getTurn(){
        return turn;
    }
}
