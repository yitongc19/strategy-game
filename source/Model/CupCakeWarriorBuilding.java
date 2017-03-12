package Model;

import View.Sprite;
import javafx.scene.image.Image;

/**
 * Created by xingfanxia on 3/9/17.
 */
public class CupCakeWarriorBuilding extends BuildingImpl{

    public CupCakeWarriorBuilding(PlayerImpl owner) {
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
        double[] coords = this.getScreenCoords();

        CupCakeWarrior newcup1 = new CupCakeWarrior(manager, "Cup Cake Warrior", this.owner, coords);
        System.out.println("SPAWNED MINION");

        System.out.println(coords);

        System.out.println(coords[0]);
        System.out.println(coords[1]);
    }

}
