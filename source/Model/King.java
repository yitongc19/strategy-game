package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 2/25/17.
 */

public class King {

    public double hp;

    private double armor;

    private double maxHP;

    private double atk;

    private AtkType atkType;

    private ArmorType armorType;

    private int teamNum;

    public String kingName;

    public double[] kingArmyPos;

    public double[] kingPos;

    private ArrayList<MinionImpl> minions = new ArrayList<MinionImpl>();

    public King opponetKing;
    //initialize Model.King!
    public King(int teamNum) {
        this.hp = 5000;
        this.armor = 10;
        this.atk = 100;
        this.atkType = AtkType.Hero;
        this.armorType = ArmorType.HeroArmor;
        this.teamNum = teamNum;

        if (this.teamNum == 1) {
            kingName = "Radiant King";
            this.kingPos = new double[]{5, 500}; //to be set
            this.kingArmyPos = new double[]{10, 500};
        } else {
            kingName = "Dire King";
            this.kingPos = new double[]{95, 500};
            this.kingArmyPos = new double[]{90, 500};
        }
    }
    /*
    Accessors
     */
    public double getHp() {
        return this.hp;
    }

    public double getAtk() {
        return this.atk;
    }

    public AtkType getAtkType() {
        return this.atkType;
    }

    public ArmorType getArmorType() {
        return this.armorType;
    }

    public int getTeam() {
        return this.teamNum;
    }


    public double getMaxHP() {
        return maxHP;
    }

    /*
    Setters
     */

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public void setAnimation() {

    }

    public ArrayList<MinionImpl> getMinions() {
        return minions;
    }

    public void add_Minions(MinionImpl minion) {
        this.minions.add(minion);
        minion.myKing = this;
        //reset minions' coord to king's lane here
    }

    public boolean checkDeath() {
        if (this.hp <0) {
            if (this.teamNum == 1) {
                System.out.println("The Radiant has been defeated!");
            } else {
                System.out.println("The Dire has been defeated!");
            }
            return true;
        }
        else
            return false;
    }

    public King getOpponetKing() {
        return opponetKing;
    }

    public void setOpponetKing(King opponetKing) {
        this.opponetKing = opponetKing;
        this.opponetKing.opponetKing = this;
    }

    public double getArmor() {
        return armor;
    }

    public kingFight 
}
