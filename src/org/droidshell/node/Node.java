package org.droidshell.node;

import java.util.ArrayList;

import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.node.interfaces.iTransformable;
import org.droidshell.node.interfaces.iUpdatable;
import org.droidshell.node.modifier.iNodeModifier;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public abstract class Node implements iUpdatable, iRenderable, iTransformable,
		Cloneable {

	private static final String TAG = Node.class.getName();

	private Vector2D worldPosition;

	public Vector2D coords;
	public Matrix modelMatrix = Matrix.identityMatrix();
	public boolean isVisible;
	public boolean isUpdatable;
	public int zIndex;
	public Node parentNode;
	public NodeList<Node> childrenList;

	public ArrayList<iNodeModifier> modifierList;

	public Node() {
		worldPosition = new Vector2D();
		coords = new Vector2D();
		childrenList = new NodeList<Node>();
		modifierList = new ArrayList<iNodeModifier>();

		isVisible = true;
		isUpdatable = true;
		zIndex = 0;
	}

	public Node(Vector2D coords) {
		worldPosition = new Vector2D();
		this.coords = coords;
		childrenList = new NodeList<Node>();
		modifierList = new ArrayList<iNodeModifier>();

		isVisible = true;
		isUpdatable = true;
		zIndex = 0;
	}

	public void resetPosition() {
		modelMatrix.loadIdentity();
	}

	public Vector2D getPosition() {
		return Matrix.multiply(worldPosition, modelMatrix, coords);
	}

	public void onTranslate(float tX, float tY) {
		modelMatrix.translate(tX, tY);
	}

	public void onScale(float sX, float sY) {
		modelMatrix.scale(sX, sY);
	}

	public void onRotate(float angle) {
		modelMatrix.rotate(angle);
	}

	public void onUpdate(long gameTime) {
		for (int i = 0; i < modifierList.size(); i++)
			modifierList.get(i).onUpdate(this, gameTime);
	}

	public void onUpdate(long gameTime, boolean childrenUpdate) {
		for (int i = 0; i < modifierList.size(); i++)
			modifierList.get(i).onUpdate(this, gameTime);

		if (childrenUpdate)
			for (int i = 0; i < childrenList.size(); i++)
				childrenList.get(i).onUpdate(gameTime);
	}

	@Override
	public Node clone() {
		try {
			Node node = (Node) super.clone();
			node.modelMatrix = Matrix.identityMatrix();
			return node;
		} catch (CloneNotSupportedException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

}
