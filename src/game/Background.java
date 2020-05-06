package game;

import game.renderer.Renderer;
import tklibs.SpriteUtils;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject {

    public Background() {
//        image = SpriteUtils.loadImage("assets/images/background/0.png");
        renderer = new Renderer("assets/images/background/0.png",8, false);
        position.set(0, 600-Settings.BACKGROUND_HEIGHT);
        velocity.set(0, 1);
        anchor.set(0,0);
    }
    @Override
    public void run() {
        //position.add(0, 2);
        super.run(); // ~ position.add(velocity.x, velocity.y), chay ham run o class cha
        if (position.y >= 0) position.y = 0;
    }
}
