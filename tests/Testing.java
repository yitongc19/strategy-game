import Model.CupCakeWarrior;
import Model.MinionImpl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class Testing {
    public static void main(String[] args) {
        double[] pos1 = {100, 200};
        double[] pos2 = {200, 300};
        double[] pos3 = {110, 150};
        double[] pos4 = {120, 130};
        double[] pos5 = {100, 180};
        double[] pos6 = {100, 100};
        ArrayList<MinionImpl> Enemies = new ArrayList<MinionImpl>();

//        how to do variable naming with var in java
//        for (int i = 1; i < 7; i += 1) {
//            CupCakeWarrior new
//        }
        CupCakeWarrior newcup1 = new CupCakeWarrior("Cup Cake Warrior 1", pos1);
        CupCakeWarrior newcup2 = new CupCakeWarrior("Cup Cake Warrior 2", pos2);
        CupCakeWarrior newcup3 = new CupCakeWarrior("Cup Cake Warrior 3", pos3);
        CupCakeWarrior newcup4 = new CupCakeWarrior("Cup Cake Warrior 4", pos4);
        CupCakeWarrior newcup5 = new CupCakeWarrior("Cup Cake Warrior 5", pos5);
        CupCakeWarrior newcup6 = new CupCakeWarrior("Cup Cake Warrior 6", pos6);

        Enemies.add(newcup1);
        Enemies.add(newcup2);
        Enemies.add(newcup3);
        Enemies.add(newcup4);
        Enemies.add(newcup5);
        System.out.println(newcup1.getHP());
        System.out.println(newcup1.hp);
        System.out.println(newcup1.dmgCal(newcup2));
        System.out.println(Arrays.toString(newcup6.chooseTarget(Enemies).Coords));
//        newcup6.performAttack(Enemies);

        int[] color = {10, 20, 30};
        Model.PlayerImpl soap = new Model.PlayerImpl(1, "Yitong", color);
        Model.PlayerImpl russell = new Model.PlayerImpl(1, "Russell", color);
        soap.add_Minions((newcup6));
        russell.add_Minions(newcup1);
        russell.add_Minions(newcup2);
        russell.add_Minions(newcup3);
        russell.add_Minions(newcup4);
        russell.add_Minions(newcup5);
        while(newcup4.hp>0) {
            newcup6.attackCounter += 1;
            soap.attack(russell);
        }
    }
}