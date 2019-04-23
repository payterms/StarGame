package ru.payts.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.payts.base.Sprite;
import ru.payts.math.Rect;

public class Logo extends Sprite {

    private Vector2 touchLogo;
    private Vector2 buf;
    private Vector2 pos;
    private Vector2 v;
    private float horizontalLogoWidth = 0.25f;
    private float horizontalLogoHeight = 0.25f;
    TextureRegion region;
    private final float V_SIZE = 0.001f;


    public Logo(TextureRegion region) {
        super(region);
        this.region = region;
        touchLogo = new Vector2();
        buf = new Vector2();
        pos = new Vector2();
        v = new Vector2(V_SIZE, V_SIZE);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/4);
        pos.set(worldBounds.pos);
    }


    public void update(float delta) {
        buf.set(touchLogo.x + 0.5f, touchLogo.y + 0.5f);
        if (buf.sub(pos).len() > V_SIZE) {
            pos.add(v);
        } else {
            pos.x = touchLogo.x + 0.5f ;
            pos.y = touchLogo.y + 0.5f;
        }
    }

    public void draw(SpriteBatch batch) {
        pos.add(v);
        batch.draw(region, pos.x - 0.5f, pos.y - 0.5f, horizontalLogoWidth, horizontalLogoHeight);
        if ((pos.y > 1 - horizontalLogoHeight)|| ((pos.y < 0))||(pos.x < 0)||(pos.x > 1 - horizontalLogoHeight)) {
            v.setZero();
        }
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        touchLogo = touch;
        touch.set(touch.x + 0.5f, touch.y + 0.5f);
        v.set(touch.cpy().sub(pos)).setLength(V_SIZE);
        System.out.println("Logo touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        System.out.println("Logo touchDown pos.x = " + pos.x + " pos.y = " + pos.y);
        return false;
    }


}
