package org.droidshell.math;

import android.util.Log;

public class Vector2D implements Cloneable {

	private static final String TAG = Vector2D.class.getName();

	/*
	 * Member variables
	 */

	private float x;
	private float y;

	/*
	 * Constructors
	 */

	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}

	/*
	 * Getters and Setters
	 */

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	/*
	 * Public methods
	 */
	
	public Vector2D set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector2D setAll(float f) {
		this.x = f;
		this.y = f;
		return this;
	}

	public Vector2D add(Vector2D v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}

	public Vector2D addN(Vector2D v) {
		return new Vector2D(this.x + v.x, this.y + v.y);
	}

	public Vector2D subtract(Vector2D v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}

	public Vector2D subtractN(Vector2D v) {
		return new Vector2D(this.x - v.x, this.y - v.y);
	}

	public Vector2D scale(float s) {
		this.x *= s;
		this.y *= s;
		return this;
	}

	public Vector2D scaleN(float s) {
		return new Vector2D(this.x * s, this.y * s);
	}

	public Vector2D divide(float s) {
		this.x /= s;
		this.y /= s;
		return this;
	}

	public Vector2D divideN(float s) {
		return new Vector2D(this.x / s, this.y / s);
	}

	public Vector2D neg() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}

	public Vector2D negN() {
		return new Vector2D(-this.x, -this.y);
	}

	public Vector2D normalize() {
		float l = this.length();
		if (l != 0)
			return divide(l);

		return divide(1);
	}

	public Vector2D normalizeN() {
		float l = this.length();
		if (l != 0)
			return divideN(l);

		return divideN(1);
	}

	public float dotProduct(Vector2D v) {
		return this.x * v.x + this.y * v.y;
	}

	public float length() {
		return MathHelper.sqrt(this.x * this.x + this.y + this.y);
	}

	public float angle() {
		return MathHelper.atan2(this.y, this.x);
	}

	public float angle(Vector2D unit) {
		return MathHelper.acos(this.dotProduct(unit));
	}

	public int hashCode() {
		int hash = 13;

		hash += 29 * hash + Float.floatToIntBits(x);
		hash += 29 * hash + Float.floatToIntBits(y);
		return hash;
	}

	@Override
	public Vector2D clone() {
		try {
			return (Vector2D) super.clone();
		} catch (CloneNotSupportedException e) {
			Log.d(TAG, e.getMessage());
		}
		return null;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		
		try {
			Vector2D v = (Vector2D) o;
			
			if (Float.compare(v.x, this.x) == 0 && Float.compare(v.y, this.y) == 0)
				return true;
			else
				return false;
			
		} catch (ClassCastException e) {
			Log.d(TAG, e.getMessage());
		}
		
		return false;
	}
	
	public String toString() {
		return x + ":" + y;
	}
	
	public float[] toFloatArray() {
		float[] array = new float[2];
		
		array[0] = x;
		array[1] = y;
		
		return array;
	}

}
