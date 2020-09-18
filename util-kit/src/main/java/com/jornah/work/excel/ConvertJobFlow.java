package com.jornah.work.excel;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

public class ConvertJobFlow {
    ArrayList<ArrayList<Object>> readExcelRes;
    ArrayList<ArrayList<Object>> writeFileRes;

    private static final int READ_JOB_ID_INDEX = 0;
    private static final int READ_SUB_JOB_INDEX = 1;
    private static final int READ_RUN_TIMES_INDEX = 2;
    private static final int READ_REMARK_INDEX = 3;
    private static final int READ_JOB_STATUS_INDEX = 5;
    private static final int READ_INCONDITION_INDEX = 7;

    private static final int WRITE_OUT_STATUS_INDEX = 4;
    private static final int WRITE_JOB_ID_INDEX = 5;
    private static final int WRITE_DYAS_PREFIXJOBS_INDEX = 11;
    private static final int WRITE_START_END_TIME_INDEX = 12;
    private static final int WRITE_INTERVAL_INDEX = 13;
    private static final int WRITE_PREFIX_JOBS_INDEX = 14;
    
    private static final int ARR_DAYS_INDEX = 0;
    private static final int ARR_START_END_INDEX = 1;
    private static final int ARR_INTERVAL_INDEX = 2;

    public ConvertJobFlow(ArrayList<ArrayList<Object>> readExcelRes, ArrayList<ArrayList<Object>> writeFileRes) {
        this.readExcelRes = readExcelRes;
        this.writeFileRes = writeFileRes;
    }

    public  void convert() throws Exception {
      
        int startRowIndex = 1;
        int endRowIndex = -1;
        ArrayList<Object> readCurRow;
        String readJobId;
        char jobType=' ';
        String inCondition;
        String jobStatus;
        String[] separatedRunTime;
        if(endRowIndex<0) {
            endRowIndex=this.readExcelRes.size()-1;
        }
        for (int readCurRowIndex = startRowIndex; readCurRowIndex <= endRowIndex; readCurRowIndex++) {
            readCurRow = this.getReadRow(readCurRowIndex);
            readJobId = readCurRow.get(READ_JOB_ID_INDEX).toString();
            try {
                
                jobType=readJobId.charAt(readJobId.length()-1);
            }catch(Exception e){
                System.out.println(readJobId);
                
            }
            // find in col subjobs
//            prefixJobs = convertFlow.getPrefixJobs(readJobId);
            separatedRunTime = this.getSeparatedRunTime(readCurRow);
            
            if(separatedRunTime==null) {
                separatedRunTime=new String[] {"","",""};
            }
            inCondition=readCurRow.get(READ_INCONDITION_INDEX).toString();
            jobStatus=readCurRow.get(READ_JOB_STATUS_INDEX).toString();
            /**
             * R job need output start and end time 
             * 
             */
            int startIndex=21;
            this.outputToJPS(readJobId,jobType,separatedRunTime,inCondition,jobStatus,startIndex);
//            System.out.println("jobType:"+jobType);
//            System.out.println("readJobId:"+readJobId);
////            System.out.println("prefixJobs:"+prefixJobs);
//            System.out.println("--separatedRunTime--");
//            for(String str:separatedRunTime) {
//                System.out.println("  "+str);
//            }
        }

    }

    private void outputToJPS(String readJobId, char jobType, String[] separatedRunTime,String inCondition,String jobStatus,int startIndex) {
        if(this.writeFileRes.size()<=startIndex) {
            
        }
        StringBuilder outDaysPrefixJobs=new StringBuilder("");
        StringBuilder nonRJob=new StringBuilder("");
        ArrayList<Object> newRow =  new ArrayList<Object>();
        while(newRow.size()<=15) {
            newRow.add("");
        }
        newRow.set(WRITE_JOB_ID_INDEX, readJobId);
        //output WRITE_DYAS_PREFIXJOBS_INDEX
        String prefix="";
        switch(jobType){
            case 'Q': prefix="Quarterly: ";break;
            case 'M': prefix="Monthly: ";break;
            case 'W': prefix="Weekly: ";break;
            case 'D': prefix="Daily: ";break;
            case 'R': prefix="Recurly: ";break;
            case 'O': prefix="Adhoc Job: ";break;
            default:prefix="----";
        }
        outDaysPrefixJobs.append(prefix);
        outDaysPrefixJobs.append(separatedRunTime[ARR_DAYS_INDEX]);
        if(inCondition!=null&&!"".equals(inCondition.trim())) {
            outDaysPrefixJobs.append("\n After "+inCondition);
        }
        if(jobType=='R') {
            newRow.set(WRITE_DYAS_PREFIXJOBS_INDEX,outDaysPrefixJobs.toString());
            newRow.set(WRITE_START_END_TIME_INDEX, separatedRunTime[ARR_START_END_INDEX]);
            newRow.set(WRITE_INTERVAL_INDEX, separatedRunTime[ARR_INTERVAL_INDEX]);
        }else {
            if(separatedRunTime!=null) {
                nonRJob.append(prefix);
                nonRJob.append(separatedRunTime[0]);
                if(separatedRunTime[1]!=null&&!"".equals(separatedRunTime[1].trim())) {
                    nonRJob.append(" @ ").append(separatedRunTime[1]);
                }
                if(inCondition!=null&&!"".equals(inCondition.trim())) {
                    nonRJob.append("\n After "+inCondition);
                }
            }
            newRow.set(WRITE_DYAS_PREFIXJOBS_INDEX,nonRJob.toString());
        }
        
        
        newRow.set(WRITE_PREFIX_JOBS_INDEX, inCondition);
//        WRITE_OUT_STATUS_INDEX
        if(jobStatus!=null&&!"#N/A".equals(jobStatus.trim())) {
            newRow.set(WRITE_OUT_STATUS_INDEX, jobStatus);
        }
        this.writeFileRes.add(newRow);
        
//        private static final int WRITE_JOB_ID_INDEX = 5;
//        private static final int WRITE_DYAS_PREFIXJOBS_INDEX = 11;
//        private static final int WRITE_START_END_TIME_INDEX = 12;
//        private static final int WRITE_INTERVAL_INDEX = 13;
//        private static final int WRITE_PREFIX_JOBS_INDEX = 14;
//        
//        private static final int ARR_DAYS_INDEX = 0;
//        private static final int ARR_START_END_INDEX = 1;
//        private static final int ARR_INTERVAL_INDEX = 2;
        
        
    }
  
