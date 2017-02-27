package Model;

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
        CupCakeWarrior newcup1 = new CupCakeWarrior(pos1);
        CupCakeWarrior newcup2 = new CupCakeWarrior(pos2);
        CupCakeWarrior newcup3 = new CupCakeWarrior(pos3);
        CupCakeWarrior newcup4 = new CupCakeWarrior(pos4);
        CupCakeWarrior newcup5 = new CupCakeWarrior(pos5);
        CupCakeWarrior newcup6 = new CupCakeWarrior(pos6);

        Enemies.add(newcup1);
        Enemies.add(newcup2);
        Enemies.add(newcup3);
        Enemies.add(newcup4);
        Enemies.add(newcup5);
        System.out.println(newcup1.getHP());
        System.out.println(newcup1.hp);
        System.out.println(newcup1.dmgCal(newcup2));
        System.out.println(Arrays.toString(newcup6.chooseTarget(Enemies).Coords));
        newcup6.attackCounter += 1;
        newcup6.performAttack(Enemies);
        System.out.println(newcup4.getHP());
    }
}