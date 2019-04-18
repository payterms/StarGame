package ru.payts.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.payts.base.Sprite;
import ru.payts.math.Rect;
import ru.payts.screen.GameScreen;

public class Ship extends Sprite {

    private Game game;

    public Ship(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("main_ship"));
        this.game = game;
        setHeightProportion(0.19f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.02f);
        setLeft(worldBounds.getLeft() + 0.5f);
    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch){

    }


    public boolean keyDown(int keycode) {

        return false;
    }

    public boolean keyUp(int keycode) {

        return false;
    }

}
