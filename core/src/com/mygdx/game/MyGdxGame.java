package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import world.GameMap;
import world.TileType;
import world.TiledGameMap;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Bullet bullet;

	OrthographicCamera camera;
	GameMap gameMap;
	OrthogonalTiledMapRenderer mapRenderer;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		gameMap = new TiledGameMap();
		//mapRenderer = new OrthogonalTiledMapRenderer(gameMap);

		bullet = new Bullet(0,0);

		batch = new SpriteBatch();
	}

	public void update(){
		bullet.update(Gdx.input);
		camera.translate(1,0);
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(1, 0, 0, 1);

		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(camera, batch);

		batch.begin();
		bullet.render(batch);
		batch.end();
		/*if (Gdx.input.isTouched()){
			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}*/


	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
