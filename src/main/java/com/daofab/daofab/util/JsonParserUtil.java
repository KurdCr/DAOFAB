package com.daofab.daofab.util;

import com.daofab.daofab.model.Child;
import com.daofab.daofab.model.Parent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonParserUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Parent> parseParents(String jsonFilePath) throws IOException {
        File file = new File(jsonFilePath);
        Parent[] parents = objectMapper.readValue(file, Parent[].class);
        return Arrays.asList(parents);
    }

    public static List<Child> parseChildren(String jsonFilePath) throws IOException {
        File file = new File(jsonFilePath);
        Child[] children = objectMapper.readValue(file, Child[].class);
        return Arrays.asList(children);
    }
}
