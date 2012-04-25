package org.droidshell.lang.math;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Matrix {

	private static final String TAG = Matrix.class.getName();

	private static Matrix translationMatrix = new Matrix();
	private static Matrix scaleMatrix = new Matrix();
	private static Matrix rotationMatrix = new Matrix();

	private static float[] result = new float[16];

	/*
	 * Member variables
	 */

	public float[] m;

	/*
	 * Constructors
	 */

	public Matrix() {
		m = new float[16];

		for (int i = 0; i < 16; i++) {
			if (i % 5 == 0)
				m[i] = 1.0f;
			else
				m[i] = 0.0f;
		}
	}

	public Matrix(float m) {
		this.m = new float[16];

		for (int i = 0; i < 16; i++)
			this.m[i] = m;
	}

	public Matrix(float[] m4x4) {
		m = new float[16];

		System.arraycopy(m4x4, 0, m, 0, 16);
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Public methods
	 */

	private static void clearResult() {
		for (int i = 0; i < 16; i++)
			Matrix.result[i] = 0;
	}

	public Matrix loadIdentity() {
		for (int i = 0; i < 16; i++) {
			if (i % 5 == 0)
				m[i] = 1.0f;
			else
				m[i] = 0.0f;
		}

		return this;
	}

	public Vector2D multiply(Vector2D v) {
		float xN = m[0] * v.x + m[4] * v.y + m[8] * 1;
		float yN = m[1] * v.x + m[5] * v.y + m[9] * 1;

		v.x = xN;
		v.y = yN;

		return v;
	}

	public Vector2D multiplyN(Vector2D v) {
		float xN = m[0] * v.x + m[4] * v.y + m[8] * 1;
		float yN = m[1] * v.x + m[5] * v.y + m[9] * 1;

		return new Vector2D(xN, yN);
	}

	public Vector3D multiply(Vector3D v) {
		float xN = m[0] * v.x + m[4] * v.y + m[8] * 1;
		float yN = m[1] * v.x + m[5] * v.y + m[9] * 1;
		float zN = m[2] * v.x + m[6] * v.y + m[10] * 1;

		v.x = xN;
		v.y = yN;
		v.z = zN;

		return v;
	}

	public Vector3D multiplyN(Vector3D v) {
		float xN = m[0] * v.x + m[4] * v.y + m[8] * 1;
		float yN = m[1] * v.x + m[5] * v.y + m[9] * 1;
		float zN = m[2] * v.x + m[6] * v.y + m[10] * 1;

		return new Vector3D(xN, yN, zN);
	}

	public Matrix multiply(Matrix m) {
		Matrix.clearResult();

		android.opengl.Matrix.multiplyMM(Matrix.result, 0, this.toFloatArray(),
				0, m.toFloatArray(), 0);

		System.arraycopy(Matrix.result, 0, this.m, 0, 16);

		return this;
	}

	public Matrix multiplyN(Matrix m) {
		Matrix.clearResult();

		android.opengl.Matrix.multiplyMM(Matrix.result, 0, this.toFloatArray(),
				0, m.toFloatArray(), 0);

		return new Matrix(Matrix.result);
	}

	public Matrix add(Matrix m) {
		for (int i = 0; i < 16; i++)
			this.m[i] += m.m[i];

		return this;
	}

	public Matrix addN(Matrix m) {
		Matrix n = new Matrix(0);
		for (int i = 0; i < 16; i++)
			n.m[i] = m.m[i] + this.m[i];
		return n;
	}

	public void translate(float translX, float translY) {
		multiply(Matrix.translationMatrix(translX, translY));
	}

	public void rotate(float angleDeg) {
		multiply(Matrix.rotationMatrix(angleDeg));
	}

	public void scale(float scaleX, float scaleY) {
		multiply(Matrix.scaleMatrix(scaleX, scaleY));
	}

	public static Matrix identityMatrix() {
		return new Matrix();
	}

	/*
	 * http://db-in.com/blog/2011/04/cameras-on-opengl-es-2-x/
	 */
	public static Matrix projectionMatrix(Matrix resultMatrix, float ratio,
			float angleView, float near, float far) {
		Matrix.clearResult();

		// android.opengl.Matrix.frustumM(Matrix.result, 0, left, right, bottom,
		// top, near, far);

		float size = near * Math.tan(Math.degreeToRadian(angleView) / 2.0f);
		float left = size;
		float right = -size;
		float bottom = -size / ratio;
		float top = size / ratio;

		result[0] = 2 * near / (right - left);
		result[1] = 0;
		result[2] = 0;
		result[3] = 0;

		result[4] = 0;
		result[5] = 2 * near / (top - bottom);
		result[6] = 0;
		result[7] = 0;

		result[8] = (right + left) / (right - left);
		result[9] = (top + bottom) / (top - bottom);
		result[10] = -(far + near) / (far - near);
		result[11] = -1;

		result[12] = 0;
		result[13] = 0;
		result[14] = -(2 * far * near) / (far - near);
		result[15] = 0;

		resultMatrix.loadIdentity();
		System.arraycopy(Matrix.result, 0, resultMatrix.m, 0, 16);

		return resultMatrix;
	}

	public static Matrix lookAtMatrix(Matrix resultMatrix, Vector3D eye,
			Vector3D center, Vector3D up) {
		Matrix.clearResult();
		android.opengl.Matrix.setLookAtM(Matrix.result, 0, eye.x, eye.y, eye.z,
				center.x, center.y, center.z, up.x, up.y, up.z);

		resultMatrix.loadIdentity();
		System.arraycopy(Matrix.result, 0, resultMatrix.m, 0, 16);

		return resultMatrix;
	}

	public static Matrix rotationMatrix(float angleDeg) {
		float angle = Math.degreeToRadian(angleDeg);

		Matrix.rotationMatrix.m[0] = Math.cos(angle);
		Matrix.rotationMatrix.m[1] = -Math.sin(angle);
		Matrix.rotationMatrix.m[4] = Math.sin(angle);
		Matrix.rotationMatrix.m[5] = Math.cos(angle);

		return Matrix.rotationMatrix;
	}

	public static Matrix translationMatrix(float translX, float translY) {

		Matrix.translationMatrix.m[8] = translX;
		Matrix.translationMatrix.m[9] = translY;

		return Matrix.translationMatrix;
	}

	public static Matrix scaleMatrix(float scaleX, float scaleY) {

		Matrix.scaleMatrix.m[0] = scaleX;
		Matrix.scaleMatrix.m[5] = scaleY;

		return Matrix.scaleMatrix;
	}

	public static Matrix add(Matrix resultMatrix, Matrix m1, Matrix m2) {
		for (int i = 0; i < 16; i++)
			resultMatrix.m[i] = m1.m[i] + m2.m[i];

		return resultMatrix;
	}

	public static Vector2D multiply(Vector2D resultVector, Matrix m, Vector2D v) {
		resultVector.x = m.m[0] * v.x + m.m[4] * v.y + m.m[8] * 1;
		resultVector.y = m.m[1] * v.x + m.m[5] * v.y + m.m[9] * 1;

		return resultVector;
	}

	public static Vector3D multiply(Vector3D resultVector, Matrix m, Vector3D v) {
		resultVector.x = m.m[0] * v.x + m.m[4] * v.y + m.m[8] * 1;
		resultVector.y = m.m[1] * v.x + m.m[5] * v.y + m.m[9] * 1;
		resultVector.z = m.m[2] * v.x + m.m[6] * v.y + m.m[10] * 1;

		return resultVector;
	}

	public static Matrix multiply(Matrix resultMatrix, Matrix m1, Matrix m2) {
		android.opengl.Matrix.multiplyMM(resultMatrix.m, 0, m1.m, 0, m2.m, 0);
		return resultMatrix;
	}

	public int hashCode() {
		int hash = 13;

		for (int i = 0; i < 16; i++)
			hash += 29 * hash + Float.floatToIntBits(m[i]);

		return hash;
	}

	@Override
	public Matrix clone() {
		try {
			return (Matrix) super.clone();
		} catch (CloneNotSupportedException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		try {
			Matrix m = (Matrix) o;

			boolean isEqual = true;
			for (int i = 0; i < 16; i++) {
				if (Float.compare(m.m[i], this.m[i]) != 0)
					isEqual = false;
			}

			return isEqual ? true : false;

		} catch (ClassCastException e) {
			Log.e(TAG, e.getMessage());
		}

		return false;
	}

	public String toString() {
		String out = "(";
		for (int i = 0; i < 16; i++)
			out += m[i] + ",";

		out += ")";

		return out;
	}

	public float[] toFloatArray() {
		return m;
	}

}
