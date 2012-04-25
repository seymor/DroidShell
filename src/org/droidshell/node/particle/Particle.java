package org.droidshell.node.particle;

import org.droidshell.lang.math.Math;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Particle<T extends Node> {

	public long actualAge;
	public long maxAge;
	public boolean isDead = false;

	public Vector2D velocity;
	public Vector2D position;

	public T node;

	public Particle(T node, long maxAge) {
		this.node = node;
		this.maxAge = maxAge;
		position = new Vector2D(0, 0);
		velocity = new Vector2D(0, 0);
		reborn();
	}

	public void update(long gameTime) {
		if (!isDead) {
			position.add(velocity.multiply(gameTime));
			actualAge += gameTime;
			node.onTranslate(position.x, position.y);
			node.onUpdate(gameTime);
		}

		if (actualAge >= maxAge && !isDead)
			reborn();
	}

	public void reborn() {
		position.setAll(0);
		velocity.set(Math.generateRandomFloat(0, 0.01f),
				Math.generateRandomFloat(0, 0.01f));
	}

}
