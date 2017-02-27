package Model;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class CupCakeWarrior extends MeleeMinion {
//    private double hp;
//    private double armor;
//    private double atk;
//    private double maxhp;
//    private ArmorType armorType;
//    private AtkType atkType;
//    private double moveSpeed;
//    private double healthRegen;
//    private double attackSpeed;
//    private int rangeOrMelee;

    public CupCakeWarrior(String name, double[] coords) {
        this.minionName = name;
        this.hp = 500;
        this.atk = 30;
        this.armor = -9;
        this.maxhp = 500;
        this.armorType = ArmorType.HeavyArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 2;
        this.healthRegen = 3;
        this.attackSpeed = 1;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 100;
    }
}
