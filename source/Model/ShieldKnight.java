package Model;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class ShieldKnight extends MeleeMinion {

    public ShieldKnight(CombatManager manager, String name, PlayerImpl master, double[] coords) {
        this.minionName = name;
        this.master = master;
        this.master.add_Minions(this);
        this.hp = 700;
        this.atk = 10;
        this.armor = 10;
        this.maxhp = 500;
        this.armorType = ArmorType.HeavyArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 2;
        this.healthRegen = 3;
        this.attackSpeed = 1;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 50;
        this.priority = 9;

        this.manager = manager;
        manager.instances.add(this);
    }
}
