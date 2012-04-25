package org.droidshell.lang.math;

import java.util.Random;

import android.util.FloatMath;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Math {

	private static final Random randomGenerator = new Random();

	public static final float EPSILON = 0.0000001f;
	public static final float INFINITE = Float.MAX_VALUE;
	public static final float PI = (float) java.lang.Math.PI;
	public static final float E = (float) java.lang.Math.E;

	public static final float D_TO_R = PI / 180.0f;
	public static final float R_TO_D = 180.0f / PI;

	/**
	 * Returns a random integer between the given integers
	 * 
	 * @param minInt
	 *            the minimum of the random integer
	 * @param maxInt
	 *            the maximum of the random integer
	 */

	public static final int generateRandomInteger(int minInt, int maxInt) {
		return randomGenerator.nextInt(maxInt - minInt) + minInt;
	}

	/**
	 * Returns a random float between the given floats
	 * 
	 * @param minFloat
	 *            the minimum of the random float
	 * @param maxInt
	 *            the maximum of the random float
	 */

	public static final float generateRandomFloat(float minFloat, float maxFloat) {
		return randomGenerator.nextFloat() * (maxFloat - minFloat) + minFloat;
	}

	/**
	 * Returns the closest float approximation of the square root of the
	 * argument
	 * 
	 * @param f
	 *            the value whose square root has to be computed
	 */

	public static final float sqrt(float f) {
		return FloatMath.sqrt(f);
	}

	/**
	 * Returns the closest float approximation of the result of raising number
	 * to the power of exponent
	 * 
	 * @param number
	 *            the base of the operation
	 * @param exponent
	 *            the exponent of the operation
	 */

	public static final float pow(float number, float exponent) {
		return (float) java.lang.Math.pow(number, exponent);
	}

	/**
	 * Returns the closest float approximation of the sine of the argument
	 * 
	 * @param f
	 *            the angle whose sin has to be computed, in radians
	 */

	public static final float sin(float f) {
		return FloatMath.sin(f);
	}

	/**
	 * Returns the closest float approximation of the arc sine of the argument
	 * within the range [-pi/2..pi/2]
	 * 
	 * @param f
	 *            the value whose arc sine has to be computed
	 */

	public static final float asin(float f) {
		return (float) java.lang.Math.asin(f);
	}

	/**
	 * Returns the closest float approximation of the cosine of the argument
	 * 
	 * @param f
	 *            the angle whose cosine has to be computed, in radians
	 */

	public static final float cos(float f) {
		return FloatMath.cos(f);
	}

	/**
	 * Returns the closest float approximation of the arc cosine of the argument
	 * within the range [0..pi]
	 * 
	 * @param f
	 *            the value whose arc cosine has to be computed
	 */

	public static final float acos(float f) {
		return (float) java.lang.Math.acos(f);
	}

	/**
	 * Returns the closest float approximation of the tangent of the argument
	 * 
	 * @param f
	 *            the angle whose tangent has to be computed, in radians
	 */

	public static final float tan(float f) {
		return (float) java.lang.Math.tan(f);
	}

	/**
	 * Returns the closest float approximation of the arc tangent of the
	 * argument within the range [-pi/2..pi/2]
	 * 
	 * @param f
	 *            the value whose arc tangent has to be computed
	 */

	public static final float atan(float f) {
		return (float) java.lang.Math.atan(f);
	}

	/**
	 * Returns the closest float approximation of the arc tangent of y/x within
	 * the range [-pi..pi]
	 * 
	 * @param y
	 *            the numerator of the value whose arc tangent has to be
	 *            computed
	 * @param x
	 *            the denominator of the value whose arc tangent has to be
	 *            computed
	 */

	public static final float atan2(float y, float x) {
		return (float) java.lang.Math.atan2(y, x);
	}

	/**
	 * Returns the float value in degrees of the argument
	 * 
	 * @param radian
	 *            the angle has to be computed, in radians
	 */

	public static final float radianToDegree(float radian) {
		return radian * R_TO_D;
	}

	/**
	 * Returns the float value in radians of the argument
	 * 
	 * @param degree
	 *            the angle has to be computed, in degrees
	 */

	public static final float degreeToRadian(float degree) {
		return degree * D_TO_R;
	}

	/**
	 * Returns the absolute value of the argument
	 * 
	 * @param f
	 *            the value whose absolute value has to be computed
	 */

	public static final float abs(float f) {
		return java.lang.Math.abs(f);
	}

}
