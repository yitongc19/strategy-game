package Model;

import View.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by xingfanxia on 3/9/17.
 */
public class CupCakeWarriorBuilding extends BuildingImpl{

    public CupCakeWarriorBuilding() {
        this.minion = new CupCakeWarrior();
        this.buildingName = "Warrior Camp";
        this.displayedImage = "some path";
        this.cost = 200;
        Image temp1 = new Image("file:assets/swordmanT1/t1buildgood.gif");
        Image temp2 = new Image("file:assets/swordmanT1/t1buildevil.gif");
        this.buildingImageViewLight = new ImageView(temp1);
        this.buildingImageViewDark = new ImageView(temp2);
    }

    public CupCakeWarriorBuilding(PlayerImpl owner) {
        this.minion = new CupCakeWarrior();
        this.buildingName = "Warrior Camp";
        this.displayedImage = "some path";
        this.cost = 200;
        this.owner = owner;

        owner.add_Building(this);

        this.sprite = new Sprite();
        if (this.owner.getTeam() == 1) {
            //this.spriteImage = new Image("file:assets/swordmanT1/t1buildgood.gif", 50, 50, true, true);
            this.spriteImage = new Image("file:assets/swordmanT1/t1buildgood.gif", 50, 50, true, true);

        } else if (this.owner.getTeam() == 2){
            this.spriteImage = new Image("file:assets/swordmanT1/t1buildevil.gif", 50, 50, true, true);
        }
        this.sprite.setImage(spriteImage);

    }

    public void spawnMinion(CombatManager manager) {
        System.out.println("SPAWNED");
        double[] coords = this.getScreenCoords();

        CupCakeWarrior newcup1 = new CupCakeWarrior(manager, "Cup Cake Warrior", this.owner, coords);
    }

}
