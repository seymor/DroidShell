package org.droidshell.node;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.node.interfaces.iTransformable;
import org.droidshell.node.interfaces.iUpdatable;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public abstract class Node implements iUpdatable, iRenderable, iTransformable{
	
	public Vector2D coords;
	public Color color;
	
	public Matrix modelMatrix = Matrix.identity();
	
	public boolean isVisible = true;
	public boolean isUpdatable = true;
	
	public int zIndex = 0;
	
	public Node parentNode;
	public NodeList<Node> childrenList;
	
	public Node() {
		coords = new Vector2D(0,0);
		color = Color.WHITE;
	}
	
	public Node(Vector2D coords) {
		this.coords = coords;
		this.color = Color.WHITE;
	}
	
	public Node(Color color) {
		this.coords = new Vector2D(0,0);
		this.color = color;
	}
	
	public Node(Vector2D coords, Color color) {
		this.coords = coords;
		this.color = color;
	}
	
	public Vector2D getPosition() {
		return modelMatrix.multiply(coords);
	}
	
	public void update(boolean childrenUpdate) {
		if (childrenUpdate)
			for(int i=0; i < childrenList.size(); i++)
				childrenList.get(i).update(true);
	}

}
