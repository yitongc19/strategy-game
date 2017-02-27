package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 2/26/17.
 */
public class PlayerImpl implements Player {
    private Integer gold;
    private ArrayList<Building> buildings = new ArrayList<Building>();
    private King myKing;
    private Integer myScore;
    private int teamNum;
    private String playerName;
    private int[] playerColor; //in rgba

    public PlayerImpl(int teamNum, String playerName, int[] playerColor) {
        this.gold = 0;
        this.playerName = playerName;
        this.teamNum = teamNum;
        this.playerColor = playerColor;
        this.myScore = 0;
        this.myKing = new King(teamNum);
    }

    @Override
    public Integer getGold() {
        return this.gold;
    }

    @Override
    public ArrayList getBuildings() {
        return this.buildings;
    }

    @Override
    public King getKing() {
        return this.myKing;
    }

    @Override
    public Integer getScore() {
        return this.myScore;
    }

    @Override
    public int getTeam() {
        return this.teamNum;
    }

    @Override
    public void setScore(Integer score) {
        this.myScore = score;
    }

    @Override
    public void setTeam(int teamNum) {
        this.teamNum = teamNum;
    }

    @Override
    public void add_Building(Building building) {
        this.buildings.add(building);
    }

    @Override
    public void getFarmers() {

    }

    @Override
    public void spawn_farmer() {

    }

    @Override
    public Integer getCrystal() {
        return null;
    }

    @Override
    public ArrayList getMercenaries() {
        return null;
    }

    @Override
    public void upgradeKing(King king) {

    }

    public static void main(String[] args) {
        int[] color = {10, 20, 30};
        Model.PlayerImpl soap = new Model.PlayerImpl(1, "soap", color);
        System.out.println(soap.getScore());
    }
}
