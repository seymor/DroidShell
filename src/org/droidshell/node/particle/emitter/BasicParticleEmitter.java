package org.droidshell.node.particle.emitter;

import org.droidshell.lang.math.Vector2D;

public class BasicParticleEmitter implements iParticleEmitter {

	protected Vector2D position;
	
	public BasicParticleEmitter(Vector2D position) {
		this.position = position;
	}

	public Vector2D offset() {
		return position;
	}

}
