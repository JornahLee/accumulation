package com.jornah.usualclazz;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.jornah.pojo.UserDO;
import org.junit.Test;

import com.jornah.javabase.aboutextend.Animal;
import com.jornah.javabase.aboutextend.Cat;
import org.springframework.util.StringUtils;

//import java.sql.Date;

public class Others {
	public static void main(String args[]) throws ParseException {
		Date date=Date.valueOf("2019-1-1");
//		Date date=new Date();
//		DateFormat formate=new SimpleDateFormat("yyyy-mm-dd,hh:MM:ss");
//		Date date = formate.parse("2019-1-1,1:1:1");
		System.out.println(date);
	}
	@Test
	public void test1() {
		int i=5;
//		6
//		18+5		
		int s=i++ + ++i + i-- + --i;
		System.out.println(s);
//		System.out.println(s);
	}
	@Test
	public void test2() {
		int num[]= {1,2,3};
		System.out.println(num.toString());
//		System.out.println(s);
	}
	@Test
    public void test3() {
        Integer i1=200;
        Integer i2=200;
        System.out.println(i1==i2);
//        b=b+1;
    }
	@Test
	public void test4() {
	    int i=0;
	    int j=i++;
	    System.out.println("i++: result :"+j);
	    i=0;
	    j=++i;
	    System.out.println("i++: result :"+j);
	}
	
	@Test
	public void test5() {
	    int i=129;
	    byte b=(byte)i;
	    System.out.println("b="+b);
	    
	    double d=1.1d;
	    float f=(float)d;
	    System.out.println("f="+f);
	    
	}
	@Test
	public void test6() {
	    Animal animal=(Animal)new Cat();
	    Cat cat=(Cat)animal;
//	    animal.setAge(1);
	    System.out.println("Age:"+animal.getAge());
	    System.out.println("Animal static count:"+Animal.count);
	    System.out.println("Cat static count:"+Cat.count);
	    System.out.println("animal static count:"+animal.count);
        System.out.println("cat static count:"+cat.count);
	}
	@Test
	public void test7() {
	    List<String> list=new ArrayList();
	    list.add(null);
	    list.add(null);
	}
	@Test
	public void test8() {
	   String str="OMCSNA101Q\r\n" + 
	           "OPO588101Q\r\n" + 
	           "OPOBND588Q\r\n" + 
	           "OMEMNO101R\r\n" + 
	           "OMJSHC101R\r\n" + 
	           "OPORPC101R\r\n" + 
	           "";
	   StringTokenizer st=new StringTokenizer(str);
	   ArrayList<String> list=new ArrayList<String>();
	   while(st.hasMoreElements()) {
	       String element = st.nextElement().toString();
	       list.add(element);
	   }
	   for(String str1:list) {
	       System.out.println(str1);
	   }
	   Collections.sort(list);
	   System.out.println("-----after sorting-----");
       for(String str1:list) {
           System.out.println(str1);
       }
	}
	@Test
	public void test9() {
		int a=58062;
		System.out.println("--licg---   (short)a: " + (short)a + "-----");
		System.out.println("--licg---   : " + Short.MAX_VALUE + "-----");
	}

	public boolean isDefaultValueOrNull(int value) {
		if (value == -9999 || value == -1) {
			return true;
		}
		return false;
	}
	public boolean isDefaultValueOrNull(String value){
		if(StringUtils.isEmpty(value)){
			return true;
		}
		return false;
	}

	@Test
	public void test10(){
		String sql="select * from op_work_ord where no=? and data = ? and id =?";
		String[] params = new String[]{"canshu1","canshu2","123"};
		System.out.println("-------"+UpdateSql(sql,params));
	}
	public static String UpdateSql(String sqlString, String[] params) {
		//YYYY-MM-DD HH24:MI:SS.
		String newSqlString = "";
		if(params != null && sqlString != null) {
			sqlString = sqlString.replaceAll("YYYY-MM-DD HH24:MI:SS.\\?", "YYYY-MM-DD HH24:MI:SS.<");
			int lenPara = params.length;
			String[] split = sqlString.split("\\?");
			int lenSql = split.length;

			for(int i = 0; i < lenPara; i++) {
				split[i] = split[i] + " '" + params[i] + "' ";
			}
			for(int i = 0; i < lenSql; i++) {
				newSqlString = newSqlString + split[i];
			}
			newSqlString = newSqlString.replaceAll("YYYY-MM-DD HH24:MI:SS.<", "YYYY-MM-DD HH24:MI:SS.\\?");
		}

		return newSqlString;
	}
	

}
