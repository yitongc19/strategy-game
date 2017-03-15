package Model;

import View.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Created by xingfanxia on 3/1/17.
 * the building implementation
 * -- this class is incomplete--
 */
public class BuildingImpl {

    String buildingName;
    String displayedImage;
    Image spriteImage;
    String buildingImagePath_V1;
    String buildingImagePath_V2;
    String buildingImagePath_V3;
    int cost;
    Integer upgradeCost;
    MinionImpl minion;

    ArrayList<String> upgradeTree;
    PlayerImpl owner;

    int[] gridCoords;
    double[] screenCoords;

    public Sprite sprite = new Sprite();

    public MinionImpl getMinion() {return this.minion;}

    public Integer getCost() {
        return this.cost;
    }

    public ArrayList<Building> upgradeTree() {
        return null;
    }

    public void upgrade(int level, MinionImpl upgraded) {
        setImage(upgradeTree.get(level));
        owner.gold -= upgradeCost;
    }

    public void render(GraphicsContext gc) {
        System.out.println("build Coords: " + this.screenCoords[0] + ", " + this.screenCoords[1]);
        this.sprite.setPos(this.screenCoords[0], this.screenCoords[1]);
        this.sprite.render(gc);
    }

    public void setImage(String imagePath) {
        this.displayedImage = imagePath;
    }

    public String getName() {
        return this.buildingName;
    }

    public void spawnMinion(CombatManager manager) {
        return;
    }

    public int[] getGridCoords() {
        return gridCoords;
    }

    public void setGridCoords(int[] gridCoords) {
        this.gridCoords = gridCoords;
    }

    public void setScreenCoords(double[] screenCoords) {
        this.screenCoords = screenCoords;
    }

    public double[] getScreenCoords() {
        return screenCoords;
    }
}
