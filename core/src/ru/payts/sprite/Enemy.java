package ru.payts.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.Sprite;
import ru.payts.math.Rect;
import ru.payts.pool.BulletPool;

public class Enemy extends Sprite {

    private Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected final Vector2 v = new Vector2();

    private Vector2 bulletV = new Vector2(0f, 0.5f);
    protected Sound bulletSound;

    protected float bulletHeight;
    protected int bulletDamage;

    protected int hp;

    protected float reloadInterval;
    protected float reloadTimer;

    private enum State { DESCENT, FIGHT }

    private MainShip mainShip; // ссылка на игровой корабль
    private State state; // состояние корабля

    private Vector2 descentV = new Vector2(0, -0.15f);
    private Vector2 v0 = new Vector2(); // начальная скорость

    public Enemy(BulletPool bulletPool,  Rect worldBounds, MainShip mainShip) {
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        this.mainShip = mainShip;
        this.v.set(v0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        pos.mulAdd(v, deltaTime);
        switch (state) {
            case DESCENT:
                if (getTop() <= worldBounds.getTop()) {
                    v.set(v0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += deltaTime;
                if (reloadTimer >= reloadInterval) {
                    reloadTimer = 0f;
                    shoot();
                }
                if (getBottom() < worldBounds.getBottom()) {
                    //mainShip.damage(bulletDamage);
                    destroy();
                }
                break;
        }
    }

    public void set(
            TextureRegion[] regions, // текстура корабля
            Vector2 v0, // начальная скорость
            TextureRegion bulletRegion, // текстура пули
            float bulletHeight, // высота пули
            float bulletVY, // скорость пули
            int bulletDamage, // урон пули
            float reloadInterval, // скорость перезарядки
            Sound soundShoot, // звук выстрела
            float height, // размер корабля
            int hp // жизни
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.bulletSound = soundShoot;
        this.hp = hp;
        setHeightProportion(height);
        reloadTimer = reloadInterval;
        v.set(descentV);
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        if (bulletSound.play() == -1) {
            throw new RuntimeException("Can't play sound");
        }
    }


}