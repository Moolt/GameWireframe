/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Drawing;

import Engine.Main.Scene;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Moolt
 */
public class DrawingPanel extends JPanel {

    private Scene level;
    private double lag;

    public DrawingPanel() {
        this.lag = 1D;
    }

    public DrawingPanel(Scene level) {
        this.level = level;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        this.level.Draw(canvas, this.lag);     
    }
    
    public void Draw(double lag){
        this.lag = lag;
        this.repaint();
    }
}
