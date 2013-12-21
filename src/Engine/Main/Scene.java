package Engine.Main;

import Engine.Entity.Interfaces.IDrawable;
import Engine.Entity.Interfaces.IUpdatable;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Moolt
 */
public class Scene implements IDrawable, IUpdatable{
    //Stores all GameObjects of the current Level
    private EntityList entities;
    private Camera camera;
    private Rectangle size;
    
    public Scene(){
        this.entities = new EntityList();
        this.camera = new Camera();
        this.size = new Rectangle();        
    }

    public void AddEntity(Entity e){
        this.entities.Add(e);
    }
    
    @Override
    public void Draw(Graphics2D g, double lag){
        entities.Draw(g, lag);
    }
    
    @Override
    public void Update(){
        entities.Update();
        camera.Update();
    }
}
