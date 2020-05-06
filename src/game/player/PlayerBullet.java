package game.player;

import game.GameObject;
import game.Settings;
import game.enemy.Enemy;
import game.enemy.EnemyExplosion;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class PlayerBullet extends GameObject {



    public PlayerBullet() {
//        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
//        velocity = new Vector2D(0, -4);
        renderer = new Renderer("assets/images/player-bullets/a", 1, false);
        velocity.set(0, -4);
        hitBox = new BoxCollider(this, Settings.PLAYER_BULLET_WIDTH, Settings.PLAYER_BULLET_HEIGHT);
    }

    @Override
    public void run() {
        super.run();
        this.checkEnemy();
        this.deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if (position.y < -50) this.deactive();
    }

    private void checkEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this.hitBox);
        if (enemy != null) {
            this.deactive();
            enemy.deactive();
            if (!enemy.active) {
                Settings.score++; //tinh diem neu giet dc enemy
                EnemyExplosion enemyExplosion = new EnemyExplosion();
                enemyExplosion.position.set(this.position.x, this.position.y);
            }
        }
    }
}
