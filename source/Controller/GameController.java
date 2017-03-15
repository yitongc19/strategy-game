package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russll on 3/10/17.
 */
public class GameController {
    private List<PlayerImpl> players;

    private int numPlayers;
    private int numRemainingPlayers;

    private int numPlayerTeam1;
    private int numPlayerTeam2;
    private int totalBuffTeam1;
    private int totalBuffTeam2;


    public GameController(int playernum) {
        this.numRemainingPlayers = playernum;
        this.numPlayers = playernum;
        this.players = new ArrayList<>();
        numPlayerTeam1 = numPlayers / 2;
        numPlayerTeam2 = numPlayers / 2;
    }
    public int getNumRemainingPlayers() {
        return numRemainingPlayers;
    }

    public void setNumRemainingPlayers(int numRemainingPlayers) {
        this.numRemainingPlayers = numRemainingPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public List<PlayerImpl> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerImpl> players) {
        this.players = players;
    }

    public void addPlayer(PlayerImpl player) {
        players.add(player);
    }

    public void setUpGame() {
        double[] pos1 = {1220, 0};
        double[] pos2 = {1200, 30};
        double[] pos3 = {1230, 60};
        double[] pos4 = {1200, 90};
        double[] pos5 = {1200, 120};
        double[] pos6 = {200, 0};
        double[] pos7 = {200,20};
        double[] pos8 = {250, 60};
        double[] pos9 = {200, 80};
        double[] pos10 = {200, 130};

//        how to do variable naming with var in java
//        for (int i = 1; i < 7; i += 1) {
//            CupCakeWarrior new
//        }


        int[] color = {10, 20, 30};
        PlayerImpl player1 = getPlayers().get(0);
        PlayerImpl player2 = getPlayers().get(1);

        CupCakeWarriorBuilding build1 = new CupCakeWarriorBuilding(player1);
        build1.setScreenCoords(pos1);

        CupCakeWarriorBuilding build2 = new CupCakeWarriorBuilding(player1);
        build2.setScreenCoords(pos2);

        CupCakeWarriorBuilding build3 = new CupCakeWarriorBuilding(player1);
        build3.setScreenCoords(pos3);

        CupCakeWarriorBuilding build4 = new CupCakeWarriorBuilding(player1);
        build4.setScreenCoords(pos4);

        CupCakeWarriorBuilding build5 = new CupCakeWarriorBuilding(player1);
        build5.setScreenCoords(pos5);

        CupCakeWarriorBuilding build6 = new CupCakeWarriorBuilding(player2);
        build6.setScreenCoords(pos6);

        CupCakeWarriorBuilding build7 = new CupCakeWarriorBuilding(player2);
        build7.setScreenCoords(pos7);

        CupCakeWarriorBuilding build8 = new CupCakeWarriorBuilding(player2);
        build8.setScreenCoords(pos8);

        CupCakeWarriorBuilding build9 = new CupCakeWarriorBuilding(player2);
        build9.setScreenCoords(pos9);



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
