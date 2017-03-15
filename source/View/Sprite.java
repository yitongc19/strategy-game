package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Russll on 3/8/17. Class that allows game objects to render their images to the screen
 */
public class Sprite {
    private Image image;
    private double x;
    private double y;
    private double width;
    private double height;


    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage( image, x, y );
    }

}
