package Model;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by xingfanxia on 2/25/17.
 */
public class MinionImpl implements Minion {

    public PlayerImpl master;
    public String minionName;
    public double hp;
    public double armor;
    public double atk;
    public double atkRange;
    public double maxhp;
    public ArmorType armorType;
    public AtkType atkType;
    public double moveSpeed;
    public double healthRegen;
    public double attackSpeed;
    public int rangeOrMelee;
    public double[] Coords; //Coord on map
    public double attackCounter = 0;

    public double getHP() {
        return this.hp;
    }

    public double getMaxHp() {
        return this.maxhp;
    }

    public double getATK() {
        return this.atk;
    }

    public AtkType getAttackType() {
        return this.atkType;
    }

    public double getArmor() {
        return this.armor;
    }

    public ArmorType getArmorType() {
        return this.armorType;
    }

    public double getAS() {
        return this.attackSpeed;
    }

    public int range_melee() {
        return this.rangeOrMelee;
    }

    public double move_speed() {
        return this.moveSpeed;
    }

    public double health_regen() {
        return this.healthRegen;
    }

    public double getAtkRange() {
        return atkRange;
    }

    public void setAtkRange(double atkRange) {
        this.atkRange = atkRange;
    }

    public double[] getCoords() {
        return Coords;
    }

    public void setCoords(double[] coords) {
        Coords = coords;
    }

    public void setStaticAnimation() {

    }

    public void setAtkAnimation() {

    }

    public void setProjectile() {

    }

    public void setSpecial() {

    }

    private double calDmgPercent(MinionImpl enemy) {
        AtkType myAtkType = this.getAttackType();
//        Model.ArmorType myArmorType = this.armorType;
//        Model.AtkType enemyAtkType = enemy.atkType;
        ArmorType enemyArmorType = enemy.getArmorType();

        switch(myAtkType) {
            case Normal:
                switch(enemyArmorType) {
                    case LightArmor:
                        return 0.8;
                    case MediumArmor:
                        return 1.3;
                    case HeavyArmor:
                        return 0.9;
                    case FortArmor:
                        return 0.9;
                    case HeroArmor:
                        return 0.85;
                    case NoArmor:
                        return 1.0;
                }
                break;
            case Pierce:
                switch(enemyArmorType) {
                    case LightArmor:
                        return 1.3;
                    case MediumArmor:
                        return 1.0;
                    case HeavyArmor:
                        return 0.8;
                    case FortArmor:
                        return 0.8;
                    case HeroArmor:
                        return 0.85;
                    case NoArmor:
                        return 1.0;
                }
                break;
            case Seige:
                switch(enemyArmorType) {
                    case LightArmor:
                        return 0.8;
                    case MediumArmor:
                        return 0.9;
                    case HeavyArmor:
                        return 0.9;
                    case FortArmor:
                        return 1.3;
                    case HeroArmor:
                        return 0.85;
                    case NoArmor:
                        return 1.0;
                }
                break;
            case Magic:
                switch(enemyArmorType) {
                    case LightArmor:
                        return 1.1;
                    case MediumArmor:
                        return 0.8;
                    case HeavyArmor:
                        return 1.3;
                    case FortArmor:
                        return 0.7;
                    case HeroArmor:
                        return 0.85;
                    case NoArmor:
                        return 1.0;
                }
                break;
            case Chaos:
                switch(enemyArmorType) {
                    case LightArmor:
                        return 1.1;
                    case MediumArmor:
                        return 1.1;
                    case HeavyArmor:
                        return 1.1;
                    case FortArmor:
                        return 1.1;
                    case HeroArmor:
                        return 1.1;
                    case NoArmor:
                        return 1.1;
                }
                break;
            case Hero:
                switch(enemyArmorType) {
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
                break;
        }
        return 0;
    }

    public double dmgCal(MinionImpl enemy) {
        double armorModifer;
        double dmgTypeModifier = calDmgPercent(enemy);
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

    public double cal_distance(MinionImpl enemy) {
        return sqrt(pow(this.Coords[0] - enemy.Coords[0], 2)+pow(this.Coords[1] - enemy.Coords[1], 2));
    }

    public MinionImpl chooseTarget(ArrayList<MinionImpl> enemies) {
        MinionImpl target = new MinionImpl();
        double minDist = 9999999;
        for (MinionImpl warrior: enemies){
            double dist = cal_distance(warrior);
            if (dist < minDist && dist < atkRange) {
                minDist = dist;
                target = warrior;
            }
        }
        return target;
    }

//    public MinionImpl rangeCheck(MinionImpl target) {
//        if (cal_distance(target) < this.atkRange) {
//            performAttack();
//        }
//    }

    public void performAttack(ArrayList<MinionImpl> enemies) {
        MinionImpl target = new MinionImpl();
        target = chooseTarget(enemies);

        if (this.attackCounter == this.attackSpeed){
            double damage = this.dmgCal(target);
            System.out.println(this.master.getPlayerName() + "'s " +
                    this.minionName + " dealt " + damage + " damage to " +
                    target.master.getPlayerName() + "'s " + target.minionName);
        }
    }

    public void dieForHonor() {
        if (this.hp <= 0) {
            //destroy the minion?
        }
    }
}
