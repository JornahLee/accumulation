package com.jornah.util;


import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutDateAndTime {
	
	//String to Date
	//Date to formate String
	@Test
	public void diffBetweentSqlDtAndUtlDt() throws ParseException {
		Date utilDate=new Date();
		java.sql.Date sqlDate=new java.sql.Date(5l);
		System.out.println("util:"+utilDate.toString());
		System.out.println("sql:"+sqlDate.toString());
		SimpleDateFormat formate=new SimpleDateFormat("HH点mm分ss秒");
//		String formateDt=formate.format(utilDate);
		Date formateDt=formate.parse("0点14分11秒");
		System.out.println("formatDt:"+formate.format(formateDt));
	}
	@Test
	public void test() throws ParseException {
	    StringBuilder sb=new StringBuilder();
	    sb.append("");
	    DateFormat df=new SimpleDateFormat("hhmm");
	    Date fromTime=df.parse("2200");
	    System.out.println(fromTime);
        Date toTime;
	}


}
