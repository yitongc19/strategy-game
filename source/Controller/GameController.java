package Controller;

//controller for the whole game. This class just serves as a way to store our players,
//add them to the game, and keep track of their numbers as we pass them from view to view

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
        this.numRemainingPlayers = playernum - 1;
        this.numPlayers = playernum;
        this.players = new ArrayList<>();
        numPlayerTeam1 = numPlayers / 2;
        numPlayerTeam2 = numPlayers / 2;
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

    /* return the number of players in team2*/
    public int getNumPlayerTeam2() {
        return numPlayerTeam2;
    }

    /* set the number of buff used by team1 */
    public void setTotalBuffTeam1(int totalBuffTeam1) {
        this.totalBuffTeam1 = totalBuffTeam1;
    }

    /* set the number of buff used by team2 */
    public void setTotalBuffTeam2(int totalBuffTeam2) {
        this.totalBuffTeam2 = totalBuffTeam2;
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

}
