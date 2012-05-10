package org.droidshell.test.game;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.engine.scene.Scene;

public class SuperScene extends Scene {
	
	public Hero hero;
	public StarList starList;
	public Smoke smoke;
	
	public SuperScene() {
		starList = new StarList();
	}
	
	public void onUpdate(long gameTime) {
		super.onUpdate(gameTime);
		hero.onUpdate(gameTime);
		starList.onUpdate(gameTime);
		smoke.onUpdate(gameTime);
	}
	
	public void onRender(RenderContext renderContext) {
		super.onRender(renderContext);
		hero.onRender(renderContext);
		starList.onRender(renderContext);
		smoke.onRender(renderContext);
	}

}
