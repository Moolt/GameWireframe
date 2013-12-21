/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Resources;

import Engine.Entity.Interfaces.IUpdatable;
import java.awt.image.BufferedImage;

/**
 *
 * @author Moolt
 */
public class Sprite implements IUpdatable, Resource {

    private BufferedImage[] images;
    private String name;
    private double index;
    private double speed;
    private int width;
    private int height;
    private int xorigin;
    private int yorigin;

    public Sprite() {
        this.index = 0D;
        this.speed = 1D;
    }

    @Override
    public void Update() {
        this.index += this.speed;
        if (Math.round(this.index) > this.images.length) {
            this.index = 0D;
        }
    }

    public BufferedImage getImage() {
        return this.images[((int)Math.round(this.index))];
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getXorigin() {
        return xorigin;
    }

    public void setXorigin(int xorigin) {
        this.xorigin = xorigin;
    }

    public int getYorigin() {
        return yorigin;
    }

    public void setYorigin(int yorigin) {
        this.yorigin = yorigin;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getSubimageCount(){
        return this.images.length;
    }
}
