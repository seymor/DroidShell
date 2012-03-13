package org.droidshell.math;

public class Vector3D {
	
	/*
	 * Member variables
	 */

	public float x;
	public float y;
	public float z;
	
	/*
	 * Constructors
	 */
	
	public Vector3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3D(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 1;
	}
	
	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D(Vector3D v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

}
