package ru.payts.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.ScaledTouchUpButton;
import ru.payts.screen.GameScreen;


import ru.payts.base.Ship;
import ru.payts.base.Sprite;
import ru.payts.math.Rect;
import ru.payts.pool.BulletPool;

public class NewGame extends ScaledTouchUpButton {

    private static final float HEIGHT = 0.05f;
    private static final float TOP = -0.012f;


    private GameScreen screen;

    public NewGame(TextureAtlas atlas, GameScreen screen) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(HEIGHT);
        setTop(TOP);
        this.screen = screen;
    }

    @Override
    protected void action() {
        screen.reset();
    }
}
