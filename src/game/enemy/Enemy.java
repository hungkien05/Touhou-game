package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class Enemy extends GameObject {
    public Enemy() {
//        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
        renderer = new Renderer("assets/images/enemies/level0/pink", 4, false);
        position.set(-50, -50);
        velocity.set(0,3);
        velocity.setAngle(Math.toRadians(20));
        hitBox = new BoxCollider(this, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
    }

    @Override
    public void run() {
        super.run();
        this.checkChangeDirection();
        this.fire();
        this.deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if (this.position.y > Settings.GAME_HEIGHT + 50) {
            this.deactive();
        }
    }

    public void checkChangeDirection() {
        if(this.outOfBoundRight() && this.onGoingRight()) {
            velocity.x = - velocity.x;
        }
        if (position.x <= 0 && velocity.x < 0) {
            velocity.x = - velocity.x;
        }
    }

    public boolean outOfBoundRight() {
        return position.x >= Settings.BACKGROUND_WIDTH-Settings.ENEMY_WIDTH;
    }

    public boolean onGoingRight() {
        return velocity.x > 0;
    }

    int frameCount = 0;
    private void fire() {
        frameCount++;
        if (frameCount > 20) {
//            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(position.x, position.y);
            frameCount = 0;
        }
    }

    @Override
    public void reset() {
        super.reset();
        position.set(-50, -50);
        velocity.set(0,3);
        velocity.setAngle(Math.toRadians(20));
    }

}
