package org.droidshell.node;

import org.droidshell.math.Matrix;
import org.droidshell.math.Vector2D;
import org.droidshell.node.interfaces.iDrawable;
import org.droidshell.node.interfaces.iTransformable;
import org.droidshell.node.interfaces.iUpdatable;

public abstract class Node implements iUpdatable, iDrawable, iTransformable{
	
	public Vector2D coords;
	
	public Matrix modelMatrix = Matrix.identity();
	
	public boolean isVisible = true;
	public boolean isUpdatable = true;
	
	public int zIndex = 0;
	
	private Node parentNode;
	private NodeList<Node> childrenList;
	
	public Node(Vector2D coords) {
		this.coords = coords;
	}
	
	public Node getParentNode() {
		return parentNode;
	}
	
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	public NodeList<Node> getChildrenList() {
		return childrenList;
	}
	
	public void setChildrenList(NodeList<Node> childrenList) {
		this.childrenList = childrenList;
	}
	
	public void update(boolean childrenUpdate) {
		if (childrenUpdate)
			for(int i=0; i < childrenList.size(); i++)
				childrenList.get(i).update(true);
	}

}
