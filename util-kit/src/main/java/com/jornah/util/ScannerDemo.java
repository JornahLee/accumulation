package com.jornah.util;

import java.util.Scanner;

/**
 * Scanner 是读取的流，next()的默认终止符是whitespace(换行 空格)
 */
public class ScannerDemo {

    public static final String TERMINATOR = "::!!";


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        getMultiLineTextFromConsole(scanner, TERMINATOR);
    }

    /**
     * 在遇到终止符(terminator)之前一直读取键盘输入，即使输入了换行也不会终止
     *
     * @param scanner
     */
    private static void getMultiLineTextFromConsole(Scanner scanner, String terminator) {
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String read = scanner.nextLine();
            sb.append(read).append("\n");
            if (read.contains(terminator)) {
                break;
            }
        }
        sb.setLength(sb.length() - terminator.length());
        System.out.println(sb);
    }
}
