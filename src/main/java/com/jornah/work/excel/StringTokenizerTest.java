package com.jornah.work.excel;

import java.util.StringTokenizer;

public class StringTokenizerTest {
    
    public static void main(String[] args) {
        String str1="( Monthly: ALL ) @ ( 08:15 ~ 06:00 ) @ 15 mins";
        String[] strings = str1.split("@");
        String [] res=new String[] {"","",""};
        for(String str:strings) {
            if(str.contains("ly")) {
                //days
                res[0]=str;
                continue;
            }
            if(str.contains(":")) {
                //from to time
                res[1]=str;
            }
            if(str.contains("mins")||str.contains("hours")) {
                res[2]=str;
                
            }
           
        }
        for(String str2:res) {
            System.out.println(str2);
        }
    }
    private String cutInCondition(String inCondition) {
        StringTokenizer sti=new StringTokenizer(inCondition);
        StringBuilder sb=new StringBuilder();
        while(sti.hasMoreElements()) {
             String element = sti.nextElement().toString();
             if(element.length()>=10) {
                 int index1 = element.indexOf("-");
                 element=element.substring(index1+1,element.length());
                 element=element.substring(0,element.length()-3);
             }
             if(element.length()>=8) {
                 sb.append(element).append(" ");
             }
//             System.out.println(element);
        }
        if(sb.length()>=1) {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

}
