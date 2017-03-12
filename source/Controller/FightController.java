package Controller;


import Model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FightController implements FightControllerInterface {
    private PlayerImpl[] players;


    public FightController() {
        return;
    }

    public void setPlayers(PlayerImpl[] players) {
        this.players = players;
    }

    public void runFight(CombatManager manager, GraphicsContext graphics, GraphicsContext graphics1) {
        PlayerImpl player1 = players[0];
        PlayerImpl player2 = players[1];

        player1.setOpponent(player2);

        System.out.println(player1.getBuildings().size());
        System.out.println(player2.getBuildings().size());

        for (int j = 0; j < player1.getBuildings().size(); j = j + 1) {
            BuildingImpl building = player1.getBuildings().get(j);
            building.spawnMinion(manager);
        }
        for (int k = 0; k < player2.getBuildings().size(); k = k + 1) {
            BuildingImpl building = player2.getBuildings().get(k);
            building.spawnMinion(manager);
        }


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException e) {
                    System.out.println(e);
                }

                manager.doCombat(player1, player2);

                /*
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(MinionImpl.class.getResource("MinionGrunt.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                    }
                catch (Exception e) {
                    System.out.println(e);
                    }
                    */

                graphics.clearRect(0,0,2000,1000);
                for (int i = 0; i < manager.getAllInstances().size(); i = i + 1) {
                    MinionImpl minion = manager.getAllInstances().get(i);
                    double coords[] = minion.getCoords();
                    minion.sprite.setPos(coords[0], coords[1]);
                    minion.render(graphics);
                }
//                for (int j = 0; j < player2.minions.size(); j = j + 1) {
//                    MinionImpl minion = player2.minions.get(j);
//                    double coords[] = minion.getCoords();
//                    minion.sprite.setPos(coords[0], coords[1]);
//                    minion.render(graphics);
//                }

            }
        }.start();
    }

    public void showBuildings(GraphicsContext gc) {
        PlayerImpl player1 = players[0];
        PlayerImpl player2 = players[1];

        for (int j = 0; j < player1.getBuildings().size(); j = j + 1) {
            BuildingImpl building = player1.getBuildings().get(j);
            building.render(gc);
        }
        for (int k = 0; k < player2.getBuildings().size(); k = k + 1) {
            BuildingImpl building = player2.getBuildings().get(k);
            building.render(gc);
        }
    }
}