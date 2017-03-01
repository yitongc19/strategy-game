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

    public CupCakeWarrior(CombatManager manager, String name, PlayerImpl master, double[] coords) {
        this.minionName = name;
        this.master = master;
        this.master.add_Minions(this);
        this.hp = 500;
        this.atk = 30;
        this.armor = -2;
        this.maxhp = 500;
        this.armorType = ArmorType.LightArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 2;
        this.healthRegen = 3;
        this.attackSpeed = 1;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 10;
        this.priority = 5;

        this.manager = manager;
        manager.instances.add(this);

        //set the Frames
    }
}
