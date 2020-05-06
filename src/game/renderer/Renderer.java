package game.renderer;

import game.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Renderer {
    public BufferedImage image;
    public ArrayList<BufferedImage> images;
    public int currentImageIndex;
    public static int frameDelay;
    public boolean isOnce;

    public Renderer(String url, int delay, boolean isOnce) {
        File source = new File(url);
        frameDelay = delay;
        this.isOnce = isOnce;
        if (source.isFile()) {
            //single image
            image = SpriteUtils.loadImage(url);
        }
        if (source.isDirectory()) {
            // animation
            images = SpriteUtils.loadImages(url);
            currentImageIndex = 0;
        }
//        image = SpriteUtils.loadImage(url);
    }

    int count = 0; //framecount

    public void render(Graphics g, GameObject master) {
        if (image != null) {
            drawImage(g, master, image);
        }
        if (images != null) {
            BufferedImage currentImage = images.get(currentImageIndex);
            drawImage(g, master, currentImage);
            count++;
            if (count > frameDelay) {
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    if (isOnce) {
                        master.deactive();
                    }
                    currentImageIndex = 0;
                }
                count = 0;
            }
        }
    }

    private void drawImage(Graphics g, GameObject master, BufferedImage img) {
        g.drawImage(img, (int) (master.position.x - master.anchor.x * img.getWidth()),
                (int) (master.position.y - master.anchor.y * img.getHeight()), null);
        //draw anchor
        g.setColor(Color.red);
        g.fillOval((int) master.position.x - 2, (int) master.position.y - 2, 5, 5);
        // draw hitBox
        if (master.hitBox != null) {
            g.setColor(Color.green);
            g.drawRect((int) master.hitBox.left(), (int) master.hitBox.top(), master.hitBox.width, master.hitBox.height);
        }
    }
}
