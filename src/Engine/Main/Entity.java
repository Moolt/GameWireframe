package Engine.Main;

import java.awt.Graphics2D;

/**
 *
 * @author Moolt
 */
public abstract class Entity {

    protected int depth;
    protected String name;
    protected double direction;
    protected double x, y;

    public Entity() {   
        this.x = 0D;
        this.y = 0D;
        this.depth = 0;
        this.name = null;
        this.direction = 0D;
    }

    public abstract void Update();

    public abstract void Draw(Graphics2D g, double lag);
}
