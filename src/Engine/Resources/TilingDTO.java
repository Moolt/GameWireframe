/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Resources;

/**
 *
 * @author Moolt
 */
public class TilingDTO {

    private boolean tiled;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;
    private int hSeparation;
    private int vSeparation;

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

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int gethSeparation() {
        return hSeparation;
    }

    public void sethSeparation(int hSeparation) {
        this.hSeparation = hSeparation;
    }

    public int getvSeparation() {
        return vSeparation;
    }

    public void setvSeparation(int vSeparation) {
        this.vSeparation = vSeparation;
    }

    public boolean isTiled() {
        return tiled;
    }

    public void setTiled(boolean tiled) {
        this.tiled = tiled;
    }       
}
