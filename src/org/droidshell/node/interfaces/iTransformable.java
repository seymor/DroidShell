package org.droidshell.node.interfaces;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public interface iTransformable {
	
	public void onTranslate(float tX, float tY);
	public void onScale(float sX, float sY);
	public void onRotate(float angleD);

}
