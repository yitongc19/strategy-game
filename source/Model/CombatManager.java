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

    private int numPlayerTeam1 = 2;
    private int numPlayerTeam2 = 2;
    private int totalBuffTeam1;
    private int totalBuffTeam2;

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
                }
                //otherwise attack!
                instances.get(i).performAttack(instances.get(i).master.opponent.getMinions());

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

    /* return the total number of buffs used so far by team1 */
    public int getTotalBuffTeam1() {
        return totalBuffTeam1;
    }

    /* return the total number of buffs used so far by team2 */
    public int getTotalBuffTeam2() {
        return totalBuffTeam2;
    }

    /* return the number of players in team1 */
    public int getNumPlayerTeam1() {
        return numPlayerTeam1;
    }

    /* set the number of players in team1 to the given number */
    public void setNumPlayerTeam1(int numPlayerTeam1) {
        this.numPlayerTeam1 = numPlayerTeam1;
    }

    /* return the number of players in team2*/
    public int getNumPlayerTeam2() {
        return numPlayerTeam2;
    }

    /* set the number of players in team2 to the given number */
    public void setNumPlayerTeam2(int numPlayerTeam2) {
        this.numPlayerTeam2 = numPlayerTeam2;
    }

    /* set the number of buff used by team1 */
    public void setTotalBuffTeam1(int totalBuffTeam1) {
        this.totalBuffTeam1 = totalBuffTeam1;
    }

    /* set the number of buff used by team2 */
    public void setTotalBuffTeam2(int totalBuffTeam2) {
        this.totalBuffTeam2 = totalBuffTeam2;
    }
}
