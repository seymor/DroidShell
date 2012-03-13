package org.droidshell.node;

import java.util.ArrayList;

import org.droidshell.node.interfaces.iRenderable;
import org.droidshell.node.interfaces.iUpdatable;
import org.droidshell.render.RenderContext;

import android.util.Log;

public class NodeList<E extends iUpdatable & iRenderable> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;
	private static final String TAG = NodeList.class.getName();
	
	public NodeList() {
	}
	
	public NodeList(int size) {
		super(size);
	}
	
	public void add(int index, E object) {
		E old = null;
		
		if(index < this.size() && index >= 0) {
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
		if(index < this.size() && index >= 0) {
			removable = this.get(index);
			this.set(index,this.get(this.size()-1));
			return removable;
		}
		
		Log.e(TAG,"Node not found on index: " + index);
		return null;
	}
	
	public void updateAll(long gameTime) {
		for(int i=0; i < this.size(); i++)
			this.get(i).update(gameTime);
	}
	
	public void updateAllRecursively() {
		for(int i=0; i < this.size(); i++)
			this.get(i).update(true);
	}
	
	public void renderAll(RenderContext renderContext) {
		for(int i=0; i < this.size(); i++)
			this.get(i).render(renderContext);
	}

}
