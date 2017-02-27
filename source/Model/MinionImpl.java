package Model;

/**
 * Created by xingfanxia on 2/25/17.
 */
public class MinionImpl implements Minion {

    private double hp;
    private double armor;
    private double atk;
    private double maxhp;
    private ArmorType armorType;
    private AtkType atkType;
    private double moveSpeed;
    private double healthRegen;
    private double attackSpeed;
    private int rangeOrMelee;

    @Override
    public double getHP() {
        return this.hp;
    }

    @Override
    public double getMaxHp() {
        return this.maxhp;
    }

    @Override
    public double getATK() {
        return this.atk;
    }

    @Override
    public AtkType getAttackType() {
        return this.atkType;
    }

    @Override
    public double getArmor() {
        return this.armor;
    }

    @Override
    public ArmorType getArmorType() {
        return this.armorType;
    }

    @Override
    public double getAS() {
        return this.attackSpeed;
    }

    @Override
    public int range_melee() {
        return this.rangeOrMelee;
    }

    @Override
    public double move_speed() {
        return this.moveSpeed;
    }

    @Override
    public double health_regen() {
        return this.healthRegen;
    }

    @Override
    public void setStaticAnimation() {

    }

    @Override
    public void setAtkAnimation() {

    }

    @Override
    public void setProjectile() {

    }

    @Override
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

    @Override
    public double dmgCal(MinionImpl enemy) {
        double armorModifer;
        double dmgTypeModifier = calDmgPercent(enemy);
        assert(dmgTypeModifier!=0);
        double enemyArmor = enemy.getArmor();
        if (enemyArmor < 0) {
            armorModifer = 2-0.94*(-enemyArmor);
        } else {
            armorModifer = (enemyArmor*0.06)/(1+enemyArmor*0.06);
        }
        return this.atk * armorModifer * dmgTypeModifier;
    }
}
