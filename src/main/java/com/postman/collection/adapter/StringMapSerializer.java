package com.postman.collection.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;

public class StringMapSerializer implements JsonSerializer<HashMap<String, String>> {


    /**
     * @param src
     * @param typeOfSrc
     * @param context
     * @return JsonElement
     */
    @Override
    public JsonElement serialize(HashMap<String, String> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonMap = new JsonObject();
        for (String curKey : src.keySet()) {
            jsonMap.addProperty(curKey, src.get(curKey));
        }

        return jsonMap;
    }
}
