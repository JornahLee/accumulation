package com.jornah;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	
	/*
	 * import jar: 
	 * 1.A portal Interface slf4j-api.jar
	 * 2.portalInterface-to-BasementInterface.jar
	 *   slf4f-to-commons-logging.jar
	 * 3.BasementImplement:slf4j-jdk1.4.jar
	 */
	public static void main(String args[]) {
		Logger logger=LoggerFactory.getLogger(Log.class);
		logger.info("logeer info");
	}

}
