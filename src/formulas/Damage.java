package formulas;

public class Damage {
    public int DMG = 0;
    public double BaseDMG = 0; //base DMG value 1=20 (-5.5=10,-2=15,1=20,1.8=30)
    public double BaseDMGMultiplier = 0.2;
    public double AdditiveBaseDMGBonus = 2.5;
    public double DMGBonus = 0.6;
    public double DMGReductionTarget = 0.2;
    public double CRIT = 1.2;
    public double EnemyDefMult = 3.0;
    public double EnemyResMult = 1.5;
    public double AmplifyingReaction = 1;

    //genshin damage formula that calculates the damage
    public void calculateDMG(){
            DMG = (int) ((int) (((BaseDMG * BaseDMGMultiplier)
                    + AdditiveBaseDMGBonus)
                    * (1 + DMGBonus - DMGReductionTarget) * CRIT
                    * EnemyDefMult * EnemyResMult) * AmplifyingReaction);
    }

    //sets the AmplifyingReaction multiplier
    public void setAmplifyingReaction(double mult){
        AmplifyingReaction = mult;
    }

    //runs the damage formula with base damage as a parameter
    //returns dmg number
    public int getDMG(double base){
        BaseDMG = base;
        calculateDMG();
        return DMG;
    }
}
