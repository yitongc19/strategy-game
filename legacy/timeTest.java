import java.lang.Thread;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class timeTest {


	public static void main (String[] args) {
        JFrame frame = new JFrame("TimeTest");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        ImageIcon image = new ImageIcon("cupcakeDefault.png");
        JLabel imageLabel = new JLabel(image);
        imageLabel.setVisible(true);
        frame.add(imageLabel);
        
        ImageIcon image2 = new ImageIcon("cupcakeFight.png");
        JLabel imageLabel2 = new JLabel(image2);
        imageLabel2.setVisible(false);
        frame.add(imageLabel2);
        
        
        
        frame.setSize(200, 229);
        frame.setVisible(true);
        
        int i = 0;
        try {
        	while (true) {
        		Thread.sleep(100);
        		if ((i%2) == 0) {
        			imageLabel2.setVisible(true);
        			imageLabel.setVisible(false);
        		}
        		else {
        			imageLabel2.setVisible(false);
        			imageLabel.setVisible(true);
        		}
        	i = i + 1;
        	}
        }
        catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }













}