package org.droidshell.scene.background;

import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.node.sprite.Sprite;
import org.droidshell.render.RenderContext;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class SpriteBackground implements iRenderable {

	public Sprite sprite;

	public SpriteBackground(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(RenderContext renderContext) {
		sprite.render(renderContext);
	}

}
