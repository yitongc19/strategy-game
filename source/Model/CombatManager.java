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
        ArrayList<MinionImpl> player1Army = player1.getMinions();
        ArrayList<MinionImpl> player2Army = player2.getMinions();

        Collections.sort(player1Army, Collections.reverseOrder(byPriority()));
        Collections.sort(player2Army, Collections.reverseOrder(byPriority()));

    }
}
