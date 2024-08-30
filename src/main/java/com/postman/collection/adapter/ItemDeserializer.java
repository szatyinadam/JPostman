package com.postman.collection.adapter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.postman.collection.element.*;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Custom deserializer for writing out the Item class, the <code>item</code> propertyList
 */


public class ItemDeserializer implements JsonDeserializer<Item> {

    /**
     * Deserialize a {@link Item }
     *
     * @param jElement The JSON element passed in by Gson
     * @param typeOfT  The type for the adapter, {@link RequestAuth}
     * @param context  Deserialization context
     * @return ItemAuth The assembed {@link Item} object
     * @throws JsonParseException IF there are errors in the JSON element
     */
    @Override
    public Item deserialize(JsonElement jElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = jElement.getAsJsonObject();

        if (jObject.get("request") == null) {
            return context.deserialize(jElement.getAsJsonObject(), ItemGroup.class);
        }

        Type typeEvent = new TypeToken<ArrayList<Event>>() {
        }.getType();
        Type typeResponse = new TypeToken<ArrayList<Response>>() {
        }.getType();
        Request newRequest = new Request(jObject.get("name").getAsString());
        if (jObject.get("description") != null) {
            newRequest.setDescription(jObject.get("description").getAsString());
        }

        RequestBody req = context.deserialize(jObject.getAsJsonObject("request"), RequestBody.class);
        newRequest.setRequestBody(req);
        ArrayList<Event> events = context.deserialize(jObject.getAsJsonArray("event"), typeEvent);
        newRequest.setEvents(events);
        ArrayList<Response> responses = context.deserialize(jObject.getAsJsonArray("response"), typeResponse);
        newRequest.setResponses(responses);
        return newRequest;
    }
}
             