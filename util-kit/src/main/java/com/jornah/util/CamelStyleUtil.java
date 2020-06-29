package com.jornah.util;

import com.google.common.base.CaseFormat;

public class CamelStyleUtil {
    public static void main(String[] args) {
        String lower = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "ChinesePeople");

        String item="li_cong";
        lower=CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, item);
        System.out.println("lower = " + lower);
    }
}
