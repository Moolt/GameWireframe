/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Moolt
 */
public class ResourceManagerTest {
    
    public ResourceManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saveSprite(){
        TilingDTO tiling = new TilingDTO();
        tiling.setTiled(false);
        try {
            File file = new File("C:\\Users\\Moolt\\Pictures\\random\\monogatariseries1201-150x150.jpg");
            BufferedImage[] img = {ImageIO.read(file)};
            Sprite spr = new Sprite();
            spr.setHeight(img[0].getHeight());
            spr.setWidth(img[0].getWidth());
            spr.setName("test");
            spr.setXorigin(0);
            spr.setYorigin(0);
            spr.setImages(img);
            ResourceManager.SaveSprite(tiling, spr);
        } catch (IOException ex) {
            assertTrue(false);
        }
    }
}