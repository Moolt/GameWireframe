/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Resources;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Moolt
 */
public class ResourceManager {

    private static Map<String, Sprite> sprites = new HashMap<>();
    private final static Logger LOGGER = Logger.getLogger(ResourceManager.class.getName());

    public static Sprite getSprite(String name) {
        if (sprites.containsKey(name)) {
            //Return existing sprite
            return sprites.get(name);
        } else {
            try {
                //If sprite does not exist yet, load it
                Sprite sprite = LoadSprite(name);
                sprites.put(name, sprite);
                return sprite;
            } catch (IOException ex) {
                //Loading sprite failed
                LOGGER.log(Level.SEVERE, "Error loading sprite: {0}", name);
                return null;
            }
        }
    }

    private static Sprite LoadSprite(String name) throws IOException {
        String path = "./tex/" + name + ".tex";
        File file = new File(path);
        Sprite sprite = new Sprite();
        //The tiling information (sprite does not have to be tiled)
        TilingDTO tiling = new TilingDTO();
        //Image to be loaded
        BufferedImage img;
        //The image-array of the loaded sprite
        BufferedImage[] imageList;
        //Amount of bytes in image
        int fileLength;
        //Stores the binary data of the image
        byte[] imgBytes;

        try (ObjectInputStream io = new ObjectInputStream(new FileInputStream(file))) {
            int filetype = io.readInt();
            //Read Meta-Data from file
            sprite.setName(name);
            sprite.setWidth(io.readInt());
            sprite.setHeight(io.readInt());
            sprite.setXorigin(io.readInt());
            sprite.setYorigin(io.readInt());

            if (filetype == 0) {
                io.skipBytes(24);
                //Sprite without animation does not contain tiling
                tiling.setTiled(false);
            } else if (filetype == 2) {
                //Enable tiling for animations frames
                tiling.setTiled(true);
                //Read Spritesheet
                tiling.setHeight(io.readInt());
                tiling.setWidth(io.readInt());
                tiling.setOffsetX(io.readInt());
                tiling.setOffsetY(io.readInt());
                tiling.sethSeparation(io.readInt());
                tiling.setvSeparation(io.readInt());
            }
            //Read Image(s)
            fileLength = io.readInt();
            imgBytes = new byte[fileLength];
            io.readFully(imgBytes, 0, fileLength);
            img = GetImageFromBytes(imgBytes);
            if (tiling.isTiled()) {
                imageList = AnimationFromBitmap(tiling, img);
            } else {
                imageList = new BufferedImage[1];
                imageList[0] = img;
            }
            sprite.setImages(imageList);
        }
        return sprite;
    }

    private static BufferedImage GetImageFromBytes(byte[] bytes) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
       
        File sf = new File("./tex/"+bytes.length+".png");
        FileOutputStream fo = new FileOutputStream(sf);
        fo.write(bytes);
        fo.close();
        
        BufferedImage img = ImageIO.read(is);
        return img;
    }

    private static BufferedImage[] AnimationFromBitmap(TilingDTO tiling, BufferedImage img) {
        int x = img.getWidth() / (tiling.getWidth() + tiling.getOffsetX() + tiling.gethSeparation());
        int y = img.getHeight() / (tiling.getHeight() + tiling.getOffsetY() + tiling.getvSeparation());
        BufferedImage[] animation = new BufferedImage[x * y];
        int index = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                animation[index++] = img.getSubimage(
                        tiling.getOffsetX() + i * (tiling.gethSeparation() + tiling.getWidth()),
                        tiling.getOffsetY() + j * (tiling.getvSeparation() + tiling.getHeight()),
                        tiling.getOffsetX() + i * (tiling.gethSeparation() + tiling.getWidth()) + tiling.getWidth(),
                        tiling.getOffsetY() + j * (tiling.getvSeparation() + tiling.getHeight()) + tiling.getHeight());
            }
        }
        return animation;
    }

    public static void SaveSprite(TilingDTO tiling, Sprite sprite) throws IOException {
        String path = "./tex/" + sprite.getName() + ".tex";
        File file = new File(path);
        file.createNewFile();

        ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream(file));
        BufferedImage[] images = sprite.getImages();

        //If there are multiple subimages, the sprite contains an animation
        int filetype = (tiling.isTiled()) ? 2 : 0;
        io.writeInt(filetype);
        io.writeInt(sprite.getWidth());
        io.writeInt(sprite.getHeight());
        io.writeInt(sprite.getXorigin());
        io.writeInt(sprite.getYorigin());

        if (filetype == 0) {
            io.write(new byte[24]);
        } else {
            io.writeInt(tiling.getWidth());
            io.writeInt(tiling.getHeight());
            io.writeInt(tiling.getOffsetX());
            io.writeInt(tiling.getOffsetY());
            io.writeInt(tiling.gethSeparation());
            io.writeInt(tiling.getvSeparation());
        }

        for (BufferedImage bufferedImage : images) {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteStream);
            byte[] imageBytes = byteStream.toByteArray();
            io.writeInt(imageBytes.length);
            io.write(imageBytes);

            File sf = new File("./tex/bild.png");
            FileOutputStream fo = new FileOutputStream(sf);
            fo.write(imageBytes);
            fo.close();
        }
        io.flush();
        io.close();
    }

    public boolean SpriteExists(String name) {
        return sprites.containsKey(name);
    }
}
