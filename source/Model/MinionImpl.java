package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by xingfanxia on 2/25/17.
 */
public class MinionImpl implements Minion {

//    static ArrayList<MinionImpl> instances = new ArrayList<MinionImpl>();
    public CombatManager manager;
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
    public boolean alive = true;
    public Random rand;
    public int priority;
    public King myKing = null;

    public double randomMinConst;
    public double randomMaxConst;
    public String stillFrame;
    public String moveFrame;
    public String attackFrame;
    public MinionImpl upgradeTo;

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

    /*
    Calculate dmg percent accrodiing to armortype and atktype
     */
    public double calDmgPercent(MinionImpl enemy) {
        AtkType myAtkType = this.getAttackType();
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

    /*
    Calculate dmg persent to king
     */
    public double calDmgPercentKing(King enemy) {
        AtkType myAtkType = this.getAttackType();

        switch(myAtkType) {
            case Normal:
                return 0.85;
            case Pierce:
                return 0.85;
            case Seige:
                return 0.85;
            case Magic:
                return 0.85;
            case Chaos:
                return 1.1;
            case Hero:
                return 1.0;
        }
        return 0;
    }

    /*
    randomnized attack dmg
     */
    public double atkDamageRandomModifier(double minCon, double maxCon) {
        return ThreadLocalRandom.current().nextDouble(this.atk*minCon, this.atk*maxCon);
    }

    /*
    calculate actual dmg dealt
     */
    public double dmgCal(MinionImpl enemy) {
        double armorModifer;
        double dmgTypeModifier = calDmgPercent(enemy);
        assert(dmgTypeModifier!=0);
        double enemyArmor = enemy.getArmor();
        if (enemyArmor < 0) {
            //if armor is negative, follow the formula here
            armorModifer = 2-pow(0.84,(-enemyArmor));
        } else {
            armorModifer = 1- (enemyArmor*0.06)/(1+enemyArmor*0.06);
        }
        /*
        calculate damage and reduce target's hp
         */
        double realtimeAtk = atkDamageRandomModifier(randomMinConst, randomMaxConst);
        double dmgDealt = realtimeAtk * armorModifer * dmgTypeModifier;
        enemy.hp -= dmgDealt;
        return dmgDealt;
    }

    public double dmgCalKing(King enemy) {
        /*
        Similar as dmgCal but to King
         */
        double armorModifer;
        double dmgTypeModifier = calDmgPercentKing(enemy);
        assert(dmgTypeModifier!=0);
        double enemyArmor = enemy.getArmor();
        if (enemyArmor < 0) {
            armorModifer = 2-pow(0.84,(-enemyArmor));
        } else {
            armorModifer = 1- (enemyArmor*0.06)/(1+enemyArmor*0.06);
        }
        double realtimeAtk = atkDamageRandomModifier(randomMinConst, randomMaxConst);
        double dmgDealt = realtimeAtk * armorModifer * dmgTypeModifier;
        enemy.hp -= dmgDealt;
        return dmgDealt;
    }

    public double cal_distance(MinionImpl enemy) {
        /*
        Calculate dist to target
         */
        return sqrt(pow(this.Coords[0] - enemy.Coords[0], 2)+pow(this.Coords[1] - enemy.Coords[1], 2));
    }

    public double cal_dist_king(King target) {
        /*
        Calculate dist to King
         */
        return sqrt(pow(this.Coords[0] - target.kingPos[0], 2)+pow(this.Coords[1] - target.kingPos[1], 2));
    }

    public MinionImpl randomTarget(ArrayList<MinionImpl> mylist) {
        /*
        return target randomly
         */
        this.rand = new Random();
        MinionImpl randomMinion = mylist.get(this.rand.nextInt(mylist.size()));
        return randomMinion;
    }

    public MinionImpl chooseTarget(ArrayList<MinionImpl> enemies) {
        /*
        target choosing mechanism
         */
        if (enemies.isEmpty()) {
            return null;
        }
        MinionImpl target = new MinionImpl();
        ArrayList<MinionImpl> temp = new ArrayList<MinionImpl>();
        double minDist = 9999999;
        /*
        if only one target in dist return him
        if multiple in same dist, return a random one of the multiple list
         */
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

    public void chase(MinionImpl target) {
        /*
        chase target minion
         */
        double xdiff = target.Coords[0] - this.Coords[0];
        double ydiff = target.Coords[1] - this.Coords[1];

        double dist = cal_distance(target);
        double normalx = (xdiff/dist)*moveSpeed;
        double normaly = (ydiff/dist)*moveSpeed;

        this.Coords[0] += normalx;
        this.Coords[1] += normaly;
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + "'s target is " + target.minionName);
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " moved " + normalx + "," + normaly);
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " is at " + Arrays.toString(this.Coords));
    }

    public void approachKing(King target) {
        /*
        approach king's location
         */
        double xdiff = target.kingPos[0] - this.Coords[0];
        double ydiff = target.kingPos[1] - this.Coords[1];

        double dist = cal_dist_king(target);
        double normalx = (xdiff/dist)*moveSpeed;
        double normaly = (ydiff/dist)*moveSpeed;

        this.Coords[0] += normalx;
        this.Coords[1] += normaly;
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + "'s target is " + target.kingName);
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " moved " + normalx + "," + normaly);
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " is at " + Arrays.toString(this.Coords));
    }

    public void keepWalking() {
        /*
        keep walking to the portal if no enemies
         */
        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " keeps walking to fight for the " + this.master.myKing.kingName + " !");
        if (this.master.getTeam() == 1) {
            this.Coords[0] += moveSpeed;
        } else {
            this.Coords[0] -= moveSpeed;
        }

        System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " is at " + Arrays.toString(this.Coords));
