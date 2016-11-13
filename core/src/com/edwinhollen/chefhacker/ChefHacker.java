package com.edwinhollen.chefhacker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.lang.reflect.InvocationTargetException;

public class ChefHacker extends ApplicationAdapter {
	private static SpriteBatch batch;
	private static Viewport viewport;
	private static Stage currentStage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		viewport = new FitViewport(800, 600);

		changeStage(GameStage.class);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentStage.act();
		currentStage.draw();
	}

	public static void changeStage(Class<? extends Stage> newStageClass){
		if(currentStage != null) currentStage.dispose();
		try {
			currentStage = newStageClass.getConstructor(Viewport.class, Batch.class).newInstance(viewport, batch);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		currentStage.dispose();
	}
}
