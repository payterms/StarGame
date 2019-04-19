package ru.payts.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.payts.base.SpritesPool;
import ru.payts.math.Rect;
import ru.payts.sprite.Enemy;
import ru.payts.sprite.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private final BulletPool bulletPool;
     private final Rect worldBounds;
    private final MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, worldBounds, mainShip);
    }


    protected void debugLog() {
        System.out.println("EnemyPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
