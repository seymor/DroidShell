package org.droidshell.lang.math;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Color implements Cloneable {

	private static final String TAG = Color.class.getName();

	/*
	 * Static final variables
	 */

	public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
	public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 1.0f);
	public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f, 1.0f);
	public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);
	public static final Color GREY = new Color(0.5f, 0.5f, 0.5f, 1.0f);

	public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f, 1.0f);
	public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f, 1.0f);
	public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f, 1.0f);

	public static final int NORM_TO_INT = 255;

	/*
	 * Member variables
	 */

	public float r;
	public float g;
	public float b;
	public float a;

	/*
	 * Constructors
	 */

	public Color() {
		r = 0;
		g = 0;
		b = 0;
		a = 1.0f;
	}

	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1.0f;
	}

	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public Color(Color rgba) {
		r = rgba.r;
		g = rgba.g;
		b = rgba.b;
		a = rgba.a;
	}

	/*
	 * Getters and Setters
	 */

	/*
	 * Public methods
	 */

	public Color set(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		return this;
	}

	public Color setAll(float rgb, float a) {
		this.r = rgb;
		this.g = rgb;
		this.b = rgb;
		this.a = a;
		return this;
	}

	public Color add(Color rgba) {
		r += rgba.r;
		g += rgba.g;
		b += rgba.b;
		a += rgba.a;
		return this;
	}

	public Color addN(Color rgba) {
		return new Color(r + rgba.r, g * rgba.g, b * rgba.b, a * rgba.a);
	}

	public Color multiply(float s) {
		r *= s;
		g *= s;
		b *= s;
		return this;
	}

	public Color multiplyN(float s) {
		return new Color(r * s, g * s, b * s);
	}

	public Color multiply(Color rgba) {
		r *= rgba.r;
		g *= rgba.g;
		b *= rgba.b;
		a *= rgba.a;
		return this;
	}

	public int hashCode() {
		int hash = 13;

		hash += 29 * hash + Float.floatToIntBits(r);
		hash += 29 * hash + Float.floatToIntBits(g);
		hash += 29 * hash + Float.floatToIntBits(b);
		hash += 29 * hash + Float.floatToIntBits(a);
		return hash;
	}

	@Override
	public Color clone() {
		try {
			return (Color) super.clone();
		} catch (CloneNotSupportedException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		try {
			Color rgba = (Color) o;

			if (Float.compare(rgba.r, r) == 0
					&& Float.compare(rgba.g, g) == 0
					&& Float.compare(rgba.b, b) == 0
					&& Float.compare(rgba.a, a) == 0)
				return true;
			else
				return false;

		} catch (ClassCastException e) {
			Log.e(TAG, e.getMessage());
		}

		return false;
	}

	public static final float intToNorm(int intValue256) {
		return intValue256 / NORM_TO_INT;
	}

	public static final int normToInt(float floatValue) {
		return (int) floatValue * NORM_TO_INT;
	}

	public String toString() {
		return "(" + r + "," + g + "," + b + "," + a + ")";
	}

	public float[] toFloatArray() {
		return new float[] { r, g, b, a };
	}

}
