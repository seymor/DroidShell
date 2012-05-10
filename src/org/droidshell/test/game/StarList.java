package org.droidshell.test.game;

import java.util.ArrayList;

import org.droidshell.engine.render.RenderContext;

public class StarList {
	
	public ArrayList<Star> stars;
	
	public StarList() {
		stars = new ArrayList<Star>();
	}
	
	public void add(Star star) {
		stars.add(star);
	}
	
	public void remove(Star star) {
		stars.remove(star);
	}
	
	public void onUpdate(long gameTime) {
		for(int i = 0; i < stars.size(); i++)
			stars.get(i).onUpdate(gameTime);
	}
	
	public void onRender(RenderContext renderContext) {
		for(int i = 0; i < stars.size(); i++)
			stars.get(i).onRender(renderContext);
	}
}
