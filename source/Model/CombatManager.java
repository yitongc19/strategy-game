package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static Model.MinionImpl.byPriority;

/**
 * Created by xingfanxia on 2/28/17.
 * Used to controll the combat flow, will be used in fight controller
 */
public class CombatManager {
    static ArrayList<MinionImpl> instances = new ArrayList<MinionImpl>();

    private PlayerImpl player1;
    private PlayerImpl player2;

    //a global ArrayList containing all alive minions
    public ArrayList<MinionImpl> getAllInstances() {
        return instances;
    }

    //Add attack counter to all the minions
    public void addAtkCounterToAll() {
        for (MinionImpl minion:this.getAllInstances()) {
            minion.attackCounter += 0.1;
        }

    }

    //print out minions by their name
    private void printMinions(ArrayList<MinionImpl> minions) {
        System.out.print("[");
        for (MinionImpl each:minions) {
            System.out.print(each.minionName + ",");

        }
        System.out.print("] \n");
    }

    /*
    The standard combat flow loop
     */
    public void doCombat(PlayerImpl player1, PlayerImpl player2) {
        player1.getKing().setOpponetKing(player2.getKing());
        //sort minion atkSequence by their priority
        Collections.sort(instances, Collections.reverseOrder(byPriority()));

            this.addAtkCounterToAll();
        for (int i = 0; i < instances.size(); i += 1) {
            if (instances.get(i).myKing == null) {
                //if the minion has no king then it hasn't teleported yet so check portal
                instances.get(i).checkPortal();
                instances.get(i).performAttack(instances.get(i).master.opponent.getMinions());
            } else {
                //otherwise attack
                instances.get(i).performAttack(instances.get(i).master.myKing.getOpponetKing().getMinions());
            }
        }
            //increment king's atkCounter
            player1.getKing().atkCounter += 0.1;
            player2.getKing().atkCounter += 0.1;

            //let king fight whatever enemy exists
            player1.getKing().kingFight();
            player2.getKing().kingFight();

            //check if anyone died here
            for (int i = 0; i < instances.size(); i += 1) {
                instances.get(i).dieForHonor();
            }
    }

}
