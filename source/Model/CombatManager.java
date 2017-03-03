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
            minion.attackCounter += 1;
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
//        ArrayList<MinionImpl> King1Armry = player1.getKing().getMinions();
//        ArrayList<MinionImpl> King2Armry = player2.getKing().getMinions();
//        ArrayList<MinionImpl> player1Army = player1.getMinions();
//        ArrayList<MinionImpl> player2Army = player2.getMinions();
//
//        Collections.sort(player1Army, Collections.reverseOrder(byPriority()));
//        Collections.sort(player2Army, Collections.reverseOrder(byPriority()));

        Collections.sort(instances, Collections.reverseOrder(byPriority()));
        printMinions(instances);

//        while (!player1.getMinions().isEmpty() || !player2.getMinions().isEmpty()) {

            ArrayList<MinionImpl> playerSoldiers = new ArrayList<MinionImpl>();
            playerSoldiers.addAll(player1.getMinions());
            playerSoldiers.addAll(player2.getMinions());

            this.addAtkCounterToAll();
            for (int i = 0; i < playerSoldiers.size(); i += 1) {
                if (playerSoldiers.get(i).checkPortal()) {
                    continue;
                }
                playerSoldiers.get(i).performAttack(playerSoldiers.get(i).master.opponent.getMinions());
            }

            for (int i = 0; i < playerSoldiers.size(); i += 1) {
                playerSoldiers.get(i).dieForHonor();
            }

            if (!player1.getKing().getMinions().isEmpty() || !player2.getKing().getMinions().isEmpty()) {
                
            }

//        }
        for (MinionImpl each:instances) {
            System.out.println(each.myKing.kingName + "'s :" + each.minionName + "'s hp is" + each.hp);
        }
        System.exit(0);

    }
}
