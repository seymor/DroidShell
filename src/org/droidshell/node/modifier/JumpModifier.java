package org.droidshell.node.modifier;

import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;
import org.droidshell.test.game.Hero;

public class JumpModifier implements iNodeModifier {

	@SuppressWarnings("unused")
	private static final String TAG = JumpModifier.class.getName();

	public Hero hero;
	public final float gravity;

	public long jumpingTime;
	public boolean isJumping;

	private Vector2D displacement;

	public Vector2D velocity;
	public long movingTime = 0;

	public JumpModifier(Hero hero, float gravity, Vector2D velocity) {
		this.hero = hero;
		this.velocity = velocity;
		this.gravity = gravity;
		jumpingTime = 0;
		isJumping = false;

		displacement = new Vector2D();
	}

	private float currentPosition() {
		/*
		 * s = s1 - s2; s = v0*t - (g/2) * t^2;
		 */
		float s1 = velocity.y * jumpingTime / 1000;
		float s2 = gravity / 2 * jumpingTime / 1000 * jumpingTime / 1000;

		return s1 - s2;
	}

	public void onUpdate(Node node, long gameTime) {
		movingTime += gameTime;
		Vector2D.multiply(displacement, velocity, movingTime);

		if (currentPosition() < 0)
			isJumping = false;

		if (isJumping) {
			hero.sprite.isAnimated = false;
			jumpingTime += gameTime;
			node.onTranslate(displacement.x, currentPosition() - 0.3f);
		} else {
			node.onTranslate(displacement.x, -0.3f);
			hero.sprite.isAnimated = true;
			jumpingTime = 0;
			isJumping = false;
		}
	}

}
