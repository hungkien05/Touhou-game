package game;


import game.physics.BoxCollider;

import game.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    //quan li doi tuong(static)
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    public static void runAll() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if (object.active) {
                object.run();
            }
        }
    }

    public static void renderAll(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if (object.active) {
                object.render(g);
            }
        }
    }

    public static void clearAll() {
        gameObjects.clear();
    }

    public static <E> E findIntersects(Class<E> cls, BoxCollider hitBox) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if (object.active
                    && object.hitBox != null
                    && object.hitBox.intersects(hitBox)
                    && cls.isAssignableFrom(object.getClass())) {
                return (E) object;
            }
        }
        return null;
    }

    //recycle : dung lai GameObject
    //E ~ Player, PLayerBullet, Enemy, ...
    public static <E extends GameObject> E recycle(Class<E> cls) {
        // 1. tim 1 gameObject dang ko active
        // neu co -> reset gameObject >> return
        // 2. neu ko co >> tao moi >> return
        E gameObject = findInactive(cls);
        if (gameObject != null) {
            gameObject.reset();
            return (E) gameObject;
        }
        try {
            // E ~Player
            gameObject = cls.getConstructor().newInstance();
            return gameObject;
        } catch (Exception ex) {
            return null;
        }
    }

    public static <E extends GameObject> E findInactive(Class<E> cls) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if (!object.active
                    && cls.isAssignableFrom(object.getClass())) return (E) object;
        }
        return null;
    }

    //dinh nghia doi tuong
    public Renderer renderer;
    public Vector2D position;
    public Vector2D velocity;
    public boolean active;
    public BoxCollider hitBox;
    public Vector2D anchor;

    public GameObject() {
        gameObjects.add(this);
        //System.out.println(gameObjects.size());
        position = new Vector2D(); // (0,0);
        velocity = new Vector2D();
        active = true;
        anchor = new Vector2D(0.5, 0.5);
    }

    public void render(Graphics g) {
        if (renderer != null) {
            renderer.render(g, this);
        }
    }

    public void run() {
        position.add(velocity.x, velocity.y);
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }

//    public static void main(String[] args) {
//        Player player = find(Player.class);
//        Enemy enemy = find(Enemy.class);
//        System.out.println(player);
//        System.out.println(enemy);
//    }
//
//    public static <E> E find(Class<E> cls) {
//        try {
//            return cls.getConstructor().newInstance();
//        } catch (Exception ex){
//            return null;
//        }
//    }
}
