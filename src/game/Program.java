package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static double[] equation2(double a, double b, double c) {
        double[] result = new double[2];
        if (a == 0) {
            if (b == 0) return null;
            else {
                result[0] = -c / b;
                result[1] = -c / b;
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) return null;
            else {
                result[0] = (-b - Math.sqrt(delta)) / (2 * a);
                result[1] = (-b + Math.sqrt(delta)) / (2 * a);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        /*String message = "Hello world";
        double[] result = equation2(1, -4, 4);
        if (result == null) {
            System.out.println("Can not resolve the equation!");
        } else {
            System.out.println("x1 = " + result[0] + "; x2 = "+ result[1]);
        }
        game.Chicken c1 = new game.Chicken();
        c1.print();

        game.FightingCock fc1 = new game.FightingCock(2.1, "green", 2, 10000);
        System.out.println(fc1.color);
        System.out.println(fc1.damage);
        fc1.eat()*/

      /*   ArrayList<game.Vector2D> listVectors = new ArrayList<>();
        //listVectors empty
        listVectors.add(new game.Vector2D(1,1));
        listVectors.add(new game.Vector2D(4,1));
        listVectors.add(new game.Vector2D(-10,2));
        listVectors.add(new game.Vector2D(3,3));
        double maxSum = 0;
        int maxIndex = -1;
        for (int i=0; i<4; i++){
            game.Vector2D vector = listVectors.get(i);
            System.out.println(vector.x + " " + vector.y);
            if ( vector.x + vector.y > maxSum) {
                maxSum= vector.x+vector.y;
                maxIndex = i;
            }
        }
        game.Vector2D maxVectors = listVectors.get(maxIndex); */

       System.out.println(System.currentTimeMillis());
        JFrame window = new JFrame();
        window.setTitle("Game Touhou");
        window.setSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT));
        window.add(panel);
        window.pack();
        // bat su kien phim
        KeyAdapter keyHandler = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W )
                    KeyEventPress.isUpPress = true;
                if (e.getKeyCode() == KeyEvent.VK_S)
                    KeyEventPress.isDownPress = true;
                if (e.getKeyCode() == KeyEvent.VK_A)
                    KeyEventPress.isLeftPress = true;
                if (e.getKeyCode() == KeyEvent.VK_D)
                    KeyEventPress.isRightPress = true;
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    KeyEventPress.isFirePress = true;
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W )
                    KeyEventPress.isUpPress = false;
                if (e.getKeyCode() == KeyEvent.VK_S)
                    KeyEventPress.isDownPress = false;
                if (e.getKeyCode() == KeyEvent.VK_A)
                    KeyEventPress.isLeftPress = false;
                if (e.getKeyCode() == KeyEvent.VK_D)
                    KeyEventPress.isRightPress = false;
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    KeyEventPress.isFirePress = false;
            }
        } ;

        window.addKeyListener(keyHandler);
        window.setVisible(true);
        // start game
        panel.gameLoop();

    }
}
