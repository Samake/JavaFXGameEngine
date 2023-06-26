package com.toxicfrog.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");

	public static void print(String text) {
		Date date = new Date();

		String message = dateFormat.format(date) + " || " + text;
		System.out.println(message);
	}
}
