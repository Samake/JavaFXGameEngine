package com.toxicfrog.utils;

public class Vector2D {

	public double x;
	public double y;
	
	public Vector2D() {
		x = 0.0;
		y = 0.0;
	}
	
	public Vector2D(Vector2D vector) {
		x = vector.x;
		y = vector.y;
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void clear() {
		x = 0.0;
		y = 0.0;
	}
	
	public void add(Vector2D vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void sub(Vector2D vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void mul(Vector2D vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
	public void mul(double x, double y) {
		this.x *= x;
		this.y *= y;
	}
	
	public void div(Vector2D vector) {
		this.x /= vector.x;
		this.y /= vector.y;
	}
	
	public void div(double x, double y) {
		this.x /= x;
		this.y /= y;
	}
	
	public void invert() {
		this.x = -x;
		this.y = -y;
	}
}
