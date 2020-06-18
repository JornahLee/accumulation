package com.jornah.work.camelroute;

import com.jornah.divideclass.DivideClazz;
import com.jornah.util.MyStringUtil;
import com.jornah.work.camelroute.pojo.Function;
import com.jornah.work.excel.ExcelUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author LiCong
 *
 * create route model by old class file for woring
 * input java file
 * output txt route model file
 */
public class DirectStates {
    private File outStateFile;
    private List<Function> functions = new ArrayList<Function>();
    private ArrayList<Function> tempFunList = new ArrayList<Function>(8);
    private ArrayList<Function> nextTempFunList = new ArrayList<Function>(8);
    // private Set<Function> nextTempFunSet = new HashSet<Function>(8);
    // private Set<Function> tempFunSet = new HashSet<Function>(8);
    private Set<Function> totalFunSet = new HashSet<Function>(32);

    // private Set<Function> tempFunSet = new HashSet<Function>(8);
    // private Set<Function> totalFunSet = new HashSet<Function>(32);
    // private Set<Function> nextTempFunSet = new HashSet<Function>(8);

    public static void main(String[] args) {
        DirectStates directStates = new DirectStates();
        String rootDir = "D:\\workspace\\workRecordsList\\EAI\\ManageOrder\\CreateRouteFlow\\classInput";
        File file = new File(rootDir);

        if (!file.exists()) {
            file.mkdirs();
        }
        File[] files = null;
        if (file.isDirectory()) {
            files = file.listFiles();
        }
        DivideClazz divideClazz = new DivideClazz();
        String inFile;
        String outClearedFile;
        String outFormatFile;
        String outStateFile;
        FileWriter fw;
        Pattern startStatePattern = compile("\\d+|\\bstart_state\\b");
        startStatePattern.matcher("");

        try {
            for (File f : files) {
                // path :CreateRouteFlow is root path
                //format path : in root/
                //cleared path : in root/cleared
                inFile = f.getAbsolutePath();
                outClearedFile = file.getParent() + "\\cleared\\c-" + f.getName();
                outFormatFile = file.getParent() + "\\format\\f-" + f.getName();
                String filename=f.getName();
                outStateFile = file.getParent() + "\\state\\s-"
                        + filename.substring(0,filename.indexOf('.'))+".xlsx";
                File tempFile = new File(outStateFile);
                if (!tempFile.exists()) {
                    tempFile.createNewFile();
                }
                directStates.setOutStateFile(tempFile);
                // miss  generate EventName
                ArrayList<Function> functions = directStates.generateFunction(divideClazz, inFile, outClearedFile, outFormatFile);
                directStates.setFunctions(functions);
                directStates.generateRouteModel(functions);

                directStates.clearAll();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void generateRouteModel(ArrayList<Function> functions) throws IOException {
        /**1.init tempSet and totalSet
         *
         *
         */
        // 1. init tempSet : which function's fromState is number or Start_State
        Pattern startPattern = compile("\\d+|Start_State|Start");
        Matcher matcher;
        int startIndex = 0;
        for (Function function : functions) {
            matcher = startPattern.matcher(function.getFromState());
            while (matcher.find()) {
                if (!totalFunSet.contains(function)) {
                    tempFunList.add(function);
                    totalFunSet.add(function);
                }
            }
        }
        Collections.sort(tempFunList);
        // 2. drive my program
        ArrayList<ArrayList<Object>> rowList=new ArrayList<ArrayList<Object>>();
        ArrayList<Object> newRow=new ArrayList<>();
        while (tempFunList != null && !tempFunList.isEmpty()) {
            for (Function function : tempFunList) {
                // add new Row ----start
                for (int i = 0; i < startIndex; i++) {
                    newRow.add("");
                }
                newRow.add(function.getFromState()+function.getThisState()+"");
                newRow.add(function.getName()+"");
                newRow.add(function.getToState()+"");
                newRow.add(function.getEventName()+"");
                newRow.add(function.getBpoCall()+"");
                rowList.add(newRow);
                newRow=new ArrayList<Object>();
                // add new Row ----end

                ArrayList<Function> funByToState = findFunByToStateAndThisState(function);
                for (Function function1 : funByToState) {
                    if (!totalFunSet.contains(function1)) {
                        nextTempFunList.add(function1);
                        totalFunSet.add(function1);
                    }
                }
            }
            Collections.sort(nextTempFunList);
            tempFunList = nextTempFunList;
            nextTempFunList = new ArrayList<>(8);
            startIndex++;
            //blank row
            ArrayList blankRow=new ArrayList<>();
            blankRow.add("");
            rowList.add(blankRow);
        }
        try {
            String name=outStateFile.getAbsolutePath();
            ExcelUtil.writeExcel(rowList,outStateFile.getAbsolutePath(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Function> findFunByToStateAndThisState(Function fun) {
        ArrayList<Function> restList = new ArrayList<>();
        if (fun == null || "".equals(fun.getToState().trim())) {
            return restList;
        }
        //find  which fromstate = last tostate
        ArrayList<String> findRes = null;
        ArrayList<String> findRes2 = null;
        for (Function function : functions) {
            findRes = MyStringUtil.containsString(function.getFromState(), "\\b" + fun.getToState() + "\\b");
            findRes2 = MyStringUtil.containsString(function.getThisState(), "\\b" + fun.getToState() + "\\b");
            if (findRes != null && !findRes.isEmpty()) {
                restList.add(function);
            }
            if (findRes2 != null && !findRes2.isEmpty()) {
                restList.add(function);
            }
        }
        return restList;
    }

    private void clearAll() {
        functions.clear();
        this.nextTempFunList.clear();
        this.tempFunList.clear();
        this.totalFunSet.clear();

    }



    /**
     *
     * not generating event name
     *
     */
    private ArrayList<Function> generateFunction( DivideClazz divideClazz, String inFile, String outClearedFile, String outFormatFile) throws IOException {
        String fromStateRegex = "(?<=_fromState = \").+(?=\";)";
        String toStateRegex = "(?<=_toState = \").+(?=\";)";
        String thisStateRegex = "(?<=_thisState = \").+(?=\";)";
        String bpoCallRegex = "bpo\\..+;|this\\.bpo\\..+;";

        Pattern fromStatePattern = compile(fromStateRegex);
        Pattern toStatePattern = compile(toStateRegex);
        Pattern thisStatePattern = compile(thisStateRegex);
        Pattern bpoCallPattern = compile(bpoCallRegex);

        Matcher fromStateMatcher = null;
        Matcher toStateMatcher = null;
        Matcher thisStateMatcher = null;
        Matcher bpoCallMatcher = null;

        ArrayList<Function> functionList = new ArrayList<>();
        divideClazz.init(inFile, outClearedFile, outFormatFile);
        divideClazz.process();
        Map<String, String> functionMap = divideClazz.getFunctionMap();

        StringBuilder allEventNameStr = divideClazz.getAllEventNameStr();
        // eventNamePattern
        for (Map.Entry<String, String> entry : functionMap.entrySet()) {
            // a function may have Multiple fromState
            String funBody = entry.getValue();
            fromStateMatcher = fromStatePattern.matcher(funBody);
            toStateMatcher = toStatePattern.matcher(funBody);
            thisStateMatcher = thisStatePattern.matcher(funBody);
            bpoCallMatcher = bpoCallPattern.matcher(funBody);
            String name = "";
            Matcher matcher = compile("\\w+(?=\\()").matcher(entry.getKey());
            if (matcher.find()) {
                name = matcher.group();
            }
            StringBuilder fromState = new StringBuilder();
            StringBuilder eventName = new StringBuilder();
            StringBuilder bpoCall = new StringBuilder();
            String toState = "";
            String thisState = "";
            while (fromStateMatcher.find()) {
                fromState.append(fromStateMatcher.group()).append(" | ");
            }
            if(fromState.length()>=2){
                fromState.delete(fromState.length()-2,fromState.length());
            }
            while (toStateMatcher.find()) {
                toState = toStateMatcher.group();
            }
            while (thisStateMatcher.find()) {
                thisState = thisStateMatcher.group();
            }
            while (bpoCallMatcher.find()) {
                bpoCall.append(bpoCallMatcher.group());
            }

            Matcher eventNameMatcher = compile("(?<=" + name + "\", \")[\\w.]+(?=\"\\))").matcher(allEventNameStr);
            while(eventNameMatcher.find()){
                eventName.append(eventNameMatcher.group());
            }

            Function function = new Function(name.trim(), fromState.toString().trim(), toState.trim(), thisState.trim(), eventName.toString().trim(),bpoCall.toString());
            functionList.add(function);

        }
        divideClazz.clearAll();
        return functionList;
    }

    public File getOutStateFile() {
        return outStateFile;
    }

    public void setOutStateFile(File outStateFile) {
        this.outStateFile = outStateFile;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

}
