package Model;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by xingfanxia on 2/25/17.
 */

/*
THe King class controls all the attributes of the king
 */
public class King {

    public double hp;

    private double armor;

    private double maxHP;

    private double atk;

    private AtkType atkType;

    private ArmorType armorType;

    private int teamNum;

    private int atkRange = 30;

    public String kingName;

    public double[] kingArmyPos;

    public double[] kingPos;

    private Random rand;

    private ArrayList<MinionImpl> minions = new ArrayList<MinionImpl>();

    public double atkCounter = 0;

    public double atkSpeed = 1;

    public King opponetKing;

    public String kingImageStill;

    public String kingImageAttack;
    //initialize Model.King!
    public King(int teamNum) {
        this.hp = 5000;
        this.maxHP = 5000;
        this.armor = 10;
        this.atk = 100;
        this.atkType = AtkType.Hero;
        this.armorType = ArmorType.HeroArmor;
        this.teamNum = teamNum;

        if (this.teamNum == 1) {
            kingName = "Radiant King";
            this.kingPos = new double[]{200, 590}; //to be set
            this.kingArmyPos = new double[]{200, 590};
        } else {
            kingName = "Dire King";
            this.kingPos = new double[]{1120, 590};
            this.kingArmyPos = new double[]{1120, 590};
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

    public King getOpponetKing() {
        return opponetKing;
    }

    public double getArmor() {
        return armor;
    }

    public double getHpPercent() { return this.hp/this.maxHP; }

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

    public void setOpponetKing(King opponetKing) {
        this.opponetKing = opponetKing;
        this.opponetKing.opponetKing = this;
    }

    public void setAnimation() {

    }

    public ArrayList<MinionImpl> getMinions() {
        return minions;
    }

    public void add_Minions(MinionImpl minion) {
        this.minions.add(minion);
        minion.myKing = this;
    }

    public boolean checkDeath() {
        /*
        check if the king died
         */
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

    public double kingDmgPercent(MinionImpl minion) {
        /*
        Armor Calculations
         */
        switch(minion.getArmorType()) {
            case LightArmor:
                return 1.0;
            case MediumArmor:
                return 1.0;
            case HeavyArmor:
                return 1.0;
            case FortArmor:
                return 1.0;
            case HeroArmor:
                return 1.0;
            case NoArmor:
                return 1.0;
        }
        return 0;
    }


    public double cal_distance(MinionImpl enemy) {
        /*
        Computes the distance between king and enemy
         */
        return sqrt(pow(this.kingPos[0] - enemy.Coords[0], 2)+pow(this.kingPos[1] - enemy.Coords[1], 2));
    }



    public MinionImpl randomTarget(ArrayList<MinionImpl> mylist) {
        /*
        random target as in MinionImpl
         */
        this.rand = new Random();
        MinionImpl randomMinion = mylist.get(this.rand.nextInt(mylist.size()));
        return randomMinion;
    }

    public MinionImpl chooseTarget(ArrayList<MinionImpl> enemies) {
        /*
        Target choosing, essentially the same as in MinionImpl
         */
        if (enemies.isEmpty()) {
            return null;
        }
        MinionImpl target = new MinionImpl();
        ArrayList<MinionImpl> temp = new ArrayList<MinionImpl>();
        double minDist = 9999999;
        for (MinionImpl warrior: enemies){
            double dist = cal_distance(warrior);
            if (dist < minDist) {
                minDist = dist;
                target = warrior;
            } else if (dist == minDist) {
                temp.add(warrior);
            }
        }
        if (!temp.isEmpty()) {
            return randomTarget(temp);
        } else {
            return target;
        }
    }

    public double dmgCal(MinionImpl enemy) {
        /*
        calculate Damage dealt to target similar to
        what's in MinionImpl
         */
        double armorModifer;
        double dmgTypeModifier = kingDmgPercent(enemy);
        assert(dmgTypeModifier!=0);
        double enemyArmor = enemy.getArmor();
        if (enemyArmor < 0) {
            armorModifer = 2-pow(0.84,(-enemyArmor));
        } else {
            armorModifer = 1- (enemyArmor*0.06)/(1+enemyArmor*0.06);
        }
        double dmgDealt = this.atk * armorModifer * dmgTypeModifier;
        enemy.hp -= dmgDealt;
        return dmgDealt;
    }

    public void kingFight() {
        //if no minion do nothing, otherwise attack it if in range
        if (!this.getOpponetKing().getMinions().isEmpty()) {
            ArrayList<MinionImpl> minions = this.getOpponetKing().getMinions();
            MinionImpl target = chooseTarget(minions);
            if (cal_distance(target) < this.atkRange && this.atkCounter >= this.atkSpeed) {
                double damageDone = dmgCal(target);
//                System.out.println(this.kingName + " dealt " + damageDone + " damage to " + target.minionName);
                this.atkCounter = 0;
            }
        }
    }
}
