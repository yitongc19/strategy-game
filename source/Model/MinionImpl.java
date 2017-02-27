package Model;

import static java.lang.Math.pow;

/**
 * Created by xingfanxia on 2/25/17.
 */
public class MinionImpl implements Minion {

    public double hp;
    public double armor;
    public double atk;
    public double maxhp;
    public ArmorType armorType;
    public AtkType atkType;
    public double moveSpeed;
    public double healthRegen;
    public double attackSpeed;
    public int rangeOrMelee;

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
        return this.atk * armorModifer * dmgTypeModifier;
    }
}
