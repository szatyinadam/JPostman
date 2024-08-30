package com.postman.collection.adapter;

import com.google.gson.*;
import com.postman.collection.element.Property;
import com.postman.collection.element.RequestAuth;

import java.lang.reflect.Type;

/**
 * Custom serializer for the <code>auth</code> element.
 */
public class AuthSerializer implements JsonSerializer<RequestAuth> {

    /**
     * @param src       The {@link RequestAuth} object to be deserialized
     * @param typeOfSrc The type, {@link RequestAuth}
     * @param context   Serialization context
     * @return JsonElement The JSON element returned by this serializer
     */
    @Override
    public JsonElement serialize(RequestAuth src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonAuth = new JsonObject();
        JsonArray vars = new JsonArray();

        jsonAuth.addProperty("type", src.getAuthTypeAsString());

        for (Property curVar : src.getProperties()) {
            JsonObject curJVar = new JsonObject();
            curJVar.addProperty("key", curVar.getKey());
            curJVar.addProperty("value", curVar.getValue());
            curJVar.addProperty("type", "string");
            vars.add(curJVar);
        }

        jsonAuth.add(src.getAuthTypeAsString(), vars);
        return jsonAuth;
    }
}
