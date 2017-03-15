package Model;

/**
 * Created by xingfanxia on 3/9/17.
 */
public class ShieldKnightBuilding extends BuildingImpl{

    public ShieldKnightBuilding() {
        this.buildingName = "Knight Academy";
        this.displayedImage = "some path";
        this.cost = 300;
        this.owner = null;
        this.minion = new ShieldKnight();
    }

    public ShieldKnightBuilding(PlayerImpl owner) {
        this.buildingName = "Knight Academy";
        this.displayedImage = "some path";
        this.cost = 300;
        this.owner = owner;
    }


}
