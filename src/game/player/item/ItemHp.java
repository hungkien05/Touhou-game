package game.player.item;

import game.player.Player;
import game.renderer.Renderer;

public class ItemHp extends Item {
    public ItemHp() {
        renderer = new Renderer("assets/images/items/power-up-red.png", 4, false);
    }

    @Override
    public void powerUp(Player player) {
        player.hp++;
        if (player.hp > 20) {
            player.hp = 20;
        }
    }
}
