package ru.payts.pool;

import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.payts.base.SpritesPool;
import ru.payts.math.Rect;
import ru.payts.sprite.Enemy;
import ru.payts.sprite.Explosion;
import ru.payts.sprite.MainShip;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureAtlas atlas;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas, Sound explosionSound) {
        this.atlas = atlas;
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(atlas, explosionSound);
    }
}