    private String[] getSeparatedRunTime(ArrayList<Object> readCurRow) {
        String runTime = readCurRow.get(READ_RUN_TIMES_INDEX).toString().replace("(", "");
        runTime = runTime.replace(")", "");
        if (runTime == null || "".equals(runTime.trim())) {
            return null;
        }
        String res[]=new String[] {"","",""};
        // separate time
        /**
         * 1. if have , then get days 2. if have , then get runtime, '@' 3. if
         * have , then get interval, ','
         */
        String[] strings = runTime.split("@");
        for(String str:strings) {
            if(str.contains("ly")) {
                //days
                res[0]=str;
                //remove weekly and monthly
                res[0]=res[0].replace("Monthly:", "");
                res[0]=res[0].replace("Weekly:", "");
                res[0]=res[0].trim();
                continue;
            }
            if(str.contains(":")) {
                //from to time
                res[1]=str;
                res[1]=res[1].trim();
            }
            if(str.contains("mins")||str.contains("hours")) {
                res[2]=str;
                res[2]=res[2].trim();
                
            }
        }
        return res;
    }

    private String getPrefixJobs(String readJobId) {
        StringBuilder sb = new StringBuilder();
        int[] desCols = { READ_SUB_JOB_INDEX };
        int[][] positions = ExcelUtil.findString(this.readExcelRes, readJobId, desCols);
        // get prefix job id by position
        String jobId = null;
        if (positions != null) {
            for (int i = 0; i < positions.length; i++) {
                for (int j = 0; j < positions[i].length; j++) {
                    if (positions[i][j] == READ_SUB_JOB_INDEX) {
                        // get the jobid by positions rowIndex(positions[i][0])
                        jobId = this.getReadRow(positions[i][0]).toString();
                        sb.append(jobId).append(",");
                    }
                }
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String file1 = "D:\\myWinBat\\excelBatInput\\OPOMjobs.xlsx";
        String file2 = "D:\\workspace\\workRecordsList\\getFlowAndTime\\New.xlsx";
        File readFile = new File(file1);
        File writeFile = new File(file2);
        ArrayList<ArrayList<Object>> readExcelRes = ExcelUtil.readExcel(readFile);
        ArrayList<ArrayList<Object>> writeExcelRes = ExcelUtil.readExcel(writeFile,0);
        ConvertJobFlow convertFlow = new ConvertJobFlow(readExcelRes, writeExcelRes);
        String logFileNmae = "D:\\workspace\\workRecordsList\\getFlowAndTime\\log.txt";
        File logFile = new File(logFileNmae);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        PrintStream ps = new PrintStream(logFile);
        System.setOut(ps);
        System.setErr(ps);
        /**
         * total 3 steps: 1. parse job type 2. parse runtime and output 3. find
         * parent jobs
         */
        convertFlow.convert();
        ExcelUtil.writeExcel(writeExcelRes, file2, 0);
    }

    private String[] parseRunTimeFromRead() {
        return null;
    }

    public static int parseIndex(String colStr) {
        System.out.println("F:" + ('f' - 'a'));
        System.out.println("L:" + ('l' - 'a'));
        System.out.println("M:" + ('m' - 'a'));
        System.out.println("N:" + ('n' - 'a'));
        System.out.println("O:" + ('o' - 'a'));
        return 0;
    }

    public ArrayList<Object> getReadRow(int rowIndex) {
        return this.readExcelRes.get(rowIndex);
    }

    public ArrayList<Object> getWriteRow(int rowIndex) {
        return this.writeFileRes.get(rowIndex);
    }

}
