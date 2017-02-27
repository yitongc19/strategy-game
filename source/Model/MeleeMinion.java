package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 2/27/17.
 */
public class MeleeMinion extends MinionImpl {
    public MinionImpl targetChoosing(ArrayList<MinionImpl> enemies) {
        MinionImpl target = new MinionImpl();
        double minDist = 9999999;
        for (MinionImpl warrior: enemies){
            double dist = cal_distance(warrior);
            if (dist < minDist) {
                minDist = dist;
                target = warrior;
            }
        }
        return target;
    }
}
