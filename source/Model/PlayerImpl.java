package Model;

import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by xingfanxia on 2/26/17.
 */
public class PlayerImpl implements Player, Comparable<PlayerImpl> {
    /*
    player class holding all relevant attributes like
    gold, king, score, name, team, color, minions and buildings
     */
//    CombatManager manager = new CombatManager();
    public Integer gold;
    private ArrayList<BuildingImpl> buildings = new ArrayList<BuildingImpl>();
    public ArrayList<MinionImpl> minions = new ArrayList<MinionImpl>();
    public King myKing;
    public int myScore;
    private int teamNum;
    private String playerName;
    private Paint playerColor; //in rgba
    private double xOffset;
    private double yOffset;

    public PlayerImpl opponent;
    public PlayerImpl(int teamNum, String playerName, Paint playerColor) {
        this.gold = 2000;
        this.playerName = playerName;
        this.teamNum = teamNum;
        this.playerColor = playerColor;
        this.myScore = 0;
        this.opponent = opponent;
    }

    public double getxOffset() {
        return this.xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public Integer getGold() {
        return this.gold;
    }

    public ArrayList<BuildingImpl> getBuildings() {
        return this.buildings;
    }

    public ArrayList<MinionImpl> getMinions() {
        return minions;
    }

    public String getPlayerName() {
        return playerName;
    }

    public King getKing() {
        return this.myKing;
    }

    public int getScore() {
        return this.myScore;
    }

    public int getTeam() {
        return this.teamNum;
    }

    public void setScore(Integer score) {
        this.myScore = score;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setTeam(int teamNum) {
        this.teamNum = teamNum;
    }

    public void add_Building(BuildingImpl building) {
        this.buildings.add(building);
    }

    public void add_Minions(MinionImpl minion) {
        this.minions.add(minion);
        minion.master = this;
    }

    public void remove_Minions(MinionImpl minion) {
        this.minions.remove(minion);
    }

    public PlayerImpl getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerImpl opponent) {
        this.opponent = opponent;
        opponent.opponent = this;
    }

    /*
    To be implemented
     */
    public void getFarmers() {

    }

    public void spawn_farmer() {

    }

    public Integer getCrystal() {
        return null;
    }

    public ArrayList getMercenaries() {
        return null;
    }

    public void upgradeKing(King king) {

    }

    @Override
    public int compareTo(PlayerImpl o) {
        Integer o1Score = this.getScore();
        Integer o2Score = o.getScore();
        return o1Score.compareTo(o2Score);
    }

    public static class Comparators {
        public static Comparator<PlayerImpl> SCORE = new Comparator<PlayerImpl>() {
            @Override
            public int compare(PlayerImpl o1, PlayerImpl o2) {
                Integer o1Score = o1.getScore();
                Integer o2Score = o2.getScore();

                return o1Score.compareTo(o2Score);
            }
        };
    }

}
