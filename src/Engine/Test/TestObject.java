/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Test;

import Engine.Resources.Sprite;
import Engine.Main.Entity;
import Engine.Resources.ResourceManager;
import java.awt.Graphics2D;

/**
 *
 * @author Moolt
 */
public class TestObject extends Entity {

    Sprite sprite = ResourceManager.getSprite("test");
    private double hSpeed;
    private double vSpeed;

    public TestObject() {
        super();
        this.hSpeed = 4;
        this.vSpeed = 0;
    }

    @Override
    public void Update() {
        this.x += hSpeed;
        if (this.x > 500) {
            this.x = 0;
            this.y += 20;
        }
        if (y > 500) {
            y = 20;
        }
    }

    @Override
    public void Draw(Graphics2D g, double lag) {
        g.drawRect((int) (x + hSpeed * lag), (int) y, 20, 20);
        g.drawImage(this.sprite.getImage(), (int) (x + hSpeed * lag) , (int) y, null);
    }
}
