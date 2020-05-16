package com.jornah.work.excel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindJobFlow {
    public static final int OPOM_JOB_ID_INDEX = 0;
    public static final int OPOM_SUB_JOB_INDEX = 1;
    public static final int OPOM_RUN_TIME_INDEX = 2;
    public static final int OPOM_REMARK_INDEX = 3;
    public static final int OPOM_JOB_STATUS_INDEX = 5;
    public static final int OPOM_IN_CONDITION_INDEX = 6;
    

    public static final int BATCH_JOB_ID_INDEX = 1;
    public static final int BATCH_IN_CONDITION_INDEX = 14;
    public static final int BATCH_OUT_CONDITION_INDEX = 15;

    public static final int BATCH_MONTH_INDEX = 10;
    public static final int BATCH_WEEK_INDEX = 11;
    public static final int BATCH_FROM_TIME_INDEX = 8;
    public static final int BATCH_TO_TIME_INDEX = 9;
    public static final int BATCH_INTETVAL_INDEX = 5;
    public static final String[] WEEK_ARR = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday" };

    // public static final int BATCH_JOB_ID_INDEX = 4;
    // public static final int BATCH_IN_CONDITION_INDEX = 98;
    // public static final int BATCH_OUT_CONDITION_INDEX = 99;

    // public static final int BATCH_MONTH_INDEX=32;
    // public static final int BATCH_WEEK_INDEX=33;
    // public static final int BATCH_FROM_TIME_INDEX=30;
    // public static final int BATCH_TO_TIME_INDEX=31;
    // public static final int BATCH_INTETVAL_TIME_INDEX=16;

    private String readFileName;
    private String writeFile;
    private DateFormat readDf = new SimpleDateFormat("HHmm");
    private DateFormat writeDf = new SimpleDateFormat("HH:mm");
    private Map<Integer, ArrayList<Object>> addNewRowMap = new HashMap<Integer, ArrayList<Object>>();
    private Runtime runtime;

    public FindJobFlow() {
    }

    public FindJobFlow(String readFileName, String writeFile) {
        this.readFileName = readFileName;
        this.writeFile = writeFile;
    }

    public static void main(String[] args) throws Exception {
        String file1 = "D:\\myWinBat\\excelBatInput\\EcomBatch.xlsx";
        String file2 = "D:\\myWinBat\\excelBatInput\\OPOMjobs.xlsx";
        FindJobFlow findJF = new FindJobFlow(file1, file2);
        File readFile = new File(findJF.getReadFileName());
        File writeFile = new File(findJF.getWriteFile());
        String logFileNmae = "D:\\myWinBat\\excelBatInput\\log.txt";
        File logFile = new File(logFileNmae);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        PrintStream ps = new PrintStream(logFile);
        System.setOut(ps);
        System.setErr(ps);
        // get rows in OPOMJobs.xlsx by iterator
        ArrayList<ArrayList<Object>> writeFileRes = ExcelUtil.readExcel(writeFile, 0);
        ArrayList<ArrayList<Object>> readExcelRes = ExcelUtil.readExcel(readFile, 0);
        int startRowIndex = 1;
        int endRowIndex = 1309;
        Runtime runtime = findJF.new Runtime(readExcelRes, writeFileRes, startRowIndex, -1);
        findJF.setRuntime(runtime);
//        findJF.sortOPOMJobId(writeFileRes);
//        findJF.outputDependencyAndRuntime(readExcelRes, writeFileRes, startRowIndex, -1);
//        findJF.addNewJobId(writeFileRes);
//        findJF.outputDependencyAndRuntime(readExcelRes, writeFileRes, startRowIndex, -1);
//        findJF.addNewJobId(writeFileRes);
//        // findJF.movSameSubTogether(writeFileRes, startRowIndex, -1);
//        findJF.outputDependencyAndRuntime(readExcelRes, writeFileRes, startRowIndex, -1);
//        findJF.addNewJobId(writeFileRes);
//
//        findJF.clearSubJob(writeFileRes);
//        findJF.outputDependencyAndRuntime(readExcelRes, writeFileRes, startRowIndex, -1);
//        findJF.outputMore(readExcelRes, writeFileRes, startRowIndex, -1);

         findJF.runtime.justOutputRunTime();

        ExcelUtil.writeExcel(writeFileRes, file2, 0);

        System.out.println("-------------------------------------------");
        for (Entry<Integer, ArrayList<Object>> entry : findJF.getAddNewRowMap().entrySet()) {
            System.out.println("rowNo:" + entry.getKey() + " jobId:" + entry.getValue().get(0));
        }

        // findJF.findAlone(writeFileRes,startRowIndex,-1);

        System.out.println("function main end");
    }

    public void sortOPOMJobId(ArrayList<ArrayList<Object>> fileRes) {
        OPOMRowComparator comparator = new OPOMRowComparator();
        ArrayList<Object> colNames = fileRes.get(0);
        fileRes.remove(0);
        Collections.sort(fileRes, comparator);
        fileRes.add(0, colNames);
    }

    public boolean movRow(ArrayList<ArrayList<Object>> fileRes, ArrayList<Object> movRow, int fromIndex, int toIndex) {
        if (toIndex > fromIndex) {
            // add and remove
            fileRes.add(toIndex, movRow);
            fileRes.remove(fromIndex);
        } else {
            // remove and add
            fileRes.remove(fromIndex);
            fileRes.add(toIndex, movRow);
        }
        return true;
    }

    public void clearSubJob(ArrayList<ArrayList<Object>> writeFileRes) {
        for (ArrayList<Object> row : writeFileRes) {
            row.set(OPOM_SUB_JOB_INDEX, "");
        }

    }
    public void outputMore(ArrayList<ArrayList<Object>> readExcelRes,
            ArrayList<ArrayList<Object>> writeFileRes, int startIndex, int endIndex) {
        // int startIndex = 1;//row no=2 in excel file
        // int endIndex = 210;
        if (endIndex < 0) {
            endIndex = writeFileRes.size() - 1;
        }
        String jobId = "";
        int[] batJobIdCol = { BATCH_JOB_ID_INDEX };
        try {
            // iterate OPOM.xlsx
            for (int opCurRow = startIndex; opCurRow <= endIndex; opCurRow++) {
                ArrayList<Object> curRow = writeFileRes.get(opCurRow);
                while(curRow.size()<=7) {
                    curRow.add("");
                }
                jobId = curRow.get(OPOM_JOB_ID_INDEX).toString();
                int[][] jobIdRes;
                if (!"".equals(jobId)) {
                    jobIdRes = ExcelUtil.findString(readExcelRes, jobId, batJobIdCol);
                    if (jobIdRes != null) {
                        this.runtime.outputRuntime(jobIdRes, opCurRow);
                        //get jobstatus into OPOMJobs.excel
                        ArrayList<Object> list = readExcelRes.get(jobIdRes[0][0]);
                        String jobStatus = list.get(BATCH_OUT_CONDITION_INDEX+1).toString();
                        String inCondition =list.get(BATCH_IN_CONDITION_INDEX).toString();
                        String condictionRes=this.cutInCondition(inCondition);
                        curRow.set(OPOM_JOB_STATUS_INDEX, jobStatus);
                        curRow.set(OPOM_IN_CONDITION_INDEX, inCondition);
                        curRow.set(OPOM_IN_CONDITION_INDEX+1, condictionRes);
//                        OPOM_IN_CONDITION_INDEX
                    } else {
                        String errMsg = "Error: " + jobId + " In EcomBatch.xlsx\n";
                        String remark = "";
                        if (jobIdRes == null) {
                            errMsg += "\t not found in jobId col\n";
                            remark += "not found in batch jobId col\n";
                        }
                        System.err.println(errMsg + "\n");
                        curRow.set(OPOM_REMARK_INDEX, remark);
                    }
                }

            }
        } catch (Exception e) {
            // do : skip this job id
            e.printStackTrace();
        }
    
    }
    public void outputDependencyAndRuntime(ArrayList<ArrayList<Object>> readExcelRes,
            ArrayList<ArrayList<Object>> writeFileRes, int startIndex, int endIndex) {
        // int startIndex = 1;//row no=2 in excel file
        // int endIndex = 210;
        if (endIndex < 0) {
            endIndex = writeFileRes.size() - 1;
        }
        String jobId = "";
        int[] inConditionCol = { BATCH_IN_CONDITION_INDEX };
        int[] batJobIdCol = { BATCH_JOB_ID_INDEX };
        try {
            // iterate OPOM.xlsx
            for (int opCurRow = startIndex; opCurRow <= endIndex; opCurRow++) {
                ArrayList<Object> curRow = writeFileRes.get(opCurRow);
                while(curRow.size()<=7) {
                    curRow.add("");
                }
                jobId = curRow.get(OPOM_JOB_ID_INDEX).toString();
                int[][] conditionRes;
                int[][] jobIdRes;
                if (!"".equals(jobId)) {
                    conditionRes = ExcelUtil.findString(readExcelRes, jobId, inConditionCol);
                    jobIdRes = ExcelUtil.findString(readExcelRes, jobId, batJobIdCol);
                    if (jobIdRes != null) {
                        if (conditionRes != null) {
                            this.updateSubJobAndMov(readExcelRes, writeFileRes, conditionRes, opCurRow);
                        }
                        this.runtime.outputRuntime(jobIdRes, opCurRow);
                        //get jobstatus into OPOMJobs.excel
                        ArrayList<Object> list = readExcelRes.get(jobIdRes[0][0]);
                        String jobStatus = list.get(BATCH_OUT_CONDITION_INDEX+1).toString();
                        String inCondition =list.get(BATCH_IN_CONDITION_INDEX).toString();
                        String condictionRes=this.cutInCondition(inCondition);
                        curRow.set(OPOM_JOB_STATUS_INDEX, jobStatus);
                        curRow.set(OPOM_IN_CONDITION_INDEX, inCondition);
                        curRow.set(OPOM_IN_CONDITION_INDEX+1, condictionRes);
//                        OPOM_IN_CONDITION_INDEX
                    } else {
                        String errMsg = "Error: " + jobId + " In EcomBatch.xlsx\n";
                        String remark = "";
                        if (jobIdRes == null) {
                            errMsg += "\t not found in jobId col\n";
                            remark += "not found in batch jobId col\n";
                        }
                        if (conditionRes == null) {
                            errMsg += "\t not found in batch condition col";
                            remark += "not found in condition";
                        }
                        System.err.println(errMsg + "\n");
                        curRow.set(OPOM_REMARK_INDEX, remark);
                    }
                }

            }
        } catch (Exception e) {
            // do : skip this job id
            e.printStackTrace();
        }
    }

    private String cutInCondition(String inCondition) {
        StringTokenizer sti=new StringTokenizer(inCondition);
        StringBuilder sb=new StringBuilder();
        while(sti.hasMoreElements()) {
             String element = sti.nextElement().toString();
             if(element.length()>10) {
                 element=element.replace("OP-","");
                 element=element.replace("-OK","");
                 element=element.replace("-ST","");
                 element=element.replace("_ST","");
                 element=element.replace("-WF","");
                 element=element.replace("-NF","");
             }
             if(element.startsWith("BI-")) {
                 if(element.length()>12) {
                     sb.append(element).append(" ");
                 }
                 continue;
             }
             if(element.length()>=10) {
                 sb.append(element).append(" ");
             }
//             System.out.println(element);
        }
        if(sb.length()>=1) {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    private void updateSubJobAndMov(ArrayList<ArrayList<Object>> readExcelRes,
            ArrayList<ArrayList<Object>> writeFileRes, int[][] arr, int opCurRow) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // find res h
                if (arr[i][j] == BATCH_IN_CONDITION_INDEX) {
                    // get JobId by col No from batch file;
                    String subJobId = readExcelRes.get(arr[i][0]).get(BATCH_JOB_ID_INDEX).toString();
                    // out into OPOM_SUB_JOB
                    ArrayList<Object> itrRow = writeFileRes.get(opCurRow);
                    String jbid = itrRow.get(OPOM_JOB_ID_INDEX).toString();
                    // output opom sub job
                    // get old sub job String in opom
                    String oldSubJobStr = itrRow.get(OPOM_SUB_JOB_INDEX).toString();
                    StringBuilder subJobCell = new StringBuilder("");
                    // avoiding dump
                    Set<String> set = new HashSet<String>();
                    StringTokenizer sToken = new StringTokenizer(oldSubJobStr);
                    while (sToken.hasMoreTokens()) {
                        String string = sToken.nextToken();
                        set.add(string);
                    }
                    int oldSetSize = set.size();
                    if (!"".equals(subJobId)) {
                        set.add(subJobId);
                    }
                    int newSetSize = set.size();
                    boolean setSizeChangedFlag = (newSetSize - oldSetSize) > 0;
                    int subJobCount = 0;
                    for (String str : set) {
                        if (subJobCount == 0) {
                            subJobCell.append(str);
                        } else {
                            subJobCell.append(" " + str);
                        }
                        subJobCount++;
                    }
                    itrRow.set(OPOM_SUB_JOB_INDEX, subJobCell.toString());
                    // mov sub job to be behind current job or add
                    int[] desCol = { OPOM_JOB_ID_INDEX };
                    if (setSizeChangedFlag) {
                        int[][] res = ExcelUtil.findString(writeFileRes, subJobId, desCol);

                        int[][] dumSubJob = ExcelUtil.findString(writeFileRes, subJobId, desCol);
                        ArrayList<Object> row = null;
                        if (res != null) {
                            // exist, so mov(del then add new row)
                            int fromIndex = res[0][0];
                            int toIndex = opCurRow + 1;
                            row = writeFileRes.get(res[0][0]);
                            movRow(writeFileRes, row, fromIndex, toIndex);

                        } else {
                            // not exist,so add new Row
                            // add by windows office
                            ArrayList<Object> newRow = new ArrayList<Object>();
                            newRow.add(OPOM_JOB_ID_INDEX, subJobId);
                            newRow.add(OPOM_SUB_JOB_INDEX, "");
                            newRow.add(OPOM_RUN_TIME_INDEX, "");
                            newRow.add(OPOM_REMARK_INDEX, "");
                            this.getAddNewRowMap().put(opCurRow + 1, newRow);
                        }
                    }
                }
            }
        }
    }

    public void movSameSubTogether(ArrayList<ArrayList<Object>> fileRes, int startIndex, int endIndex) {
        // int startIndex=1;
        // int endIndex=210;
        if (endIndex < 0) {
            endIndex = fileRes.size() - 1;
        }
        int desCols[] = { OPOM_SUB_JOB_INDEX };
        for (int opCurRow = startIndex; opCurRow <= endIndex; opCurRow++) {
            // getSameSubJobCell
            ArrayList<Object> curRow = fileRes.get(opCurRow);
            String subJobIds = curRow.get(OPOM_SUB_JOB_INDEX).toString();
            int arr[][];
            // subJobId is not null, so mov together
            StringTokenizer st = new StringTokenizer(subJobIds);
            while (st.hasMoreElements()) {
                String subJobId = st.nextElement().toString();
                if (!"".equals(subJobId)) {
                    arr = ExcelUtil.findString(fileRes, subJobId, desCols);
                    if (arr != null) {
                        // begin from No.2 Row :
                        for (int i = 1; i < arr.length; i++) {
                            if (arr[i][0] != 0 || arr[i][1] != 0) {
                                // getRow
                                ArrayList<Object> movRow = fileRes.get(arr[i][0]);
                                System.out.println(movRow.get(OPOM_SUB_JOB_INDEX) + " from index:" + (arr[i][0])
                                        + " to index:" + (arr[i - 1][0] + 1));
                                // the second matched result move to together
                                // ,where
                                // is
                                // the destination?
                                // the destination is behind the last movRow
                                // first:del
                                // fileRes.remove(arr[i][0]);
                                // second:add (the destination is behind the
                                // last
                                // movRow)
                                // fileRes.add(arr[i - 1][0] + 1, movRow);
                                // update movRow RowNo in arr(find result), the
                                // ColNum
                                // is same as all
                                int fromIndex = arr[i][0];
                                int toIndex = arr[i - 1][0] + 1;
                                movRow(fileRes, movRow, fromIndex, toIndex);
                                arr[i][0] = arr[i - 1][0] + 1;
                            }
                        }
                    }

                }
            }
        }
    }

    public void addNewJobId(ArrayList<ArrayList<Object>> fileRes) {
        int[] desCols = { OPOM_JOB_ID_INDEX };
        for (Entry<Integer, ArrayList<Object>> entry : this.getAddNewRowMap().entrySet()) {
            String curJobId = entry.getValue().get(OPOM_JOB_ID_INDEX).toString();
            int arr[][] = ExcelUtil.findString(fileRes, curJobId, desCols);
            if (arr == null) {
                fileRes.add(entry.getValue());
            }
        }
        this.getAddNewRowMap().clear();
    }
    

    public void findAlone(ArrayList<ArrayList<Object>> writeFileRes, int startIndex, int endIndex) throws IOException {

        // int startIndex = 1;//row no=2 in excel file
        // int endIndex = 210;
        File file = new File("D:\\myWinBat\\excelBatInput\\aloneJob.txt");
        FileWriter fos = new FileWriter(file);
        if (endIndex < 0) {
            endIndex = writeFileRes.size() - 1;
        }
        String jobId = "";
        int[] jobIdCol = { OPOM_JOB_ID_INDEX };
        StringBuilder sb = new StringBuilder();
        try {
            // iterate OPOM.xlsx
            for (int opCurRow = startIndex; opCurRow <= endIndex; opCurRow++) {
                ArrayList<Object> curRow = writeFileRes.get(opCurRow);
                jobId = curRow.get(OPOM_JOB_ID_INDEX).toString();
                ArrayList<Object> prevRow = writeFileRes.get(opCurRow - 1);
                String preSubJobIds = prevRow.get(OPOM_SUB_JOB_INDEX).toString();
                if (preSubJobIds != null && !"".equals(preSubJobIds.trim())) {
                    String aroundSubJobIds;
                    int fromIndex = opCurRow - 20;
                    int toIndex = opCurRow + 20;
                    fromIndex = fromIndex < 1 ? 1 : fromIndex;
                    toIndex = toIndex > writeFileRes.size() - 1 ? writeFileRes.size() - 1 : toIndex;
                    for (int i = fromIndex; i < toIndex; i++) {
                        String subJobId = writeFileRes.get(i).get(OPOM_SUB_JOB_INDEX).toString();
                        sb.append(subJobId + " ");
                    }
                    aroundSubJobIds = sb.toString();
                    if (aroundSubJobIds != null && !"".equals(aroundSubJobIds.trim())) {

                        Pattern pattern = Pattern.compile(jobId);
                        Matcher matcher = pattern.matcher(aroundSubJobIds);
                        if (!matcher.find()) {
                            // it is alone
                            fos.write("alone:" + jobId + "\n");
                        }

                    }
                    sb.setLength(0);
                }
                // String
                // preSubJobIds=prevRow.get(OPOM_SUB_JOB_INDEX).toString();
                // if(preSubJobIds!=null&&!"".equals(preSubJobIds.trim())) {
                // Pattern pattern=Pattern.compile(jobId);
                // Matcher matcher = pattern.matcher(preSubJobIds);
                // if(!matcher.find()) {
                // //it is alone
                // fos.write("alone:"+jobId+"\n");
                // }
                //
                // }
            }
            fos.flush();

        } catch (Exception e) {

        }
    }

    private class Runtime {
        // used for need +1
        private boolean prefixFlag;
        private boolean monthAllFlag=false;
        private boolean weekAllFlag=false;
        private ArrayList<ArrayList<Object>> readExcelRes;
        private ArrayList<ArrayList<Object>> writeFileRes;
        private int startIndex;
        private int endIndex;
        private boolean timeFlag = false;
        private boolean daysFlag = false;
        private boolean intervalFlag = false;

        public Runtime(ArrayList<ArrayList<Object>> readExcelRes, ArrayList<ArrayList<Object>> writeFileRes,
                int startIndex, int endIndex) {
            this.readExcelRes = readExcelRes;
            this.writeFileRes = writeFileRes;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        private String convertFromToTime(String fromStr, String toStr) throws ParseException {
            StringBuilder output = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            String from = fromStr;
            String to = toStr;
            sb.append(from);
            while (sb.length() != 0 && sb.length() < 4) {
                sb.insert(0, "0");
            }
            from = sb.toString();
            sb.setLength(0);
            sb.append(to);
            while (sb.length() != 0 && sb.length() < 4) {
                sb.insert(0, "0");
            }
            to = sb.toString();
            Date fromTime = null;
            Date toTime = null;
            String wFromTime = "";
            String wToTime = "";
            if (from != null && !"".equals(from)) {
                fromTime = readDf.parse(from);
                Date d = readDf.parse("0800");
                boolean b = fromTime.before(d);
                if (b) {
                    prefixFlag = true;
                }
                wFromTime = writeDf.format(fromTime);
            }
            if (to != null && !"".equals(to)) {
                toTime = readDf.parse(to);
                wToTime = writeDf.format(toTime);
            }
            if (wFromTime != null && !"".equals(wFromTime.trim())) {
                timeFlag = true;
                if (wToTime != null && !"".equals(wToTime.trim())) {
                    output.append("( " + wFromTime + " to " + wToTime + " )");
                } else {
                    output.append(wFromTime);
                }
            }
            return output.toString();
        }

        private String convertDays(String monthlyStr, String weeklyStr) {
            StringBuilder output = new StringBuilder();
            StringBuilder convertedMonth = new StringBuilder();
            StringBuilder convertedWeek = new StringBuilder();
            String[] splited;
            String suffix = "";
            String prefix = "";// add at front of all
            if (monthlyStr != null && !"".equals(monthlyStr.trim())) {
                daysFlag = true;
                if ("ALL".equals(monthlyStr.trim())) {
                    monthAllFlag=true;
                    return "( Monthly: " + monthlyStr + " )";
                }
                splited = monthlyStr.split(",");
                boolean isNumber = true;
                Integer day = 0;
                String curString;
                for (int i = 0; i < splited.length; i++) {
                    curString = splited[i];
                    if (curString != null && !"".equals(curString.trim())) {
                        if (splited[i].startsWith("-")) {
                            prefix = "exclude ";
                            curString = curString.substring(1, curString.length());
                        }
                        try {
                            day = Integer.parseInt(curString);
                        } catch (NumberFormatException e) {
                            isNumber = false;
                        }
                        
                        if (isNumber) {
                            if(day==1) {
                                suffix = "st";
                            }else if (day == 2) {
                                suffix = "nd";
                            } else if (day == 3) {
                                suffix = "rd";
                            } else {
                                suffix = "th";
                            }
                        } else {

                            // do not convertr
                        }
                    }
                    String res = day == 0 ? curString : day.toString();
                    if("L1".equalsIgnoreCase(res)) {
                        res="last day of month";
                    }

                    if (i != splited.length - 1) {
                        convertedMonth.append(res).append(suffix).append(",");
                    } else {
                        convertedMonth.append(res).append(suffix);
                    }
                }
                convertedMonth.insert(0,prefix);
                convertedMonth.insert(0, "Monthly: ");

            }
            if (weeklyStr != null && !"".equals(weeklyStr.trim())) {
                daysFlag = true;
                if ("ALL".equals(weeklyStr.trim())) {
                    weekAllFlag=true;
                    return "( Weekly: " + weeklyStr + " )";
                }
                splited = weeklyStr.split(",");
                Integer weekIndex = 0;
                String curString;
                for (int i = 0; i < splited.length; i++) {
                    curString = splited[i];
                    if (curString != null && !"".equals(curString.trim())) {
                        if (curString.startsWith("D")) {
                            Integer weekNo = null;
                            Integer weekDayNo = null;
                            try {
                                weekDayNo = Integer.parseInt(curString.trim().substring(1, 2));
                                weekNo = Integer.parseInt(curString.trim().substring(3, 4));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String weekSuffix = "";
                            if(weekNo==1) {
                                weekSuffix = "st";
                            }else if (weekNo == 2) {
                                weekSuffix = "nd";
                            } else if (weekNo == 3) {
                                weekSuffix = "rd";
                            } else {
                                weekSuffix="th";
                            }
                            convertedWeek.append(WEEK_ARR[weekIndex]).append(" of ").append(weekNo).append(weekSuffix)
                                    .append(" week");

                        } else {
                            // number
                            try {
                                weekIndex = Integer.parseInt(splited[i]);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            convertedWeek.append(WEEK_ARR[weekIndex]);

                        }
                    }
                    if (i != splited.length - 1) {
                        convertedWeek.append(", ");
                    }
                }

                convertedWeek.insert(0, "Weekly: ");
            }

            output.append(convertedMonth).append(convertedWeek);

            output.insert(0, "( ");
            output.insert(output.length(), " )");

            return output.toString();
        }

        private String convertInterval(String intervalStr) {
            StringBuilder output = new StringBuilder();
            String subSequence = (String) intervalStr.subSequence(0, intervalStr.length() - 1);
            String suffix = "";
            String suffixTemp = intervalStr.substring(intervalStr.length() - 1);
            int interval = Integer.parseInt(subSequence);
            if (interval != 0) {
                intervalFlag = true;
                if ("M".equalsIgnoreCase(suffixTemp)) {
                    suffixTemp = "mins";
                    suffix = suffixTemp;
                }
                if ("H".equalsIgnoreCase(suffixTemp)) {
                    suffixTemp = "hours";
                    suffix = suffixTemp;
                }
                output.append(" , interval: " + interval + " " + suffix);
            }
            return output.toString();
        }

        public void outputRuntime(int[][] arr, int opCurRow) throws ParseException {
            ArrayList<Object> curOPOMRow = writeFileRes.get(opCurRow);
            StringBuilder output = new StringBuilder();
            String monthlyStr;
            String weeklyStr;
            String fromStr;
            String toStr;
            String intervalStr;

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][1] == BATCH_JOB_ID_INDEX) {
                        ArrayList<Object> curBatchRow = readExcelRes.get(arr[i][0]);

                        fromStr = curBatchRow.get(BATCH_FROM_TIME_INDEX).toString().trim();
                        toStr = curBatchRow.get(BATCH_TO_TIME_INDEX).toString().trim();
                        String time = convertFromToTime(fromStr, toStr);

                        monthlyStr = curBatchRow.get(BATCH_MONTH_INDEX).toString();
                        weeklyStr = curBatchRow.get(BATCH_WEEK_INDEX).toString();
                        String days = convertDays(monthlyStr, weeklyStr);

                        intervalStr = curBatchRow.get(BATCH_INTETVAL_INDEX).toString();
                        String interval = convertInterval(intervalStr);

                        if (daysFlag) {
                            output.append(days);
                        }
                        if (timeFlag) {
                            if (daysFlag) {
                                output.append(" @ ");
                            }
                            output.append(time);
                        }
                        if (intervalFlag) {
                            output.append(interval);
                        }
                        if (!daysFlag && !timeFlag) {
                            curOPOMRow.set(OPOM_REMARK_INDEX, "run time from parent");
                        }
                        if (this.prefixFlag && !weekAllFlag && !monthAllFlag) {
                            curOPOMRow.set(OPOM_REMARK_INDEX, "need +1");
                        }
                        // output=prefix+date+" @ "+fromTo+" , interval:
                        // "+interval;
                        curOPOMRow.set(OPOM_RUN_TIME_INDEX, output.toString());
                        output.setLength(0);
                        prefixFlag=false;
                        daysFlag = false;
                        timeFlag = false;
                        intervalFlag = false;
                        weekAllFlag=false;
                        monthAllFlag=false;
                        

                    }
                }
            }

        }

        private void justOutputRunTime() throws ParseException {
            int[][] arr;
            int[] desCols = { BATCH_JOB_ID_INDEX };
            for (int opCurRow = startIndex; opCurRow < writeFileRes.size(); opCurRow++) {
                ArrayList<Object> opomCurRow = writeFileRes.get(opCurRow);
                String opJobId = opomCurRow.get(OPOM_JOB_ID_INDEX).toString();
                arr = ExcelUtil.findString(readExcelRes, opJobId, desCols);
                if (arr != null) {
                    this.outputRuntime(arr, opCurRow);
                } else {
                    System.err.println("error:" + opJobId + " not found in batch job id col");
                    opomCurRow.set(OPOM_REMARK_INDEX, "not found in batch job id col");
                }
            }
        }

    }

    public Runtime getRuntime() {
        return runtime;
    }

    public void setRuntime(Runtime runtime) {
        this.runtime = runtime;
    }

    private Map<Integer, ArrayList<Object>> getAddNewRowMap() {
        return addNewRowMap;
    }

    private String getReadFileName() {
        return readFileName;
    }

    private String getWriteFile() {
        return writeFile;
    }

}
