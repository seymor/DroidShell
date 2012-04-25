package org.droidshell.node.particle.modifier;

import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;
import org.droidshell.node.particle.Particle;

public class ScaleParticleModifier<T extends Node> implements iParticleModifier<T> {

	public Vector2D value;
	
	public ScaleParticleModifier(Vector2D value) {
		this.value = value;
	}
	
	public void setModifier(Particle<T> particle) {
		particle.node.onScale(value.x, value.y);		
	}

}
