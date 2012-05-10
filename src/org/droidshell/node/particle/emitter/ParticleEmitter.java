package org.droidshell.node.particle.emitter;

import org.droidshell.lang.math.Math;
import org.droidshell.lang.math.Vector2D;

import android.util.Log;

public class ParticleEmitter implements Cloneable {

	private static final String TAG = ParticleEmitter.class.getName();
	
	private Vector2D absPosition;
	private Vector2D absVelocity;
	
	public Vector2D position;
	public Vector2D velocity;
	public int minAge;
	public int maxAge;
	public int age;

	public ParticleEmitter(int minAge, int maxAge, Vector2D absPosition, Vector2D absVelocity) {
		this.absPosition = absPosition;
		this.absVelocity = absVelocity;
		position = new Vector2D();
		velocity = new Vector2D();
		this.minAge = minAge;
		this.maxAge = maxAge;
	}

	public void reborn() {
		age = Math.generateRandomInteger(minAge, maxAge);
		position.set(Math.generateRandomFloat(-absPosition.x, absPosition.x),
				Math.generateRandomFloat(-absPosition.y, absPosition.y));
		velocity.set(Math.generateRandomFloat(-absVelocity.x, absVelocity.x),
				Math.generateRandomFloat(-absVelocity.y, absVelocity.y));
	}

	@Override
	public ParticleEmitter clone() {
		try {
			ParticleEmitter pe = (ParticleEmitter) super.clone();
			pe.position = new Vector2D();
			pe.velocity = new Vector2D();
			return pe;
		} catch (CloneNotSupportedException e) {
			Log.d(TAG, e.getMessage());
		}
		return null;
	}

}
