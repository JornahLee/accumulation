package com.jornah.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author LiCong
 *
 * how to use java regex
 * before calling matcher.goup(), you have to call matcher.find()
 * otherwise you can not get matched result
 *
 */
public class RegexDemo {
    public static void main(String args[]) {
        new RegexDemo().testFind();
    }
    public void testReplace() {
        String regex="#.+";
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=null;
        String line=" OM_asds ,a.,.,,..,.asdasdasd OM_1123\n#askjsdasd";
        matcher=pattern.matcher(line);
        String replaced = matcher.replaceAll("");
        System.out.println("replaced:"+replaced);
    }
    public void testFind() {
//      String regex="OM";
      String regex="OMCSNA101Q";
      Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
      Matcher matcher=null;
      String line="OMCSNA101Q\r\n" + 
              "\r\n" + 
              "OP-OMCSNA101Q-OK  PREV  A  \r\n" + 
              "\r\n" + 
              "OP-OMCSNA101Q-OK  ODAT  A  \r\n" + 
              "\r\n" 
              ;
      matcher=pattern.matcher(line);
      while(matcher.find()) {
          System.out.println(matcher.group());
      }  
    }

}
