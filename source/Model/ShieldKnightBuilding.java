package Model;

import View.Sprite;
import javafx.scene.image.Image;

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
            this.minion = new ShieldKnight();
            this.buildingName = "Warrior Camp";
            this.displayedImage = "some path";
            this.cost = 300;
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


}
