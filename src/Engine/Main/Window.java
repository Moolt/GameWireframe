/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Main;

import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Moolt
 */
public class Window extends JFrame {

    public Window(JPanel panel, int width, int height) {
        this.setTitle("Game");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setContentPane(panel);
    }
}
