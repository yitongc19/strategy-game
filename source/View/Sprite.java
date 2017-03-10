package View;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Russll on 3/8/17.
 */
public class Sprite {
    private Image image;
    private double x;
    private double y;
    private double width;
    private double height;


    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setPos(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void render(GraphicsContext gc)
    {
        System.out.println("RENDER CALLED");
        System.out.print(x);
        System.out.print(", ");
        System.out.println(y);

        gc.drawImage( image, x, y );
    }

}
