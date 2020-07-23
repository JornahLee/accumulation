package com.jornah.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.List;

public class GsonExclusionStrategy implements ExclusionStrategy {

    private List<String> excludeFields;

    public GsonExclusionStrategy(List<String> excludeFields) {
        super();
        this.excludeFields = excludeFields;

    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        String name = f.getName();
        return excludeFields.contains(name);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
