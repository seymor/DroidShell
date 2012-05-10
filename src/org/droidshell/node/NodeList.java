package org.droidshell.node;

import java.util.ArrayList;

import org.droidshell.engine.render.RenderContext;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class NodeList<T extends Node> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final String TAG = NodeList.class.getName();

	public NodeList() {
		super();
	}

	public NodeList(int size) {
		super(size);
	}

	private int calculateIndex(T object) {
		for (int i = 0; i < size(); i++) {
			if (object.zIndex < get(i).zIndex)
				return i;
		}
		return size();
	}

	@Override
	public boolean add(T object) {
		add(calculateIndex(object), object);
		return true;
	};

	public NodeList<T> push(T object) {
		add(object);
		return this;
	}
	
	public NodeList<T> pushList(ArrayList<T> list) {
		for(int i = 0; i < list.size(); i++)
			add(list.get(i));
		return this;
	}

	public void onUpdate(long gameTime) {
		for (int i = 0; i < size(); i++) {
			Node node = this.get(i);
			if (node.isUpdatable)
				node.onUpdate(gameTime);
		}
	}

	public void onUpdate(long gameTime, boolean childrenUpdate) {
		for (int i = 0; i < size(); i++) {
			Node node = this.get(i);
			if (node.isUpdatable)
				node.onUpdate(gameTime, childrenUpdate);
		}
	}

	public void onRender(RenderContext renderContext) {
		for (int i = 0; i < this.size(); i++) {
			Node node = this.get(i);
			if (node.isVisible)
				node.onRender(renderContext);
		}

	}

}
