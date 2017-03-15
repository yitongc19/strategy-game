package Controller;
//controller for running the main battle loop at the start of a fight scene.
//Stores a list of players.


import Model.*;
import View.InterPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;


import java.util.List;

public class FightController {
    private List<PlayerImpl> players;


    public FightController() {
        return;
    }

    public void setPlayers(List<PlayerImpl> players) {
        this.players = players;
    }

    public AnimationTimer runFight(CombatManager manager, GraphicsContext graphics, GraphicsContext graphics1) {

        for (int i = 0; i < players.size(); i += 1) {
            PlayerImpl curPlayer = players.get(i);
            for (int j = 0; j < curPlayer.getBuildings().size(); j = j + 1) {
                BuildingImpl building = curPlayer.getBuildings().get(j);
                building.spawnMinion(manager);
            }
        }


        for (int i = 0; i <players.size()/2; i += 1) {
            players.get(i).setOpponent(players.get(i+players.size()/2));
        }

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                }

                if (manager.getAllInstances().size() == 0) {

                    InterPlayer interPlayer = new InterPlayer(manager.getCurrentController(), manager.getCurrentController().getPlayers().get(0));
                  manager.getCurrentController().setNumRemainingPlayers(manager.getCurrentController().getNumRemainingPlayers() - 1);
                    try {
                        interPlayer.start(interPlayer.getInterPlayerStage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    manager.getCurrentGameStage().close();
                    this.stop();
                }

                manager.doCombat(players.get(0), players.get(players.size()/2));
                graphics.clearRect(0,0,2000,1000);
                for (int i = 0; i < manager.getAllInstances().size(); i = i + 1) {
                    MinionImpl minion = manager.getAllInstances().get(i);
                    double coords[] = minion.getCoords();
                    if (minion.master.getTeam() == 1) {
                        graphics.setFill(Color.RED);
                        graphics.fillRect(minion.getCoords()[0], minion.getCoords()[1], 30, 4);
                        graphics.setFill(Color.GREEN);
                        graphics.fillRect(minion.getCoords()[0], minion.getCoords()[1], 30*minion.getHealthPercent(), 4);
                    } else {
                        graphics.setFill(Color.RED);
                        graphics.fillRect(minion.getCoords()[0]+20, minion.getCoords()[1], 30, 4);
                        graphics.setFill(Color.GREEN);
                        graphics.fillRect(minion.getCoords()[0]+20, minion.getCoords()[1], 30*minion.getHealthPercent(), 4);
                    }

                    minion.sprite.setPos(coords[0], coords[1]);
                    minion.render(graphics);
                }
                graphics.setFill(Color.RED);
                graphics.fillRect(players.get(0).myKing.kingPos[0] -200, players.get(0).myKing.kingPos[1]+60, 210, 10);
                graphics.setFill(Color.GREEN);
                graphics.fillRect(players.get(0).myKing.kingPos[0] -200, players.get(0).myKing.kingPos[1]+60, 210*players.get(0).myKing.getHpPercent(), 10);

                graphics.setFill(Color.RED);
                graphics.fillRect(players.get(players.size()/2).myKing.kingPos[0] + 50, players.get(players.size()/2).myKing.kingPos[1]+60, 210, 10);
                graphics.setFill(Color.GREEN);
                graphics.fillRect(players.get(players.size()/2).myKing.kingPos[0] + 50, players.get(players.size()/2).myKing.kingPos[1]+60, 210*players.get(players.size()/2).myKing.getHpPercent(), 10);
            }
        };

        return animationTimer;
    }

    public void showBuildings(GraphicsContext gc) {

        for (int i = 0; i < players.size(); i = i + 1) {
            PlayerImpl curPlayer = players.get(i);
            for (int j = 0; j < curPlayer.getBuildings().size(); j = j + 1) {
                BuildingImpl building = curPlayer.getBuildings().get(j);
                building.render(gc);
            }
        }
    }
}