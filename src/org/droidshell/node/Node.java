package org.droidshell.node;

import java.util.ArrayList;

import org.droidshell.math.Color;
import org.droidshell.math.Vector2D;

public class Node {
	
	public Vector2D coords;
	public Color color = Color.BLACK;
	
	public boolean isVisible = true;
	public boolean isUpdatable = true;
	
	public int zIndex = 0;
	
	private Node parentNode;
	private ArrayList<Node> childrenList;
	
	public Node(Vector2D coords, Color color) {
		this.coords = coords;
		this.color = color;
	}
	
	public Node(Vector2D coords) {
		this.coords = coords;
	}
	
	public Node getParentNode() {
		return parentNode;
	}
	
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	public ArrayList<Node> getChildrenList() {
		return childrenList;
	}
	
	public void setChildrenList(ArrayList<Node> childrenList) {
		this.childrenList = childrenList;
	}

}
