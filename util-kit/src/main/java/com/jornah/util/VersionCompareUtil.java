package com.jornah.util;

import java.util.Objects;

public class VersionCompareUtil {
    public static void main(String[] args) {
        System.out.println(versionComp("1.1.2.2.2", "2.2"));

    }

    public static int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            //这个技巧经常用到，当一个已经遍历结束的话，我们将其赋值为 0
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int res = compare(num1, num2);
            if (res == 0) {
                i++;
                j++;
            } else {
                return res;
            }
        }
        return 0;
    }

    private static int compare(String num1, String num2) {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        if (n1 > n2) {
            return 1;
        } else if (n1 < n2) {
            return -1;
        } else {
            return 0;
        }
    }


    public static int versionComp(String v1, String v2) {
        String[] split1 = v1.split("\\.");
        String[] split2 = v2.split("\\.");
        int i = 0;
        int j = 0;
        String a;
        String b;
        while (i < split1.length || j < split2.length) {
            a = i < split1.length ? split1[i] : "0";
            b = j < split2.length ? split1[j] : "0";
            i++;
            j++;
            if(Objects.equals(a,b)){
                continue;
            }else {
                return compareNumStr(a,b);
            }

        }


        return 0;
    }

    public static int compareNumStr(String s1, String s2) {
        int i = Integer.parseInt(s1);
        int j = Integer.parseInt(s2);
        return i - j > 0 ? 1 : (i - j == 0 ? 0 : -1);
    }
}
