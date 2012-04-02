package org.droidshell.node.particle;

import java.util.ArrayList;

import org.droidshell.node.Node;
import org.droidshell.node.particle.emitter.iParticleEmitter;
import org.droidshell.node.particle.modifier.iParticleModifier;
import org.droidshell.render.RenderContext;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ParticleSystem<T extends Node> extends Node {

	public Particle<T>[] particles;
	
	public iParticleEmitter particleEmitter;
	public ArrayList<iParticleModifier<T>> particleModifierList;
	
	@SuppressWarnings("unchecked")
	public ParticleSystem(int particleCount, T node, iParticleEmitter particleEmitter) {
		particles = (Particle<T>[]) new Particle[particleCount];
		this.particleEmitter = particleEmitter;
		
		for(int i = 0; i < particleCount; i++) {
			//TODO
			// particles[i] = new Particle(node.deepCopy, 1000);
		}
		
		particleModifierList = new ArrayList<iParticleModifier<T>>();
	}
	
	public ParticleSystem(Particle<T>[] particles, iParticleEmitter particleEmitter) {
		this.particles = particles;
		this.particleEmitter = particleEmitter;
		
		particleModifierList = new ArrayList<iParticleModifier<T>>();
	}
	
	public void initModifiers() {
		for(int i = 0; i < particleModifierList.size(); i++) {
			for(int j = 0; j < particles.length; j++)
				particleModifierList.get(i).setModifier(particles[j]);
		}
	}
	
	public void update(long gameTime) {
		for(int i = 0; i < particles.length; i++)
			particles[i].update(gameTime);
	}
	
	public void render(RenderContext renderContext) {
		for(int i = 0; i < particles.length; i++)
			particles[i].node.render(renderContext);
		
	}

}
