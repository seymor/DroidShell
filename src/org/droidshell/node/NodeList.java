package org.droidshell.node;

import java.util.ArrayList;

public class NodeList {
	
	private static ArrayList<Node> list;
	
	public static void add(Node node) {
		list.add(node);
	}
	
	public static void updateAll() {
		for(int i=0; i < list.size(); i++)
			list.get(i).update(false);
	}
	
	public static void updateAllRecursively() {
		for(int i=0; i < list.size(); i++)
			list.get(i).update(true);
	}

}
