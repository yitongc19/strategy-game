package Model;

/**
 * Created by xingfanxia on 3/9/17.
 */
public class CupCakeWarrirorBuilding extends BuildingImpl{

    public CupCakeWarrirorBuilding(PlayerImpl owner) {
        this.buildingName = "Warrior Camp";
        this.displayedImage = "some path";
        this.cost = 200;
        this.owner = owner;
    }

}
