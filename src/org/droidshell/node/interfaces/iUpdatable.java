package org.droidshell.node.interfaces;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public interface iUpdatable {
	
	public void update(long gameTime);
	public void update(boolean childrenUpdate);

}
