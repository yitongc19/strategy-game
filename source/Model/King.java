package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 2/25/17.
 */

public class King {

    private double hp;

    private double armor;

    private double maxHP;

    private double atk;

    private AtkType atkType;

    private ArmorType armorType;

    private int teamNum;

    public String kingName;

    private ArrayList<MinionImpl> minions = new ArrayList<MinionImpl>();
    //initialize Model.King!
    public King(int teamNum) {
        this.hp = 5000;
        this.armor = 10;
        this.atk = 100;
        this.atkType = AtkType.Hero;
        this.armorType = ArmorType.HeroArmor;
        this.teamNum = teamNum;

        if (this.teamNum == 1) {
            kingName = "Titans";
        } else {
            kingName = "Old Gods";
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
}
