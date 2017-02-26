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

    //players' buildings
    void add_Building(Building building);

    ArrayList getBuildings();


    Integer getScore();

    void setScore();

    /* To be implemented in future */
    void getFarmers();

    void spawn_farmer();

    Integer getCrystal();

    ArrayList getMercenaries();

    void upgradeKing(King king);
}
