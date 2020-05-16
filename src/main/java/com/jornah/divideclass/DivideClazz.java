package com.jornah.divideclass;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * divide class
 * get all function name and body(save in entity "Function")
 * get class name
 * clear //comment  and /star  star/ comment
 *
 * @author LiCong
 */
public class DivideClazz {
    private File inFile;
    private File outClearedFile;
    private File outFormatFile;
    private Map<String, String> functionMap = new HashMap<String, String>(20);
    private Map<String, String> clazzMap = new HashMap<String, String>();
    private StringBuilder allEventNameStr = new StringBuilder();

    private int paranthesesCount;

    public static void main(String[] args) {
        DivideClazz dc = new DivideClazz();
        String inFileName = "D:\\workspace\\workRecordsList\\EAI\\ManageAcctPrdt\\CreateRouteFlow\\classInput\\testClearComment.txt";
        String outFileName = "D:\\workspace\\workRecordsList\\EAI\\ManageAcctPrdt\\CreateRouteFlow\\outputClass.txt";
        String outFormatFileName = "D:\\workspace\\workRecordsList\\EAI\\ManageAcctPrdt\\CreateRouteFlow\\outFormatFile.txt";
        try {
            dc.init(inFileName, outFileName, outFormatFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dc.process();
        dc.clearAll();
    }

    public void clearAll() {
        this.clazzMap.clear();
        this.functionMap.clear();
        this.inFile = null;
        this.outClearedFile = null;
        this.outFormatFile = null;
        this.paranthesesCount = 0;
        allEventNameStr.setLength(0);
    }

    public void process() {
        InputStream in = null;
        InputStream in2 = null;
        try {
            in2 = new FileInputStream(getInFile());
            //get allEventNameStr
            getAllEventNameStr(in2);

            in = new FileInputStream(getInFile());
            clearCommentAndImport(in);
            FileInputStream clearedInFile = new FileInputStream(getOutClearedFile());
            divide(clearedInFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //output to outFormatFile.txt
        FileOutputStream fout = null;
        OutputStreamWriter outw = null;
        try {
            fout = new FileOutputStream(getOutFormatFile());
            outw = new OutputStreamWriter(fout);
            for (Map.Entry entry : getClazzMap().entrySet()) {
                outw.write(entry.getKey() + "\r\n");
            }
            for (Map.Entry entry : getFunctionMap().entrySet()) {
                outw.write("----------------");
                outw.write(entry.getKey() + "\r\n");
                outw.write(entry.getValue() + "\r\n\r\n");
            }
            outw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getAllEventNameStr(InputStream in2) {
        String regex = "(?<=new RuleImpl).+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        InputStreamReader inr = new InputStreamReader(in2);
        BufferedReader br = new BufferedReader(inr);
        String line = null;
        try {
            do {
                line = br.readLine();
                if (line != null && line.length() > 0) {
                    matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        this.allEventNameStr.append(matcher.group() + "\r\n");
                    }
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(String inFileName, String outFileName, String outFormatFileName) throws IOException {
        File inFile = new File(inFileName);
        File outFile = new File(outFileName);
        File outFormatFile = new File(outFormatFileName);
        init(inFile, outFile, outFormatFile);

    }

    public void init(File inFileName, File outFileName, File outFormatFileName) throws IOException {
        setInFile(inFileName);
        setOutClearedFile(outFileName);
        setOutFormatFile(outFormatFileName);
        generateFile(inFileName, outFileName, outFormatFileName);
    }

    private void generateFile(File inFileName, File outFileName, File outFormatFileName) throws IOException {
        if (!inFileName.exists()) {
            inFileName.getParentFile().mkdirs();
            inFileName.createNewFile();
        }
        if (!outFileName.exists()) {
            System.out.println("--licg---  outFileName.getParentFile().getName() : " + outFileName.getParentFile().getName() + "-----");
            outFileName.getParentFile().mkdirs();
            outFileName.createNewFile();
        }
        if (!outFormatFileName.exists()) {
            boolean mkdirs = outFormatFileName.getParentFile().mkdirs();
            outFormatFileName.createNewFile();
        }
    }

    /**
     * impl by stack
     *
     * @param in
     */
    private void divide(InputStream in) {
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inr);
        Stack<Character> stack = new Stack<Character>();
        paranthesesCount = 0;
        StringBuilder completeFunctionBody = new StringBuilder();
        try {
            int read;
            Character character;
            StringBuilder classBody = new StringBuilder();
            StringBuilder className = new StringBuilder();
            StringBuilder functionBody = new StringBuilder();
            StringBuilder functionName = new StringBuilder();
            do {
                read = inr.read();
                if (read == -1) {
                    break;
                }
                character = (char) read;
                //push everything to stack
                stack.push(character);
                // if (character == '{' && paranthesesCount < 2) {
                if (character == '{') {
                    paranthesesCount++;
                }
                Character pop = null;
                if (character == '}') {
                    if (paranthesesCount > 2) {
                        parseFunBodyAndName(stack, functionBody, functionName);
                        // {} in function body
                        completeFunctionBody.append(functionName).append(functionBody);
                    } else if (paranthesesCount == 2) {
                        parseFunBodyAndName(stack, functionBody, functionName);
                        functionBody.deleteCharAt(functionBody.length()-1);
                        functionBody.append(completeFunctionBody);
                        functionBody.append("\r\n").append('}');
                        completeFunctionBody.setLength(0);
                        functionMap.put(functionName.toString(), functionBody.toString());
                    } else {
                        parseClassBodyAndName(stack, classBody, className);
                        clazzMap.put(className.toString(), classBody.toString());
                    }
                    className.setLength(0);
                    classBody.setLength(0);
                    functionName.setLength(0);
                    functionBody.setLength(0);
                }
            } while (read != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseClassBodyAndName(Stack<Character> stack, StringBuilder classBody, StringBuilder className) {
        parseBodyAndName(stack, classBody, className);
    }

    private void parseFunBodyAndName(Stack<Character> stack, StringBuilder functionBody, StringBuilder functionName) {
        parseBodyAndName(stack, functionBody, functionName);
    }


    private void parseBodyAndName(Stack<Character> stack, StringBuilder body, StringBuilder name) {
        Character pop;
        Character peek;
        do {
            pop = stack.pop();
            if (pop == '{') {
                paranthesesCount--;
            }
            body.insert(0, pop);
        } while (pop != '{');


        do {
            if (stack.empty()) {
                break;
            }
            peek = stack.peek();
            if (peek != '{') {
                pop = stack.pop();
            }
            name.insert(0, pop);
        } while (peek != '{' && !stack.empty());
    }

    /**
     * clear comment begin with "//"
     *
     * @param in
     */
    private void clearCommentAndImport(InputStream in) {
        String regex = "//.+|^(import.+)|/\\*{1,2}[\\s\\S]*?\\*/";
        StringBuilder wholeCode = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        OutputStream out = null;
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inr);
        String line = null;
        OutputStreamWriter outw;
        String afterReplace = null;
        try {
            out = new FileOutputStream(getOutClearedFile());
            outw = new OutputStreamWriter(out);
            do {
                line = br.readLine();
                if (line != null && line.length() > 0) {
                    wholeCode.append(line).append("\r\n");
                }
            } while (line != null);
            matcher = pattern.matcher(wholeCode.toString());
            while (matcher.find()) {
                afterReplace = matcher.replaceAll("");
            }
            if (afterReplace != null) {
                outw.write(afterReplace);
            } else {
                outw.write(wholeCode.toString());
            }
            outw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setOutClearedFile(File outClearedFile) {
        this.outClearedFile = outClearedFile;
    }

    public void setInFile(File inFile) {
        this.inFile = inFile;
    }

    public File getInFile() {
        return inFile;
    }

    public File getOutClearedFile() {
        return outClearedFile;
    }

    public Map<String, String> getFunctionMap() {
        return functionMap;
    }

    public void setFunctionMap(Map<String, String> functionMap) {
        this.functionMap = functionMap;
    }

    public Map<String, String> getClazzMap() {
        return clazzMap;
    }

    public void setClazzMap(Map<String, String> clazzMap) {
        this.clazzMap = clazzMap;
    }

    public File getOutFormatFile() {
        return outFormatFile;
    }

    public void setOutFormatFile(File outFormatFile) {
        this.outFormatFile = outFormatFile;
    }

    public StringBuilder getAllEventNameStr() {
        return allEventNameStr;
    }

    public void setAllEventNameStr(StringBuilder allEventNameStr) {
        this.allEventNameStr = allEventNameStr;
    }
}
