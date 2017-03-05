package Model;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 3/1/17.
 * the building implementation
 * -- this class is incomplete--
 */
public class BuildingImpl {

    String buildingName;
    String displayedImage;
    String buildingImagePath_V1;
    String buildingImagePath_V2;
    String buildingImagePath_V3;
    Integer cost;
    Integer upgradeCost;

    ArrayList<String> upgradeTree;
    MinionImpl toSpwan;
    PlayerImpl owner;

    int[] gridCoords;


    public Integer getCost() {
        return this.cost;
    }

    public ArrayList<Building> upgradeTree() {
        return null;
    }

    public void upgrade(int level, MinionImpl upgraded) {
        setImage(upgradeTree.get(level));
        setToSpwan(upgraded);
        owner.gold -= upgradeCost;
    }

    public void setImage(String imagePath) {
        this.displayedImage = imagePath;
    }

    public String getName() {
        return this.buildingName;
    }

    public MinionImpl spawnMinion() {
        //spawn minion at location according to building location
        return null;
    }

    public int[] getGridCoords() {
        return gridCoords;
    }

    public void setGridCoords(int[] gridCoords) {
        this.gridCoords = gridCoords;
    }


    public MinionImpl getToSpwan() {
        return toSpwan;
    }

    public void setToSpwan(MinionImpl toSpwan) {
        this.toSpwan = toSpwan;
    }


}
