package org.droidshell.test.game;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.sprite.AnimatedSprite;

public class Hero {
	
	public AnimatedSprite sprite;
	public Vector2D velocity;
	
	public Hero(AnimatedSprite sprite, Vector2D velocity) {
		this.sprite = sprite;
		this.velocity = velocity.divide(1000.0f);
		//sprite.modifierList.add(new VelocityModifier(velocity));
	}
	
	public void onUpdate(long gameTime) {
		sprite.modelMatrix.loadIdentity();
		sprite.onUpdate(gameTime);
	}
	
	public void onRender(RenderContext renderContext) {
		sprite.onRender(renderContext);
	}

}
