package org.droidshell.node.particle.modifier;

import org.droidshell.node.Node;
import org.droidshell.node.particle.Particle;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public interface iParticleModifier<T extends Node> {

	public void setModifier(Particle<T> particle);
}
