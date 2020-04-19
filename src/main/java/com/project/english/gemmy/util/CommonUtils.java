package com.project.english.gemmy.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CommonUtils {

	public static Date convertTime(long time) {
		Date date = new Date(time);
		return date;
	}

	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
