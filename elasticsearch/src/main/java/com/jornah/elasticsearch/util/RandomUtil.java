package com.jornah.elasticsearch.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class RandomUtil {
    public static int randomInt(int start, int end) {
        return RandomUtils.nextInt(start, end);
    }
}
