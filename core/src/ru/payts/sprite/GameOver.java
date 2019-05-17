package ru.payts.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.Ship;
import ru.payts.base.Sprite;
import ru.payts.math.Rect;
import ru.payts.pool.BulletPool;

public class GameOver extends Sprite {

    private static final float HEIGHT = 0.07f;
    private static final float BOTTOM_MARGIN = 0.009f;

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM_MARGIN);
    }
}
