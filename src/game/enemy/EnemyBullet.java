package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;

public class EnemyBullet extends GameObject {
    public int damage;

    public EnemyBullet() {
//        image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        renderer = new Renderer("assets/images/enemies/bullets", 1, false);
        velocity.set(0, 4);
        hitBox = new BoxCollider(this, 16, 16);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        this.checkPlayer();
        this.deactivateifNeeded();
    }

    private void deactivateifNeeded() {
        if (this.position.y > Settings.GAME_HEIGHT +50) this.deactive();
    }
    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class, this.hitBox);
        if (player != null) {
            this.deactive();
            player.takeDamage(damage);
        }
    }
}
