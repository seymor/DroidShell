package org.droidshell.math;

import java.util.Random;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class MathHelper {
	
	/*
	 * Static member variables
	 */
	
	public static final float EPSILON = 0.0000001f;
	public static final float PI = (float) Math.PI;
	public static final float E = (float) Math.E;
	
	public static final float D_TO_R = PI / 180.0f;
	public static final float R_TO_D = 180.0f / PI;
	
	/*
	 * Public methods
	 */
	
	public static final int generateRandomInteger(int minInt, int maxInt) {
		return new Random().nextInt(maxInt - minInt) + minInt;
	}
	
	public static final float generateRandomFloat(float minFloat, float maxFloat) {
		return new Random().nextFloat() * (maxFloat - minFloat) + minFloat;
	}
	
	public static final float sqrt(float f) {
		return (float) Math.sqrt(f);
	}
	
	public static final float pow(float number, float exponent) {
		return (float) Math.pow(number, exponent);
	}
	
	public static final float sin(float f) {
		return (float) Math.sin(f);
	}
	
	public static final float asin(float f) {
		return (float) Math.asin(f);
	}
	
	public static final float cos(float f) {
		return (float) Math.cos(f);
	}
	
	public static final float acos(float f) {
		return (float) Math.acos(f);
	}
	
	public static final float tan(float f) {
		return (float) Math.tan(f);
	}
	
	public static final float atan(float f) {
		return (float) Math.atan(f);
	}
	
	public static final float atan2(float y, float x) {
		return (float) Math.atan2(y,x);
	}
	
	public static final float radianToDegree(float radian) {
		return radian * R_TO_D;
	}
	
	public static final float degreeToRadian(float degree) {
		return degree * D_TO_R;
	}
	

}
