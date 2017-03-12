package Controller;

import Model.*;

/**
 * Created by Russll on 3/10/17.
 */
public class GameController {
    private PlayerImpl[] players;
    private int numPlayers;


    public GameController(int numPlayers) {
        this.numPlayers = numPlayers;
        this.players = new PlayerImpl[numPlayers];
    }

    public PlayerImpl[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerImpl[] players) {
        this.players = players;
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
        PlayerImpl player1 = getPlayers()[0];
        PlayerImpl player2 = getPlayers()[1];

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
}
