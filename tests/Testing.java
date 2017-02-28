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
        double[] pos4 = {75, 130};
        double[] pos5 = {70, 100};
        double[] pos6 = {50, 100};
        ArrayList<MinionImpl> Enemies = new ArrayList<MinionImpl>();

//        how to do variable naming with var in java
//        for (int i = 1; i < 7; i += 1) {
//            CupCakeWarrior new
//        }


        int[] color = {10, 20, 30};
        Model.PlayerImpl yitong = new Model.PlayerImpl(1, "Yitong", color);
        Model.PlayerImpl russell = new Model.PlayerImpl(1, "Russell", color);
//        yitong.add_Minions(newcup6);
//        yitong.add_Minions(newcup1);
//        russell.add_Minions(newcup6);
//        russell.add_Minions(newcup2);
//        russell.add_Minions(newcup3);
//        russell.add_Minions(newcup4);
//        russell.add_Minions(newcup5);

        CupCakeWarrior newcup1 = new CupCakeWarrior("Cup Cake Warrior 1", russell, pos1);
        CupCakeWarrior newcup2 = new CupCakeWarrior("Cup Cake Warrior 2", russell, pos2);
        CupCakeWarrior newcup3 = new CupCakeWarrior("Cup Cake Warrior 3", russell, pos3);
        CupCakeWarrior newcup4 = new CupCakeWarrior("Cup Cake Warrior 4", russell, pos4);
        CupCakeWarrior newcup5 = new CupCakeWarrior("Cup Cake Warrior 5", russell, pos5);
        CupCakeWarrior newcup6 = new CupCakeWarrior("Cup Cake Warrior 6", yitong, pos6);
        CupCakeWarrior newcup7 = new CupCakeWarrior("Cup Cake Warrior 7", yitong, pos5);

        while(!russell.getMinions().isEmpty() && !yitong.getMinions().isEmpty()) {
            newcup6.attackCounter += 1;
            newcup7.attackCounter += 1;
            yitong.attack(russell);
        }
    }
}