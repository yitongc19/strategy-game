package Model;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;
import javafx.scene.image.Image;
import View.Sprite;

/**
 * Created by xingfanxia on 3/1/17.
 */

/*
A upgraded version of cupcake warrior
 */
public class CupCakeBerserker extends MeleeMinion {

    /*
    Variables for critical strike of berserkers
     */
    public double critChance;
    public double critDmgMultiplier;

    /*
    Initialize
     */
    public CupCakeBerserker(CombatManager manager, String name, PlayerImpl master, double[] coords) {
        this.minionName = name;
        this.master = master;
        this.master.add_Minions(this);
        this.hp = 800;
        this.atk = 75;
        this.armor = -3;
        this.maxhp = 700;
        this.armorType = ArmorType.LightArmor;
        this.atkType = AtkType.Normal;
        this.moveSpeed = 0.2;
        this.healthRegen = 3;
        this.attackSpeed = 0.7;
        this.rangeOrMelee = 0;
        this.Coords = coords;
        this.atkRange = 8;
        this.priority = 7;
        this.randomMinConst = 0.80;
        this.randomMaxConst = 1.30;
        this.killReward = 12;
        this.portalReward = 20;
        this.manager = manager;
        manager.instances.add(this);

        this.sprite = new Sprite();
        Image def = new Image(CupCakeWarrior.class.getResource("cupcakeDefault.png").toExternalForm());
        Image walk = new Image(CupCakeWarrior.class.getResource("cupcakeWalk.png").toExternalForm());
        Image fight = new Image(CupCakeWarrior.class.getResource("cupcakeFight.png").toExternalForm());
        this.sprite.setImage(def);
        this.setDef(def);
        this.setFight(fight);
        this.setWalk(walk);

        //crit strike chance and dmg
        this.critChance = 0.35;
        this.critDmgMultiplier = 2.2;

        //set the Frames
    }

    /*
    random number generator for critical chance
     */
    private double randomChance() {
        return ThreadLocalRandom.current().nextDouble(0.0, 1.0);
    }

    /*
    Used to do a critical strike!
     */
    private double CritStrike() {
        double realtimeAtk = atkDamageRandomModifier(randomMinConst, randomMaxConst);
        double chance = randomChance();
        if (chance <= this.critChance) {
            System.out.println(this.minionName + "did a Critical Strike right in his target's weak spot!");
            return realtimeAtk*this.critDmgMultiplier;
        }
        return realtimeAtk;
    }


    @Override
    public double dmgCal(MinionImpl enemy) {
        /*
        A modified dmgCal with critical strike in here
         */
        double armorModifer;
        double dmgTypeModifier = calDmgPercent(enemy);
        assert(dmgTypeModifier!=0);
        double enemyArmor = enemy.getArmor();
        if (enemyArmor < 0) {
            armorModifer = 2-pow(0.84,(-enemyArmor));
        } else {
            armorModifer = 1- (enemyArmor*0.06)/(1+enemyArmor*0.06);
        }
        double realtimeAtk = CritStrike();
        double dmgDealt = realtimeAtk * armorModifer * dmgTypeModifier;
        enemy.hp -= dmgDealt;
        return dmgDealt;
    }
}
