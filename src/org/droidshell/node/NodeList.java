package org.droidshell.node;

import java.util.ArrayList;

import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.node.interfaces.iTransformable;
import org.droidshell.node.interfaces.iUpdatable;
import org.droidshell.render.RenderContext;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class NodeList<E extends iUpdatable & iRenderable & iTransformable>
		extends ArrayList<E> {

	private static final long serialVersionUID = 1L;
	private static final String TAG = NodeList.class.getName();

	public NodeList() {
	}

	public NodeList(int size) {
		super(size);
	}

	public void add(int index, E object) {
		E old = null;

		if (index < this.size() && index >= 0) {
			old = this.get(index);
			this.set(index, object);
			this.add(old);
		} else {
			this.add(object);
		}
	}

	public NodeList<E> push(E object) {
		this.add(object);
		return this;
	}

	public E remove(int index) {
		E removable = null;
		if (index < this.size() && index >= 0) {
			removable = this.get(index);
			this.set(index, this.get(this.size() - 1));
			return removable;
		}

		Log.e(TAG, "Node not found on index: " + index);
		return null;
	}

	public NodeList<Node> sort() {
		NodeList<Node> list = new NodeList<Node>();

		// TODO: algorithm

		return list;
	}

	public void update(long gameTime) {
		for (int i = 0; i < size(); i++) {
			this.get(i).update(gameTime);
		}
	}

	public void update(long gameTime, boolean childrenUpdate) {
		for (int i = 0; i < size(); i++) {
			this.get(i).update(gameTime, childrenUpdate);
		}
	}

	public void render(RenderContext renderContext) {
		for (int i = 0; i < this.size(); i++)
			this.get(i).render(renderContext);
	}

}
