package com.toxicfrog.utils;

import java.util.Random;

public class Utils {

	private static Random rand = new Random();
	
	public static String getFileUrl() {
		 String path = System.getProperty("user.dir");
		 path = path.replace("\\", "/");
		 path = "file:/" + path;
		 
		 return path;
	}
	
	public static String getFileUrlClean() {
		 String path = System.getProperty("user.dir");
		 path = path.replace("\\", "/");

		 return path;
	}
	
	public static int randomInteger(int min, int max) {
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static float randomFloat(float min, float max) {
	    float randomNum = rand.nextFloat((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static double computeAngleOfTwoPoints(double x1, double y1, double x2, double y2) {
		float angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));

	    if (angle < 0) {
	        angle += 360;
	    }

	    return angle;
	}
	
	public static double computeDistanceOfTwoPoints(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	public static Vector2D getPositionArroundVector(Vector2D position, double angle, double distance) {
        double angleRadians = Math.toRadians(angle);
  
        double posX = position.x + distance * Math.cos(angleRadians);
        double posY = position.y + distance * Math.sin(angleRadians);
        
        return new Vector2D(posX, posY);
	}
	
	public static double getAngleBetweenPoints(Vector2D vector1,  Vector2D vector2, double offset) {
		double angle = Math.toDegrees(Math.atan2(vector2.x - vector1.x, vector2.y - vector1.y)) - 90;
	    angle = (360 + offset - angle)%360;

        return angle;
	}
}
