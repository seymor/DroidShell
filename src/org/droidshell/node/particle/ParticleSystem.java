package org.droidshell.node.particle;

import java.util.ArrayList;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.node.Node;
import org.droidshell.node.particle.emitter.ParticleEmitter;
import org.droidshell.node.particle.modifier.iParticleModifier;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ParticleSystem<T extends Node> extends Node {

	public Particle<T>[] particles;
	
	public ParticleEmitter particleEmitter;
	public ArrayList<iParticleModifier<T>> particleModifierList;
	
	@SuppressWarnings("unchecked")
	public ParticleSystem(int particleCount, T node, ParticleEmitter particleEmitter) {
		particles = (Particle<T>[]) new Particle[particleCount];
		this.particleEmitter = particleEmitter;
		
		for(int i = 0; i < particleCount; i++) {
			particles[i] = new Particle<T>((T)node.clone(), particleEmitter.clone());
		}
		
		particleModifierList = new ArrayList<iParticleModifier<T>>();
	}
	
	public ParticleSystem(Particle<T>[] particles, ParticleEmitter particleEmitter) {
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
	
	public void onUpdate(long gameTime) {
		for(int i = 0; i < particles.length; i++)
			particles[i].onUpdate(gameTime);
	}
	
	public void onRender(RenderContext renderContext) {
		for(int i = 0; i < particles.length; i++)
			particles[i].node.onRender(renderContext);
		
	}

}
