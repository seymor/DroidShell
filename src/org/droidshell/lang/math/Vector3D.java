package org.droidshell.lang.math;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Vector3D implements Cloneable {

	private static final String TAG = Vector3D.class.getName();

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
		x = 0;
		y = 0;
		z = 0;
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

	public Vector3D(Vector2D v, float z) {
		this.x = v.x;
		this.y = v.y;
		this.z = z;
	}

	public Vector3D(Vector3D v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Public methods
	 */

	public Vector3D set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3D setAll(float f) {
		x = f;
		y = f;
		z = f;
		return this;
	}

	public Vector3D add(Vector2D v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public Vector3D addN(Vector2D v) {
		return new Vector3D(x + v.x, y + v.y, z);
	}

	public Vector3D add(Vector3D v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vector3D addN(Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}

	public Vector3D subtract(Vector3D v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public Vector3D subtractN(Vector3D v) {
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}

	public Vector3D subtract(Vector2D v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	public Vector3D subtractN(Vector2D v) {
		return new Vector3D(x - v.x, y - v.y, z);
	}

	public Vector3D multiply(float s) {
		x *= s;
		y *= s;
		z *= s;
		return this;
	}

	public Vector3D multiplyN(float s) {
		return new Vector3D(x * s, y * s, z * s);
	}

	public Vector3D divide(float s) {
		x /= s;
		y /= s;
		z /= s;
		return this;
	}

	public Vector3D divideN(float s) {
		return new Vector3D(x / s, y / s, z / s);
	}

	public Vector3D neg() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	public Vector3D negN() {
		return new Vector3D(-x, -y, -z);
	}

	public Vector3D normalize() {
		float l = this.length();
		if (l != 0)
			return divide(l);

		return divide(1);
	}

	public Vector3D normalizeN() {
		float l = this.length();
		if (l != 0)
			return divideN(l);

		return divideN(1);
	}

	public float dotProduct(Vector3D v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public Vector3D crossProduct(Vector3D v) {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y
				* v.x);
	}

	public float length() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public float angle(Vector3D unit) {
		return Math.acos(this.dotProduct(unit));
	}

	public static Vector3D add(Vector3D resultVector, Vector3D v1, Vector3D v2) {
		resultVector.x = v1.x + v2.x;
		resultVector.y = v1.y + v2.y;
		resultVector.z = v1.z + v2.z;

		return resultVector;
	}

	public static Vector3D subtract(Vector3D resultVector, Vector3D v1,
			Vector3D v2) {
		resultVector.x = v1.x - v2.x;
		resultVector.y = v1.y - v2.y;
		resultVector.z = v1.z - v2.z;

		return resultVector;
	}

	public static Vector3D multiply(Vector3D resultVector, Vector3D v, float s) {
		resultVector.x = v.x * s;
		resultVector.y = v.y * s;
		resultVector.z = v.z * s;

		return resultVector;
	}

	public static Vector3D divide(Vector3D resultVector, Vector3D v, float s) {
		resultVector.x = v.x / s;
		resultVector.y = v.y / s;
		resultVector.z = v.z / s;

		return resultVector;
	}

	public static Vector3D neg(Vector3D resultVector, Vector3D v) {
		resultVector.x = -v.x;
		resultVector.y = -v.y;
		resultVector.z = -v.z;

		return resultVector;
	}

	public static Vector3D normalize(Vector3D resultVector, Vector3D v) {
		float l = v.length();
		if (l != 0)
			return Vector3D.divide(resultVector, v, l);

		return Vector3D.divide(resultVector, v, 1);
	}

	public static Vector3D crossProduct(Vector3D resultVector, Vector3D v1,
			Vector3D v2) {
		resultVector.x = v1.y * v2.z - v1.z * v2.y;
		resultVector.y = v1.z * v2.x - v1.x * v2.z;
		resultVector.z = v1.x * v2.y - v1.y * v2.x;

		return resultVector;
	}

	public int hashCode() {
		int hash = 13;

		hash += 29 * hash + Float.floatToIntBits(x);
		hash += 29 * hash + Float.floatToIntBits(y);
		hash += 29 * hash + Float.floatToIntBits(z);
		return hash;
	}

	@Override
	public Vector3D clone() {
		try {
			return (Vector3D) super.clone();
		} catch (CloneNotSupportedException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		try {
			Vector3D v = (Vector3D) o;

			boolean comp = Float.compare(v.x, x) == 0
					&& Float.compare(v.y, y) == 0 && Float.compare(v.z, z) == 0;

			return comp;

		} catch (ClassCastException e) {
			Log.e(TAG, e.getMessage());
		}

		return false;
	}

	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	public float[] toFloatArray() {
		return new float[] { x, y, z };
	}

}
