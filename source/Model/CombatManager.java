package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static Model.MinionImpl.byPriority;

/**
 * Created by xingfanxia on 2/28/17.
 */
public class CombatManager {
    static ArrayList<MinionImpl> instances = new ArrayList<MinionImpl>();

    private PlayerImpl player1;
    private PlayerImpl player2;

    public ArrayList<MinionImpl> getAllInstances() {
        return instances;
    }

    public void addAtkCounterToAll() {
        for (MinionImpl minion:this.getAllInstances()) {
            minion.attackCounter += 0.1;
        }

    }

    private void printMinions(ArrayList<MinionImpl> minions) {
        System.out.print("[");
        for (MinionImpl each:minions) {
            System.out.print(each.minionName + ",");

        }
        System.out.print("] \n");
    }
    public void doCombat(PlayerImpl player1, PlayerImpl player2) {
        player1.getKing().setOpponetKing(player2.getKing());
        Collections.sort(instances, Collections.reverseOrder(byPriority()));

//        while (!player1.getMinions().isEmpty() || !player2.getMinions().isEmpty()) {

//            ArrayList<MinionImpl> playerSoldiers = new ArrayList<MinionImpl>();
//            ArrayList<MinionImpl> kingsoldiers = new ArrayList<MinionImpl>();
//            playerSoldiers.addAll(player1.getMinions());
//            playerSoldiers.addAll(player2.getMinions());

            this.addAtkCounterToAll();
            for (int i = 0; i < instances.size(); i += 1) {
                if (instances.get(i).myKing == null) {
                    instances.get(i).checkPortal();
                }
                instances.get(i).performAttack(instances.get(i).master.opponent.getMinions());

            }
            player1.getKing().atkCounter += 0.1;
            player2.getKing().atkCounter += 0.1;
            player1.getKing().kingFight();
            player2.getKing().kingFight();
            for (int i = 0; i < instances.size(); i += 1) {
                instances.get(i).dieForHonor();
            }


//        }
//        for (MinionImpl each:instances) {
//            System.out.println(each.myKing.kingName + "'s :" + each.minionName + "'s hp is" + each.hp);
//        }


    }
}
