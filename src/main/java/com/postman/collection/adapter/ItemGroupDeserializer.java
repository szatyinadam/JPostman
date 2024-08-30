package com.postman.collection.adapter;

import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.postman.collection.element.Folder;
import com.postman.collection.element.Item;
import com.postman.collection.element.ItemGroup;
import com.postman.collection.element.RequestAuth;

/**
 * 
 * 
 * Deserializes ItemGroup objects, the <code>item</code> propertyList in a collection
 * 
 * 
 */
public class ItemGroupDeserializer implements JsonDeserializer<ItemGroup> {

    /**
     * 
     * Deserialize a {@link ItemGroup} from the <code>item</code> propertyList
     * 
     * 
     * @param jElement The JSON element passed in by Gson
     * @param typeOfT The type for the adapter, {@link RequestAuth}
     * @param context Deserialization context
     * @return ItemGroup The assembed {@link ItemGroup}
     * @throws JsonParseException IF there are errors in the JSON element
     */
    @Override
    public ItemGroup deserialize(JsonElement jElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = jElement.getAsJsonObject();
        Type typeItem = new TypeToken<ArrayList<Item>>(){}.getType();
        Folder newFolder = null;
            newFolder = new Folder(jObject.getAsJsonPrimitive("name").getAsString());
            if(jObject.get("description") != null) {
                newFolder.setDescription(jObject.getAsJsonPrimitive("description").getAsString());
            }
            if(jObject.get("item") == null) {
                return newFolder;
            }
            ArrayList<Item> items = context.deserialize(jObject.getAsJsonArray("item"), typeItem);
            newFolder.setItems(items);
            
            return newFolder;
        
    }
}
             