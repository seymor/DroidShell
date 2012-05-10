package org.droidshell.node.particle;

import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;
import org.droidshell.node.particle.emitter.ParticleEmitter;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Particle<T extends Node> {

	private Vector2D tempPosition;

	public int actualAge;
	public boolean isDead = false;

	public ParticleEmitter particleEmitter;

	public T node;

	public Particle(T node, ParticleEmitter particleEmitter) {
		this.node = node;
		this.particleEmitter = particleEmitter;
		tempPosition = new Vector2D();
		reborn();
	}

	public void onUpdate(long gameTime) {
		if (!isDead) {
			Vector2D.add(particleEmitter.position, particleEmitter.position,
					Vector2D.multiply(tempPosition, particleEmitter.velocity,
							gameTime / 1000.0f));
			actualAge += gameTime;
			node.resetPosition();
			node.onTranslate(particleEmitter.position.x,
					particleEmitter.position.y);
			node.onUpdate(gameTime);
		}

		if (actualAge >= particleEmitter.age && !isDead)
			reborn();
	}

	public void reborn() {
		actualAge = 0;
		particleEmitter.reborn();
	}

}
