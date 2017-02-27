package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 2/25/17.
 */
public interface Player {
    /*
    accessors
     */

    //get gold
    Integer getGold();

    ArrayList getBuildings();

    King getKing();

    Integer getScore();

    // 1 for team 1, 2 for team 2
    int getTeam();

    //setter
    void setScore(Integer score);

    void setTeam(int teamNum);

    // add buildings
    void add_Building(Building building);

    public void add_Minions(MinionImpl minion);

    public ArrayList<MinionImpl> getMinions();

    /* To be implemented in future */
    void getFarmers();

    void spawn_farmer();

    Integer getCrystal();

    ArrayList getMercenaries();

    void upgradeKing(King king);

    void attack(Player opponent);
}
