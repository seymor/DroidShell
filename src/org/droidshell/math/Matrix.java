package org.droidshell.math;

import android.util.Log;

public class Matrix {

	private static final String TAG = Matrix.class.getName();

	/*
	 * Static final variables
	 */

	public static final Matrix IDENTITY = new Matrix();
	public static final Matrix NIL = new Matrix(0);
	public static final Matrix ONE = new Matrix(1);

	/*
	 * Member variables
	 */

	public float m11;
	public float m12;
	public final float m13 = 0;

	public float m21;
	public float m22;
	public final float m23 = 0;

	public float m31;
	public float m32;
	public final float m33 = 1;

	/*
	 * Constructors
	 */

	public Matrix() {

		this.m11 = 1;
		this.m12 = 0;

		this.m21 = 0;
		this.m22 = 1;

		this.m31 = 0;
		this.m32 = 0;

	}

	public Matrix(float m) {

		this.m11 = m;
		this.m12 = m;

		this.m21 = m;
		this.m22 = m;

		this.m31 = m;
		this.m32 = m;
	}

	public Matrix(float m11, float m12, float m21, float m22) {

		this.m11 = m11;
		this.m12 = m12;

		this.m21 = m21;
		this.m22 = m22;

		this.m31 = 0;
		this.m32 = 0;

	}

	public Matrix(float m31, float m32) {

		this.m11 = 1;
		this.m12 = 0;

		this.m21 = 0;
		this.m22 = 1;

		this.m31 = m31;
		this.m32 = m32;

	}

	public Matrix(float m11, float m12, float m21, float m22, float m31,
			float m32) {

		this.m11 = m11;
		this.m12 = m12;

		this.m21 = m21;
		this.m22 = m22;

		this.m31 = m31;
		this.m32 = m32;

	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Public methods
	 */

	public Vector2D multiply(Vector2D v) {

		float xN = this.m11 * v.x + this.m21 * v.y + this.m31 * 1;
		float yN = this.m12 * v.x + this.m22 * v.y + this.m32 * 1;

		return new Vector2D(xN, yN);
	}

	public Matrix multiply(Matrix m) {

		this.m11 = this.m11 * m.m11 + this.m12 * m.m21 + this.m31 * m.m31;
		this.m12 = this.m11 * m.m12 + this.m12 * m.m22 + this.m32 * m.m31;

		this.m21 = this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31;
		this.m22 = this.m21 * m.m12 + this.m22 * m.m22 + this.m32 * m.m31;

		this.m31 = this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31;
		this.m32 = this.m31 * m.m12 + this.m32 * m.m22 + this.m32 * m.m31;

		return this;
	}

	public Matrix multiplyN(Matrix m) {

		float mN11 = this.m11 * m.m11 + this.m12 * m.m21 + this.m31 * m.m31;
		float mN12 = this.m11 * m.m12 + this.m12 * m.m22 + this.m32 * m.m31;

		float mN21 = this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31;
		float mN22 = this.m21 * m.m12 + this.m22 * m.m22 + this.m32 * m.m31;

		float mN31 = this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31;
		float mN32 = this.m31 * m.m12 + this.m32 * m.m22 + this.m32 * m.m31;

		return new Matrix(mN11, mN12, mN21, mN22, mN31, mN32);
	}

	public Matrix add(Matrix m) {

		this.m11 += m.m11;
		this.m12 += m.m12;

		this.m21 += m.m21;
		this.m22 += m.m22;

		this.m31 += m.m31;
		this.m32 += m.m32;

		return this;

	}

	public Matrix addN(Matrix m) {

		float mN11 = this.m11 + m.m11;
		float mN12 = this.m12 + m.m12;

		float mN21 = this.m21 + m.m21;
		float mN22 = this.m22 + m.m22;

		float mN31 = this.m31 + m.m31;
		float mN32 = this.m32 + m.m32;

		return new Matrix(mN11, mN12, mN21, mN22, mN31, mN32);

	}

	public static Matrix getRotationMatrix(float angleDeg) {
		float angle = MathHelper.degreeToRadian(angleDeg);

		return new Matrix(MathHelper.cos(angle), MathHelper.sin(angle),
				-MathHelper.sin(angle), MathHelper.cos(angle));
	}

	public static Matrix getTranslationMatrix(float translX, float translY) {
		return new Matrix(translX, translY);
	}

	public static Matrix getScaleMatrix(float scaleX, float scaleY) {
		return new Matrix(scaleX, 0, 0, scaleY);
	}

	public int hashCode() {
		int hash = 13;

		hash += 29 * hash + Float.floatToIntBits(m11);
		hash += 29 * hash + Float.floatToIntBits(m12);
		hash += 29 * hash + Float.floatToIntBits(m13);
		hash += 29 * hash + Float.floatToIntBits(m21);
		hash += 29 * hash + Float.floatToIntBits(m22);
		hash += 29 * hash + Float.floatToIntBits(m23);
		hash += 29 * hash + Float.floatToIntBits(m31);
		hash += 29 * hash + Float.floatToIntBits(m32);
		hash += 29 * hash + Float.floatToIntBits(m33);
		return hash;
	}

	@Override
	public Matrix clone() {
		try {
			return (Matrix) super.clone();
		} catch (CloneNotSupportedException e) {
			Log.d(TAG, e.getMessage());
		}
		return null;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		try {
			Matrix m = (Matrix) o;

			if (Float.compare(m.m11, this.m11) == 0
					&& Float.compare(m.m12, this.m12) == 0
					&& Float.compare(m.m21, this.m21) == 0
					&& Float.compare(m.m22, this.m22) == 0
					&& Float.compare(m.m31, this.m31) == 0
					&& Float.compare(m.m32, this.m32) == 0)
				return true;
			else
				return false;

		} catch (ClassCastException e) {
			Log.d(TAG, e.getMessage());
		}

		return false;
	}

	public String toString() {
		return "(" + m11 + "," + m12 + "," + m13 + "," + m21 + "," + m22 + ","
				+ m23 + "," + m31 + "," + m32 + "," + m33 + ")";
	}

	public float[] toFloatArray() {
		return new float[] { m11, m12, m13, m21, m22, m23, m31, m32, m33 };
	}

}
