package ru.payts.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.BaseScreen;
import ru.payts.math.Rect;
import ru.payts.sprite.Background;
import ru.payts.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Texture img;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        bg = new Texture("bg.png");
        background = new Background(new TextureRegion(bg));
        img = new Texture("badlogic.jpg");
        logo = new Logo(new TextureRegion(img));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        logo.update(delta);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        logo.touchDown(touch,pointer);
        return false;
    }
}
