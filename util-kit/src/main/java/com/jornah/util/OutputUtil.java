package com.jornah.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Common utility methods
 */
public class OutputUtil {


    public static void main(String[] args) {
        String value="1.1";
        boolean isNumeric = StringUtils.isNumeric(value);
        System.out.println("--licg---     isNumeric : " + isNumeric + "    -----");
        System.out.println( Integer.parseInt("11"));
    }


    /**
     * Pads the given pad to the left of the given string until the string if of size size.
     */

    static public String padLeft(String in, char pad, int size) throws Exception {
        StringBuffer str = in == null ? new StringBuffer("") : new StringBuffer(in.trim());
        int a = str.length();
        if (a > size) {
            throw new Exception("in String larger than required maximum size. Sring is <" + in + "> of size " + a + " specifies max is " + size);
        }
        str.ensureCapacity(size);
        a = size - a;
        while ((a--) > 0) {
            str.insert(0, pad);
        }
        return str.toString();
    }

    /**
     * Pads the given pad to the right of the given string until the string if of size size.
     */

    static public String padRight(String in, char pad, int size) throws Exception {
        StringBuffer str = in == null ? new StringBuffer("") : new StringBuffer(in.trim());
        int a = str.length();
        if (a > size) {
            throw new Exception("in String larger than required maximum size. Sring is <" + in + "> of size " + a + " specifies max is " + size);
        }
        str.ensureCapacity(size);
        while ((a++) < size) {
            str.append(pad);
        }
        return str.toString();
    }

    static public String padRight(String in, char pad, int size, boolean isToTrim) throws Exception {
        if (isToTrim == false) {
            StringBuffer str = in == null ? new StringBuffer("") : new StringBuffer(in);
            int a = str.length();
            if (a > size) {
                throw new Exception("in String larger than required maximum size. Sring is <" + in + "> of size " + a + " specifies max is " + size);
            }
            str.ensureCapacity(size);
            while ((a++) < size) {
                str.append(pad);
            }
            return str.toString();
        } else {
            return OutputUtil.padRight(in, pad, size);
        }
    }
}
