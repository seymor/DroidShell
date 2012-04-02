package org.droidshell.scene;

import org.droidshell.node.Node;
import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.render.RenderContext;
import org.droidshell.scene.background.SpriteBackground;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class Scene extends Node implements iRenderable {

	public SpriteBackground spriteBackground;

	public Scene(SpriteBackground spriteBackground) {
		this.spriteBackground = spriteBackground;
	}

	public void render(RenderContext renderContext) {
		spriteBackground.sprite.render(renderContext);
	}

}
