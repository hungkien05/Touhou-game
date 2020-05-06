package game;

import game.enemy.EnemySummoner;
import game.player.Player;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    Player player;
    Background background;
    double fps;

    public GamePanel() {
        background = new Background();
        player = new Player();
        new EnemySummoner();
        //new ItemSummoner();
    }

    // paint auto called by program
    @Override
    public void paint(Graphics g) {
        //g.drawRect(100, 100, 200, 200);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
//        background.render(g);
//        player.render(g);
        GameObject.renderAll(g);
        this.drawMenu(g);
    }

    static Font font = new Font("Verdana", Font.BOLD, 32);
    static BufferedImage heartImage = SpriteUtils.loadImage("assets/images/heart.png");
    static BufferedImage enemyImage = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");

    public void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(Settings.BACKGROUND_WIDTH, 0, Settings.GAME_WIDTH - Settings.BACKGROUND_WIDTH, Settings.GAME_HEIGHT);
        g.setColor(Color.RED);
        g.drawString(fps + "", 750, 50);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawImage(enemyImage, 550, 175, null);
        g.drawString(Settings.score + "", 600, 200);

        g.setColor(player.hp >= 3 ? Color.GREEN : Color.RED);
        g.drawImage(heartImage, 550, 225, null);
        g.drawString(player.hp + "", 600, 250);
    }

    public void runAll() {
//        player.run();
//        background.run();
        System.out.println(GameObject.gameObjects.size());
        GameObject.runAll();
    }

    public void gameLoop() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime >= 1000 / 60) {
                fps = 1000 / (currentTime - lastTime);
                this.repaint(); // ~call paint()
                this.runAll();
                //System.out.println(currentTime - lastTime + " " + backgroundY);
                lastTime = currentTime;
            }
        }
    }
}
