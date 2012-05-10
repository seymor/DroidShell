package org.droidshell.test.game;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.modifier.RotationModifier;
import org.droidshell.node.sprite.Sprite;

import org.droidshell.R;

public class Star {

	public Sprite sprite;
	public Vector2D position;
	
	public Star(Vector2D position) {
		sprite = new Sprite(0.1f,0.1f,R.drawable.star);
		this.position = position;
		sprite.modifierList.add(new RotationModifier(180));
	}
	
	public void onUpdate(long gameTime) {
		sprite.modelMatrix.loadIdentity();
		sprite.onTranslate(position.x, position.y);
		sprite.onUpdate(gameTime);
	}
	
	public void onRender(RenderContext renderContext) {
		sprite.onRender(renderContext);
	}
	
}
