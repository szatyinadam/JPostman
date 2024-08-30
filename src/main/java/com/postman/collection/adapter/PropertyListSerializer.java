package com.postman.collection.adapter;

import com.google.gson.*;
import com.postman.collection.element.Property;

import java.lang.reflect.Type;
import java.util.HashMap;




public class PropertyListSerializer implements JsonSerializer<HashMap<String, Property>> {
    
    /** 
     * @param src
     * @param typeOfSrc
     * @param context
     * @return JsonElement
     */
    public JsonElement serialize(HashMap<String, Property> src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        JsonArray varArray = new JsonArray();
        JsonObject varElement;
        String curKey;
        Property curVar;
        for (String s : src.keySet()) {
            curKey = s;
            varElement = new JsonObject();
            curVar = src.get(curKey);
            varElement.addProperty("key", curVar.getKey());
            varElement.addProperty("value", curVar.getValue());
            if (curVar.getDescription() != null) {
                varElement.addProperty("description", curVar.getDescription());
            }
            if (curVar.getType() != null) {
                varElement.addProperty("type", curVar.getType());
            }
            varArray.add(varElement);
        }

        return varArray;

    }

}
