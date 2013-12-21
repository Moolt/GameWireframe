/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Moolt
 */
public class EntityList {
    private List<Entity> entities;
    
    public EntityList(){
        entities = new ArrayList<>();
    }
    
    public void Update(){
        for(Entity e: entities){
            e.Update();
        }
    }
    
    public void Draw(Graphics2D g, double lag){
        for(Entity e: entities){
            e.Draw(g, lag);
        }
    }
    
    public void Add(Entity e){
        entities.add(e);
    }
}
