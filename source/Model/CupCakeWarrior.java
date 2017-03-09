package Model;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class CupCakeWarrior extends MeleeMinion {

    public CupCakeWarrior(CombatManager manager, String name, PlayerImpl master, double[] coords) {
        this.minionName = name;
        this.master = master;
        this.master.add_Minions(this);
        this.hp = 500;
        this.atk = 40;
        this.armor = -2;
        this.maxhp = 500;
        this.armorType = ArmorType.LightArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 2;
        this.healthRegen = 3;
        this.attackSpeed = 1;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 5;
        this.priority = 5;
        this.randomMinConst = 0.90;
        this.randomMaxConst = 1.10;
        this.manager = manager;
        manager.instances.add(this);
        this.portalReward = 12;
        this.killReward = 6;
        //set the Frames
    }
}
