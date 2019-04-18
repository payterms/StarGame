package ru.payts.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.BaseScreen;
import ru.payts.math.Rect;
import ru.payts.sprite.Background;
import ru.payts.sprite.ButtonExit;
import ru.payts.sprite.ButtonPlay;
import ru.payts.sprite.Ship;
import ru.payts.sprite.Star;

public class GameScreen extends BaseScreen {
    private Game game;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas mainAtlas;
    private Star starList[];
    private Ship ourShip;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        mainAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        starList = new Star[256];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlas);
        }
        ourShip = new Ship(mainAtlas,game );

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
        ourShip.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        for (Star star : starList) {
            star.update(delta);
        }
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : starList) {
            star.draw(batch);
        }
        ourShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        ourShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ourShip.keyUp(keycode);
        return false;
    }
}
