package View;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by xingfanxia on 2/25/17.
 */
public class GameMap implements Observer{	//render map, buildings, team interface
    public void map_init() {
        System.out.print("map initialized");
    }

    //spwan minions
    public void spawn_minions() {
        System.out.println("minions spwaned");
    }

    //display minion action, animation
    public void display_minions() {
        System.out.println("display minion actions!");
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update map based on model");
    }
}