//        checkPortal();
    }

    public void performAttack(ArrayList<MinionImpl> enemies) {
        MinionImpl target;

        /*
        if no enemies but is already king's guard, attack on the enmey king
        otherwise keep walking to the portal to fight for king

        if there is enmey, chase him if not in atkRange otherwise attack
         */
        if (enemies.isEmpty()) {
            if (this.myKing != null) {
                if (cal_dist_king(this.myKing.getOpponetKing()) > atkRange)
                    approachKing(this.myKing.getOpponetKing());
                else {
                    if (this.attackCounter >= this.attackSpeed){
                        double damage = this.dmgCalKing(this.myKing.getOpponetKing());
                        System.out.println(this.master.getPlayerName() + "'s " +
                                this.minionName + " dealt " + damage + " damage to " +
                                this.myKing.getOpponetKing().kingName);
                        System.out.println(this.myKing.getOpponetKing().hp);
                        if (this.myKing.checkDeath() || this.myKing.opponetKing.checkDeath()) {
                            System.exit(0);
                        }
                        this.attackCounter = 0;
                    }
                }
            } else {
                keepWalking();
            }
        } else {
            target = chooseTarget(enemies);
            if (cal_distance(target) > atkRange) {
                chase(target);
            } else {
                if (this.attackCounter >= this.attackSpeed){
                    double damage = this.dmgCal(target);
                    System.out.println(this.master.getPlayerName() + "'s " +
                            this.minionName + " dealt " + damage + " damage to " +
                            target.master.getPlayerName() + "'s " + target.minionName);
//                    target.dieForHonor();
                    this.attackCounter = 0;
                }
            }
        }
    }

    public boolean checkPortal() {
        /*
        if reach the portal, teleport to king's coords
         */
        if (this.master.getTeam() == 1) {
            if (this.Coords[0] > 100) { //why it hangs for higher numbers...
                System.out.println(this.minionName + " is gonna to fight for the King!");
                this.master.myKing.add_Minions(this);
                this.master.minions.remove(this);
                this.setCoords(this.myKing.kingArmyPos);
                System.out.println(this.master.myKing.kingName + "'s minions: " + this.master.myKing.getMinions().size());
                System.out.println("minions of " + this.master.getPlayerName() + ": " + this.master.getMinions().size());
                return true;
            }
            return false;
        } else {
            if (this.Coords[0] < 0) { //why it hangs for higher numbers...
                System.out.println(this.minionName + " is gonna to fight for the " + this.master.myKing.kingName + " !");
                this.master.myKing.add_Minions(this);
                this.master.minions.remove(this);
                this.setCoords(this.myKing.kingArmyPos);
                System.out.println(this.master.myKing.kingName + "'s minions: " + this.master.myKing.getMinions().size());
                System.out.println("minions of " + this.master.getPlayerName() + ": " + this.master.getMinions().size());
                return true;
            }
            return false;
        }
    }

    public void dieForHonor() {
        /*
        check if the minion died and remove him from player's list and all alive minion instances,
        if it is king's guard, revmove from king instead.
         */
        if (this.hp <= 0) {
            //destroy the minion?
            System.out.println(this.master.getPlayerName() + "'s " + this.minionName + " died for an honorable cause!");
            System.out.println("The " + this.master.myKing.kingName + " will remember him!");
            this.alive = false;
            this.master.remove_Minions(this);
            this.manager.getAllInstances().remove(this);
            if (this.myKing != null) {
                this.myKing.getMinions().remove(this);
            }
        }
    }

    /*
    function for sorting minion's atk sequence
     */
    public static Comparator<MinionImpl> byPriority()
    {
        return new Comparator<MinionImpl>()
        {
            @Override
            public int compare(MinionImpl o1, MinionImpl o2)
            {
                return o1.priority - o2.priority;
            }
        };
    }

    /*
    hp percent for health bar display
     */
    public double getHealthPercent() {
        return this.hp/this.maxhp;
    }
}
