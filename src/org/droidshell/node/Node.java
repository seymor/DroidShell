package org.droidshell.node;

import java.util.ArrayList;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.node.interfaces.iTransformable;
import org.droidshell.node.interfaces.iUpdatable;
import org.droidshell.node.modifier.iNodeModifier;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public abstract class Node implements iUpdatable, iRenderable, iTransformable {

	public Vector2D coords;
	public Color color;

	public Matrix modelMatrix = Matrix.identity();

	public boolean isVisible = true;
	public boolean isUpdatable = true;

	public int zIndex = 0;

	public Node parentNode;
	public NodeList<Node> childrenList;

	public ArrayList<iNodeModifier> modifierList;

	public Node() {
		coords = new Vector2D(0, 0);
		color = Color.WHITE;
	}

	public Node(Vector2D coords) {
		this.coords = coords;
		this.color = Color.WHITE;
	}

	public Node(Color color) {
		this.coords = new Vector2D(0, 0);
		this.color = color;
	}

	public Node(Vector2D coords, Color color) {
		this.coords = coords;
		this.color = color;
	}

	public Vector2D getPosition() {
		return modelMatrix.multiply(coords);
	}

	public void translate(float tX, float tY) {
		this.modelMatrix.translate(tX, tY);
	}

	public void scale(float sX, float sY) {
		this.modelMatrix.scale(sX, sY);
	}

	public void rotate(float angle) {
		this.modelMatrix.rotate(angle);
	}

	public void initModifiers() {
		for (int i = 0; i < modifierList.size(); i++)
			modifierList.get(i).setModifier(this);
	}

	public void update(long gameTime) {
		// TODO
	}

	public void update(long gameTime, boolean childrenUpdate) {

		if (childrenUpdate)
			for (int i = 0; i < childrenList.size(); i++)
				childrenList.get(i).update(gameTime);
	}

}
