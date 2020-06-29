package com.jornah.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Ognl工具类，主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写.
 * Ognl访问静态方法的表达式： @class@method(args).
 * <pre>
 *  &lt;if test="@com.xxx.xxx.xxx.Ognl@isNotEmpty(userId)">
 *      and user_id = #{userId}
 *  &lt;/if>
 * </pre>
 */

public class Ognl {

    /**
     * 可以用于判断String,Map,Collection,Array是否为空.
     */
    public static boolean isEmpty(Object o) throws IllegalArgumentException {
        if (o == null) {
            return true;
        }

        if (o instanceof String) {
            if (((String) o).length() == 0) {
                return true;
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    /**
     * 判断对象是否为null.
     *
     * @param o
     * @return boolean    返回类型
     * @Title: isNull
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 判断对象是否不为null.
     *
     * @param o
     * @return boolean    返回类型.
     * @Title: isNotNull
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * 可以用于判断 Map,Collection,String,Array是否不为空.
     *
     * @param
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNotBlank(Object o) {
        System.out.println("--licg---     in is bot blank  -----");
        return !isBlank(o);
    }

    public static boolean isNumber(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Number) {
            return true;
        }
        if (o instanceof String) {
            String str = (String) o;
            if (str.length() == 0 || str.trim().length() == 0) {
                return false;
            }

            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean isBlank(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            String str = (String) o;
            return isBlank(str);
        }
        return false;
    }

    public static boolean isBlank(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
