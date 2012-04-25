package org.droidshell.node.interfaces;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public interface iUpdatable {
	
	public void onUpdate(long gameTime);
	public void onUpdate(long gameTime, boolean childrenUpdate);

}
