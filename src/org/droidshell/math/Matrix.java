package org.droidshell.math;

import android.util.Log;

public class Matrix {

	private static final String TAG = Matrix.class.getName();

	/*
	 * Member variables
	 */

	private float[] m;

	/*
	 * Constructors
	 */

	public Matrix() {
		this.m = new float[16];
		for (int i = 0; i < 16; i++) {
			if (i % 5 == 0)
				this.m[i] = 1.0f;
			else
				this.m[i] = 0.0f;
		}
	}

	public Matrix(float m) {
		this.m = new float[16];

		for (int i = 0; i < 16; i++)
			this.m[i] = m;
	}

	public Matrix(float[] m4x4) {
		this.m = m4x4;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Public methods
	 */

	public static Matrix identity() {
		return new Matrix();
	}

	public Vector2D multiply(Vector2D v) {
		float xN = m[0] * v.x + m[4] * v.y + m[8] * 1;
		float yN = m[1] * v.x + m[5] * v.y + m[9] * 1;

		return new Vector2D(xN, yN);
	}

	public Matrix multiply(Matrix m) {
		float[] result = new float[16];
		android.opengl.Matrix.multiplyMM(result, 0, this.toFloatArray(), 0,
				m.toFloatArray(), 0);
		this.m = result;

		return this;
	}

	public Matrix multiplyN(Matrix m) {
		float[] result = new float[16];
		android.opengl.Matrix.multiplyMM(result, 0, this.toFloatArray(), 0,
				m.toFloatArray(), 0);

		return new Matrix(result);
	}

	public Matrix add(Matrix m) {
		for (int i = 0; i < 16; i++)
			this.m[i] += m.m[i];

		return this;
	}

	public Matrix addN(Matrix m) {
		Matrix n = new Matrix(0);
		for (int i = 0; i < 16; i++)
			n.m[i] = m.m[i];

		return n;
	}

	public static Matrix projMatrix(float left, float right, float bottom,
			float top, float near, float far) {
		float[] matrix = new float[16];
		android.opengl.Matrix.frustumM(matrix, 0, left, right, bottom, top,
				near, far);

		return new Matrix(matrix);
	}

	public static Matrix lookAtMatrix(float eyeX, float eyeY, float eyeZ,
			float centerX, float centerY, float centerZ, float upX, float upY,
			float upZ) {
		float[] matrix = new float[16];
		android.opengl.Matrix.setLookAtM(matrix, 0, eyeX, eyeY, eyeZ, centerX,
				centerY, centerZ, upX, upY, upZ);

		return new Matrix(matrix);
	}

	public static Matrix rotationMatrix(float angleDeg) {
		float angle = MathHelper.degreeToRadian(angleDeg);
		Matrix m = new Matrix();

		m.m[0] = MathHelper.cos(angle);
		m.m[1] = -MathHelper.sin(angle);
		m.m[4] = MathHelper.sin(angle);
		m.m[5] = MathHelper.cos(angle);

		return m;
	}

	public static Matrix translationMatrix(float translX, float translY) {
		Matrix m = new Matrix();
		m.m[8] = translX;
		m.m[9] = translY;
		
		return m;
	}

	public static Matrix scaleMatrix(float scaleX, float scaleY) {
		Matrix m = new Matrix();
		m.m[0] = scaleX;
		m.m[5] = scaleY;
		
		return m;
	}

	public void translate(float translX, float translY) {
		this.multiply(Matrix.translationMatrix(translX, translY));
	}

	public void rotate(float angleDeg) {
		this.multiply(Matrix.rotationMatrix(angleDeg));
	}

	public void scale(float scaleX, float scaleY) {
		this.multiply(Matrix.scaleMatrix(scaleX, scaleY));
	}

	public int hashCode() {
		int hash = 13;

		for(int i = 0; i < 16; i++)
			hash += 29 * hash + Float.floatToIntBits(m[i]);
		
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

			boolean isEqual = true;
			for(int i = 0; i < 16; i++) {
				if (Float.compare(m.m[i], this.m[i]) != 0)
					isEqual = false;
			}
			
			return isEqual ? true : false;

		} catch (ClassCastException e) {
			Log.d(TAG, e.getMessage());
		}

		return false;
	}

	public String toString() {
		String out = "(";
		for(int i = 0; i < 16; i++)
			out += m[i] + ",";
		
		out += ")";
		
		return out;
	}

	public float[] toFloatArray() {
		float[] f = new float[16];
		for(int i = 0; i < 16; i++) {
			f[i] = m[i];
		}
		
		return f;
	}

}
