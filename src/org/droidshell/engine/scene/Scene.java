package org.droidshell.engine.scene;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.engine.scene.background.LoopingBackground;
import org.droidshell.engine.scene.background.SpriteBackground;
import org.droidshell.node.Node;
import org.droidshell.node.NodeList;
import org.droidshell.node.interfaces.iRenderable;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class Scene implements iRenderable {

	public LoopingBackground background;
//	public SpriteBackground background;
	public NodeList<Node> nodeList;

	/**
	 * Creates a new Scene with an empty {@link org.droidshell.node.NodeList
	 * NodeList}
	 * 
	 */

	public Scene() {
		this.nodeList = new NodeList<Node>();
	}

	/**
	 * Creates a new Scene with an empty {@link org.droidshell.node.NodeList
	 * NodeList} and a textured
	 * {@link org.droidshell.engine.scene.background.SpriteBackground
	 * SpriteBackground}
	 * 
	 */
	
//	public Scene(SpriteBackground background) {
//		this.background = background;
//		this.nodeList = new NodeList<Node>();
//	}

	public Scene(LoopingBackground background) {
		this.background = background;
		this.nodeList = new NodeList<Node>();
	}

	/**
	 * Updates the scene with a delta time
	 * 
	 * @param gameTime
	 *            the elapsed time since last update
	 * 
	 */

	public void onUpdate(long gameTime) {
		background.onUpdate(gameTime);
		nodeList.onUpdate(gameTime);

		// TODO needs to set visibility
	}

	/**
	 * Renders the scene with an existing
	 * {@link org.droidshell.engine.render.RenderContext RenderContext}
	 * 
	 * @param renderContext
	 *            the context of rendering
	 * 
	 */

	public void onRender(RenderContext renderContext) {
		if (background != null)
			background.onRender(renderContext);
		nodeList.onRender(renderContext);
	}

}
