package org.droidshell.lang.math;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Vector2D implements Cloneable {

	private static final String TAG = Vector2D.class.getName();

	/*
	 * Member variables
	 */

	public float x;
	public float y;

	/*
	 * Constructors
	 */

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(Vector2D v) {
		x = v.x;
		y = v.y;
	}

	public Vector2D(Vector3D v) {
		x = v.x;
		y = v.y;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Public methods
	 */

	public Vector2D set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector2D setAll(float f) {
		x = f;
		y = f;
		return this;
	}

	public Vector2D add(Vector2D v) {
		x += v.x;
		y += v.y;
		return this;
	}
	
	public Vector2D add(Vector3D v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public Vector2D addN(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}

	public Vector2D subtract(Vector2D v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	public Vector2D subtractN(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);
	}

	public Vector2D multiply(float s) {
		x *= s;
		y *= s;
		return this;
	}

	public Vector2D multiplyN(float s) {
		return new Vector2D(x * s, y * s);
	}

	public Vector2D divide(float s) {
		x /= s;
		y /= s;
		return this;
	}

	public Vector2D divideN(float s) {
		return new Vector2D(x / s, y / s);
	}

	public Vector2D neg() {
		x = -x;
		y = -y;
		return this;
	}

	public Vector2D negN() {
		return new Vector2D(-x, -y);
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
		return x * v.x + y * v.y;
	}

	public float length() {
		return Math.sqrt(x * x + y * y);
	}

	public float angle() {
		return Math.atan2(y, x);
	}

	public float angle(Vector2D unit) {
		return Math.acos(this.dotProduct(unit));
	}

	public static Vector2D add(Vector2D resultVector, Vector2D v1, Vector2D v2) {
		resultVector.x = v1.x + v2.x;
		resultVector.y = v1.y + v2.y;

		return resultVector;
	}
	
	public static Vector2D add(Vector2D resultVector, Vector2D v1, Vector3D v2) {
		resultVector.x = v1.x + v2.x;
		resultVector.y = v1.y + v2.y;

		return resultVector;
	}

	public static Vector2D subtract(Vector2D resultVector, Vector2D v1,
			Vector2D v2) {
		resultVector.x = v1.x - v2.x;
		resultVector.y = v1.y - v2.y;

		return resultVector;
	}

	public static Vector2D multiply(Vector2D resultVector, Vector2D v, float s) {
		resultVector.x = v.x * s;
		resultVector.y = v.y * s;

		return resultVector;
	}

	public static Vector2D divide(Vector2D resultVector, Vector2D v, float s) {
		resultVector.x = v.x / s;
		resultVector.y = v.y / s;

		return resultVector;
	}

	public static Vector2D neg(Vector2D resultVector, Vector2D v) {
		resultVector.x = -v.x;
		resultVector.y = -v.y;

		return resultVector;
	}

	public static Vector2D normalize(Vector2D resultVector, Vector2D v) {
		float l = v.length();
		if (l != 0)
			return Vector2D.divide(resultVector, v, l);

		return Vector2D.divide(resultVector, v, 1);
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
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		try {
			Vector2D v = (Vector2D) o;

			boolean comp = Float.compare(v.x, x) == 0
					&& Float.compare(v.y, y) == 0;

			return comp;

		} catch (ClassCastException e) {
			Log.e(TAG, e.getMessage());
		}

		return false;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public float[] toFloatArray() {
		return new float[] { x, y };
	}

}
