package com.jornah.work.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestExcelUtil {
    private static String InifileName;
    private static String xlsxfileName="C:\\Users\\P1323153\\Desktop\\OM_JOBs.xlsx";
    private static File xlsxfile = new File(xlsxfileName);
    private static List<String> allTableList=new ArrayList<String>();
    private static List<String> matchedButNotTable=new ArrayList<String>();
    private static List<String> fileNotFindName=new ArrayList<String>();
    private static String className;
    public static void setallTableList(String allTable,String separator){
        String[] tables = allTable.split(separator);
        for(String str:tables) {
            if(str!=null&&!"".equals(str)) {
                allTableList.add(str);
            }
        }
    }
    private static Set<String> getMatchedResult(String jobId) throws IOException {
        StringBuilder iniName=new StringBuilder();
        File iniFile=null;
        String iniPrefix="D:\\workspace\\EcomBatch\\src\\main\\resources\\crm_config\\ta_Batch_";
        FileReader fr=null;
        BufferedReader bfr=null;
        String regex="\\bom_\\w+\\b";
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=null;
        Set<String> set=new HashSet<String>();
        iniName.append(iniPrefix).append(jobId).append(".ini");
        InifileName=iniName.toString();
        iniFile=new File(InifileName);
        //clear iniName
        iniName.setLength(0);
        try {
            fr=new FileReader(iniFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileNotFindName.add(InifileName);
            return null;
        }
        bfr=new BufferedReader(fr);
        String line =null;
        String replacedLine=null;
        String regex2="#.+";
        Pattern pattern2=Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
        Matcher matcher2=null;
        
        String regex3="\\bsingtel\\.ecom\\.op.+\\b";
        Pattern pattern3=Pattern.compile(regex3,Pattern.CASE_INSENSITIVE);
        Matcher matcher3=null;
//        String claaName=line.indexOf("123");
        while((line=bfr.readLine())!=null) {
         // remove startWith #
            matcher2=pattern2.matcher(line);
            replacedLine = matcher2.replaceAll("");
            //get sys class name
            matcher3=pattern3.matcher(line);
            if(matcher3.find()) {
                className = matcher3.group();
            }
           
            // matche table
            matcher=pattern.matcher(replacedLine);
            while(matcher.find()) {
                set.add(matcher.group());
            }
        }
            

//        set.remove("OM_Batch_Job");
        return set;
        
    }
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(xlsxfile,2);
        Set<String> matchedResult=null;
        String allTableName="OM_ACT_ATTRIB|OM_ACT_DEPEND|OM_ACT_GRP|OM_ACT_GRP_ROUTING|OM_ACT_GRP_ROUTING_DETAILS|OM_ACT_INST|OM_ACT_MASTER|OM_ATTACHMENT_HDR|OM_AUTO_ACT_CLASS_MASTER|OM_DEF_SUB|OM_EQUIP_CONTRACT_DETAILS|OM_GRP|OM_GRP_PERS_ALLOCPROD|OM_GRP_PERS_ALLOCPROD_DTL|OM_GRP_PERS_ALLOCTIME|OM_GRP_PERS_NOTIFY|OM_GRP_PERS_NOTIFY_EMAIL|OM_INST_EQUIP_DETAILS|OM_RELATIVE_TIME|OM_SURVEY_PLAN_DETAILS|OM_USER_PERS_ALLOC|OM_USER_PERS_ALLOC_DTL|OM_USER_PERS_INBOX|OM_USER_PERS_NOTIFY|OM_USER_PERS_NOTIFY_EMAIL|OM_USER_SUBSTITUTE|OM_USR_GRP_MAP|OM_WORK_ORD_MARKER|OM_ATTACHMENT_DATA|OM_EXT_TXN_LOG|OM_ACT_INST_MNP|OM_SAB_INSTALLER_MAP|OM_SAB_COF_MAP|OM_SAB_KIV_MAP|OM_BATCH_ORD|OM_ACT_GRP_EXT_ROUTING|OM_ACT_DTLS_DLY_TRACKING|OM_MUX_MAINT|OM_STB_TY_DISCREPANCY|OM_TRIAL_CHARGEABLE_COMP_MAP|OM_FNP_ACT|OM_ALERT_EMAIL|OM_CROSS_CARRIAGE_RQL|OM_DQMS_TXN|OM_CROSS_CARRIAGE_SQL|OM_DQMS_BDR|OM_CI_SVC_NO_EVENT_HOLDING|OM_EVENT_CI_INFO|OM_QUEUE_MONITOR|OM_PPO_MGMT_RPT_SRC|OM_ACT_REROUTE";
        setallTableList(allTableName,"\\|");
        try {
            for(int i = 228 ;i <=281 ;i++){
                //     Traversing row from left to right
//            for(int j = 0;j<result.get(i).size(); j++){
//                System.out.println(i+"row "+j+"col  "+ result.get(i).get(j).toString());
//            }
                String jobId=result.get(i).get(0).toString();
                System.out.println("jobId:"+jobId);
                matchedResult= getMatchedResult(jobId);
                Set<String> tableFromCode=getTableFromClass(className);
                //matchedButNotTable
                System.out.println("----tableFromCode---------");
                System.out.println(tableFromCode);
                if(tableFromCode!=null) {
                    
                    matchedResult.addAll(tableFromCode);
                }
                if(matchedResult==null) {
                    continue;
                }
                ArrayList<Object> rowList = result.get(i);
                StringBuilder matchedTables=new StringBuilder();
                for(String str:matchedResult) {
                    if(containsTable(str)) {
                        matchedTables.append(str);
                        matchedTables.append("\r\n");
                    }else {
                        matchedButNotTable.add(str);
                    }
                    
                }
                Object obj=matchedTables.toString();
                rowList.set(3, obj);
//                for(Object o:result.get(i)) {
//                    System.out.println(o);
//                }
                System.out.println("---output table------");
                System.out.println(obj);
            }
            //output fileNotFindList
            System.out.println("----fileNotFindName---------");
            System.out.println(fileNotFindName);

           
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        
        ExcelUtil.writeExcel(result, xlsxfileName,2);
//        ExcelUtil.writeExcel(result,"F:/excel/bb.xls");
    }
    
    private static boolean containsTable(String tableName) {
        for(String str:allTableList) {
            if(str.equalsIgnoreCase(tableName)) {
                return true;
            }
        }
        return false;
        
    }
    public static Set<String> getTableFromClass(String className) throws IOException {
        Set<String> tableFromClass =new HashSet<String>();
        
        String fileName="D:\\workspace\\EcomBatch\\src\\main\\java\\"+className.replace('.', '\\')+".java";
        File file=new File(fileName);
        FileReader fr=null;
        try {
            fr=  new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileNotFindName.add(className);
            return null;
        }
        BufferedReader  bfr=new BufferedReader(fr);
        String regex="\\bom_\\w+\\b";
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=null;
        String regex2="\\/\\/.+";
        Pattern pattern2=Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
        Matcher matcher2=null;
        String line=null;
        String replacedLine=null;
        while((line=bfr.readLine())!=null) {
              
            matcher2=pattern2.matcher(line);
            replacedLine = matcher2.replaceAll("");
            // matche table
            matcher=pattern.matcher(replacedLine);
            while(matcher.find()) {
               tableFromClass.add(matcher.group());
            }
        }
        
        return tableFromClass;
        
    }
    @Test
    public void test1() throws Exception {
        getTableFromClass("singtel.ecom.op.batch.obsda04.main");
    }
    //col 0:job id
    //col 3:table
    //sheetindex1  row:87-97 
    //read col:0 write col:3
    /**
     * 开始循环第一行
     * 读取excel的jobid
     *通过jobid 读取ini 
     * 通过正则读取表名存入到set集合中
     * 一个jobini读取结束，输出set集合到col3
     * 清空set集合
     * 循环
     */
//    public static class MyArrayList extends ArrayList{
//        @Override
//        public boolean contains(Object o) {
//            return indexOf(o)>0;
//        }
//        @Override
//        public int indexOf(Object o) {
//            if (o == null) {
//                for (int i = 0; i < size; i++)
//                    if (elementData[i]==null)
//                        return i;
//            } else {
//                for (int i = 0; i < size; i++)
//                    if (o.equals(elementData[i]))
//                        return i;
//            }
//            return -1;
//        }
//    }
}
