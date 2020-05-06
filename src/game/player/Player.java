package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.Vector2D;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.Mathx;
import tklibs.SpriteUtils;

import java.awt.*;


public class Player extends GameObject {
//    public ArrayList<PlayerBullet> bullets;
    public int hp;
    public boolean immune; // true: bat tu, false: binh thg

    public  Player() {
//        image = SpriteUtils.loadImage("assets/images/players/straight/1.png");
        renderer = new Renderer("assets/images/players/straight", 4, false);
        position.set(200, 200);
        hitBox = new BoxCollider(this, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        hp = 3;
        immune = false;
//        bullets = new ArrayList<>();
    }

//    @Override
//    public void render(Graphics g) {
//        super.render(g);// goi la ham render o class cha
//        this.bulletsRender(g);
//    }

//    private void bulletsRender( Graphics g) {
//        for (int i = 0; i< bullets.size(); i++) {
//            PlayerBullet bullet = bullets.get(i);
//            bullet.render(g);
//        }
//    }

    public  void takeDamage(int damage) {
        if (immune == false){
            hp -= damage;
            immune = true;
            if (hp <= 0) {
                hp = 0;
                this.deactive();
            }
        }
    }

    int countRender = 0;
    @Override
    public void render(Graphics g) {
        if (immune) {
            countRender++;
            if (countRender % 2 == 0) {
                super.render(g);
            }
        } else super.render(g);
    }

    @Override
    public void run() {
        super.run();
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
//      this.bulletsRun();

    }
    int countImmune = 0;
    private void checkImmune() {
        if (immune) {
            countImmune++;
            if (countImmune > 120) {
                immune = false;
            }
        } else {
            countImmune = 0;
        }
    }

    private void move() {
        double vx=0, vy=0;
        double speed = 7;
        if (KeyEventPress.isUpPress) /*position.add(0,-5);*/ vy-= speed;
        if (KeyEventPress.isDownPress) vy += speed;
        if (KeyEventPress.isLeftPress) vx -= speed;
        if (KeyEventPress.isRightPress) vx +=speed;
        velocity.set(vx, vy);
        velocity.setLength(speed); // di cheo se ko nhanh hon di ngang, di len
    }

    private void limitPosition() {
        position.setX(Mathx.clamp(position.x, 0, Settings.BACKGROUND_WIDTH- Settings.PLAYER_WIDTH/2 ));
        position.setY(Mathx.clamp(position.y, 0, 600-Settings.PLAYER_HEIGHT));
    }

//    private void bulletsRun() {
//        for (int i = 0; i < bullets.size(); i++) {
//            PlayerBullet bullet = bullets.get(i);
//            bullet.run();
//        }
//    }
    int frameCount= 0;
    private void fire() {
        frameCount++;
        if (KeyEventPress.isFirePress && frameCount >15) {
////            Vector2D bulletPosition = position.clone();
//            PlayerBullet bullet = new PlayerBullet();
//            bullet.position.set(position.x,position.y);
//            bullet.velocity.setAngle(Math.toRadians(-90));
//            bullets.add(bullet);
//
//            PlayerBullet bullet1 = new PlayerBullet();
//            bullet.position.set(position.x+20,position.y);
//            bullet.velocity.setAngle(Math.toRadians(-45));
//            bullets.add(bullet1);
//
//            PlayerBullet bullet2 = new PlayerBullet();
//            bullet.position.set(position.x-20,position.y);
//            bullet.velocity.setAngle(Math.toRadians(-135));
//            bullets.add(bullet2);
////            bulletPositions.add(bulletPosition);

            int numberBullets = 5;
            double startAngle = Math.toRadians(-45);
            double endAngle = Math.toRadians((-135));
            double stepAngle = Math.abs(startAngle-endAngle) / (numberBullets-1);
            double startX = position.x +20;
            double endX = position.x-20;
            double stepX = Math.abs(endX - startX) / (numberBullets-1);

            for (int i = 0; i < numberBullets; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(startX - stepX*i, position.y);
                bullet.velocity.setAngle(startAngle - stepAngle*i);
//                bullets.add(bullet);
            }
            frameCount = 0;
        }
    }
}

