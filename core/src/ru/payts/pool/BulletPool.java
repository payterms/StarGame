package ru.payts.pool;

import ru.payts.base.SpritesPool;
import ru.payts.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
