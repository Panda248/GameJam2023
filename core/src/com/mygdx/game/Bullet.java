package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Bullet {
    final float ROTATE_SPEED = 10;
    final float MOVE_SPEED = 10;

    Sprite img;
    Vector2 moveDirection;
    Vector2 position;
    float rotation = 0;
    float deltaRotate = 0;

    public static int width;
    public static int height;

    public Bullet(float x, float y) {
        position = new Vector2(x, y);
        init();
    }

    public void init () {
        img = new Sprite(new Texture("bullet.png"));
        width = (int) img.getWidth();
        height = (int) img.getHeight();
    }

    public void render (SpriteBatch batch) {
        img.setCenterX(position.x);
        img.setCenterY(position.y);
        //System.out.println("Image: " + position);

        img.draw(batch);
    }

    public void update(Input input)	{
        Vector2 mousePosition = new Vector2(input.getX(), Gdx.graphics.getHeight() - input.getY());
        //System.out.println("Mouse: " + mousePosition);

        mousePosition = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        float radToMouse = position.angleRad(mousePosition);
        deltaRotate = Math.min(ROTATE_SPEED, Math.abs(radToMouse-rotation));
        deltaRotate *= (radToMouse-rotation)/Math.abs(radToMouse-rotation);//there has to be a better way :(
        rotation += deltaRotate;
        //img.rotate(deltaRotate);
        moveDirection = mousePosition.sub(position);
        moveDirection.clamp(0, MOVE_SPEED);

        position.add(moveDirection);
    }
}
