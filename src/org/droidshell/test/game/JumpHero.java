package org.droidshell.test.game;

import org.droidshell.input.touch.handler.iFlingEventHandler;
import org.droidshell.node.modifier.JumpModifier;

import android.view.MotionEvent;

public class JumpHero implements iFlingEventHandler {

	public JumpModifier jumpModifier;
	
	public JumpHero(JumpModifier jumpModifier) {
		this.jumpModifier = jumpModifier;
	}
	
	public boolean onHandleEvent(MotionEvent e1, MotionEvent e2,
			float velocityX, float velocityY) {
		if(velocityY < 0) {
			jumpModifier.isJumping = true;
		}
		return true;
	}

	
	
}
