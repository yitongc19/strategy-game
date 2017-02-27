package Model;

/**
 * Created by xingfanxia on 2/25/17.
 */

public class King {

    private double hp;

    private double armor;

    private double atk;

    private AtkType atkType;

    private ArmorType armorType;

    private int teamNum;

    //initialize Model.King!
    public King(int teamNum) {
        this.hp = 5000;
        this.armor = 10;
        this.atk = 100;
        this.atkType = AtkType.Hero;
        this.armorType = ArmorType.HeroArmor;
        this.teamNum = teamNum;

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
}
