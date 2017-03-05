package Model;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class ShieldKnight extends MeleeMinion {

    /*
    A tanky minion class
     */
    public ShieldKnight(CombatManager manager, String name, PlayerImpl master, double[] coords) {
        this.minionName = name;
        this.master = master;
        this.master.add_Minions(this);
        this.hp = 600;
        this.atk = 15;
        this.armor = 10;
        this.maxhp = 600;
        this.armorType = ArmorType.HeavyArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 0.15;
        this.healthRegen = 3;
        this.attackSpeed = 1;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 5;
        this.priority = 9;
        this.randomMinConst = 0.99;
        this.randomMaxConst = 1.01;
        this.manager = manager;
        manager.instances.add(this);
    }
}
