package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 2/25/17.
 */
public interface Building {
    Integer getCost();

    ArrayList<Building> upgradeTree();

    /*
    Upgrade to another building, essentially change the
    image displayed and set the newMinion to be spawned
     */
    void upgrade();

    /* to be called in upgrade */
    void setImage();

    String getName();

    /* spawn my minion
     */
    MinionImpl spawnMinion();

    /* to be called in upgrade */
    void setMinion();

    int[] getPosition(); //position on the grid
}
