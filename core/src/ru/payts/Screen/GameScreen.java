package ru.payts.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.BaseScreen;
import ru.payts.math.Rect;
import ru.payts.pool.BulletPool;
import ru.payts.pool.EnemyPool;
import ru.payts.sprite.Background;
import ru.payts.sprite.ButtonExit;
import ru.payts.sprite.ButtonPlay;
import ru.payts.sprite.Enemy;
import ru.payts.sprite.EnemyGenerator;
import ru.payts.sprite.MainShip;
import ru.payts.sprite.Star;

public class GameScreen extends BaseScreen {
    private Game game;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star starList[];

    private int frags = 0;

    private MainShip mainShip;

    private BulletPool bulletPool;

    private EnemyPool enemyPool;

    private EnemyGenerator enemyGenerator;


    Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
    Sound  soundBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));



    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        starList = new Star[64];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();

        enemyPool = new EnemyPool(bulletPool, worldBounds, mainShip);

        mainShip = new MainShip(atlas, bulletPool);

        enemyGenerator = new EnemyGenerator(enemyPool, worldBounds, atlas, soundBullet);

        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyedSprites();
        draw();
    }

    private void update(float delta) {
        for (Star star : starList) {
            star.update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
        enemyGenerator.generateEnemies(delta, frags);
    }

    private void freeAllDestroyedSprites() {
        enemyPool.freeAllDestroyedActiveSprites();
        bulletPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : starList) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        music.stop();
        music.dispose();
        enemyPool.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return false;
    }
}
