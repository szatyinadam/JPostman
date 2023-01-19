package com.postman.collection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.util.Set;
import java.util.Iterator;
import com.postman.collection.util.*;


import com.google.gson.Gson;




public class PostmanCollection extends PostmanItem 
{



private PostmanInfo info = null;
private PostmanVariable[] variable = null;
private PostmanAuth auth = null;
private transient ValidationMessage[] validationMessages;


public static void main( String[] args ) throws Exception
    {
        String filePath = new java.io.File("").getAbsolutePath();

    
        PostmanCollection pmcTest = PostmanCollection.PMCFactory();
        pmcTest.setName("TEST Constructed Queries");

        
        PostmanRequest newReq = new PostmanRequest(enumHTTPRequestMethod.GET, "https://postman-echo.com/post");
        newReq.getUrl().addQuery("foo","bar");
        pmcTest.addRequest(newReq,"Get Foo Bar");
        
        
        
        try {
            pmcTest.writeToFile(filePath + "/test-output/constructed-queries.postman_collection.json");

            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        boolean valid = pmcTest.validate();
        if(!valid) {
            for(int i = 0; i < pmcTest.getValidationMessages().length;i++) {
                System.out.println("Validation message [" + i + "]: " + pmcTest.getValidationMessages()[i].getMessage());
            }
        }
        

        

        
            }

public void moveItem(String itemToMoveKey, String parentKey) throws Exception {
    PostmanItem itemToMove = this.getItem(itemToMoveKey);
    PostmanItem parent = this.getItem(parentKey);
    
    if(itemToMove == null || parent == null)
    {
        throw new Exception("Couldn't find item to move and/or parent");
    }

}

public ValidationMessage[] getValidationMessages() {
    return this.validationMessages != null ? this.validationMessages : new ValidationMessage[0];
}

public void addResponse(String requestKey, PostmanResponse response) throws Exception {
    PostmanItem req = this.getItem(requestKey);
    req.addResponse(response);

}

public boolean validate() throws Exception {
    
    String filePath = new java.io.File("").getAbsolutePath();
    String schemaJSON = null;;
    BufferedReader brItem;
    String strChunk;
    ObjectMapper mapper = new ObjectMapper();
    this.validationMessages = null;
    
    brItem = new BufferedReader(new FileReader(new File(filePath + "/src/main/resources/com/postman/collection/postman-collection-2.1-schema.json")));
        while((strChunk = brItem.readLine()) != null)
            schemaJSON = schemaJSON + strChunk;
        try {
            brItem.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    schemaJSON = schemaJSON.substring(4);
    JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
    JsonSchema schema = factory.getSchema(schemaJSON);
    JsonNode pmcNode = mapper.readTree(this.toJson(false, null));
    schema.initializeValidators();
    Set<ValidationMessage> errors = schema.validate(pmcNode);
    Iterator<ValidationMessage> itErrors = errors.iterator();
    
    if(errors.size() > 0) {
        this.validationMessages = errors.toArray(new ValidationMessage[0]);
    }

    while(itErrors.hasNext()) {
        System.out.println(itErrors.next().getMessage());
    }
    return(errors.size() == 0);

    
}

public void addFolder(PostmanItem newFolder) throws Exception {
    if(newFolder.getItemType() != enumPostmanItemType.FOLDER) {
        throw new Exception("Item is not a folder");
    }

    this.addItem(newFolder);

}


public PostmanItem addRequest(PostmanRequest newRequest, String name, PostmanResponse response) throws Exception {
    PostmanItem newItem = this.addRequest(newRequest, name);
    newItem.addResponse(response);
    return newItem;
}

public PostmanItem addRequest(PostmanRequest  newRequest, String name) throws Exception  {
    PostmanItem newItem = new PostmanItem(name);  
    newItem.setRequest(newRequest);
    super.addItem(newItem);
    //newItem.setResponses(new PostmanResponse[0]);
    return newItem;
    
}

public PostmanItem addRequest(PostmanRequest newRequest, String name, PostmanResponse[] responses) throws Exception {
    PostmanItem newItem = addRequest(newRequest, name);
    newItem.setResponses(responses);
    return newItem;
}



public void addRequest(PostmanRequest newRequest,String name, int position) throws Exception {
    PostmanItem newItem = new PostmanItem(name);  
    newItem.setRequest(newRequest);
    super.addItem(newItem, position);
    newItem.setResponses(new PostmanResponse[0]);
    
}

public void moveItem(PostmanItem itemToMove, PostmanItem newParent) throws Exception {
    PostmanItem curParent = this.getItem(itemToMove.getKey(), true);
    if(itemToMove.equals(newParent))
    {
        throw new Exception("Can't move item to itself, yo");
    }
    
    if(curParent == null) {
        throw new Exception("Item parent not found");
    }
    curParent.removeItem(itemToMove);
    newParent.addItem(itemToMove);
}    
public void setVariables(PostmanVariable[] vars)
{
    this.variable = vars;
}

public void addCollection(PostmanCollection newColl, PostmanItem parent) throws Exception {
    this.addCollection(newColl, parent, true, true);
}

public void addCollection(PostmanCollection newColl, boolean copyScripts, boolean copyVariables) throws Exception {
    this.addCollection(newColl, this, copyScripts, copyVariables);
}

public void addCollection(PostmanCollection newColl, PostmanItem parent, boolean copyScripts, boolean copyVariables) throws Exception {
    
    PostmanItem newFolder = new PostmanItem(newColl.getName());
    parent.addItem(newFolder);
    
    newFolder.addItems(newColl.getItems());
    if (copyVariables) {
        this.setVariables(CollectionUtils.arrayConcat(new PostmanVariable[0], newColl.getVariables(), this.getVariables()));
    }

    if(copyScripts) {
        newFolder.setEvents(newColl.getEvents());
    }
}

public void addCollection(PostmanCollection newColl) throws Exception
{
    
    this.addCollection(newColl, this , true, true);
}
    


public PostmanInfo getInfo() {
    return info;
}

public PostmanVariable[] getVariables() {
    return variable;
}

public PostmanAuth getAuth() {
    return auth;
}

public String getName() {
    return this.getInfo().getName() + "";
}

public void setName(String name) {
    this.getInfo().setName(name);
}

public String getDescription() {
    return this.getInfo().getDescription() + "";
}

public  PostmanCollection(String name){
    this.info = new PostmanInfo();
    this.info.setName(name);
    this.setItems(new PostmanItem[0]);
    

}

public void init() {
    System.out.println("We're in init");
    if(this.getInfo() == null)
    {
        this.info = new PostmanInfo();
    }
    //set parents for all items.
    this.setParents();

}

private void setParents() {
    PostmanItem[] folders = this.getItemsOfType(enumPostmanItemType.FOLDER);
    PostmanItem[] requests = this.getItemsOfType(enumPostmanItemType.REQUEST);
    PostmanItem curItem = null;
    PostmanItem curParent = null;
    if(folders != null && folders.length > 0)
    {
        for (int f= 0; f < folders.length;f++)
        {
            curItem = folders[f];
            curParent = getItem(curItem.getKey(), true);
            curItem.setParent(curParent);
        }
    }
    if(requests != null && requests.length > 0)
    {
        for(int r = 0; r < requests.length; r++)
        {
            curItem = requests[r];
            curParent = getItem(curItem.getKey(), true);
            curItem.setParent(curParent);
        }
    }


}



public static PostmanCollection PMCFactory() {
    String json = "{}";
    Gson gson = new Gson();
    PostmanCollection pmcRetVal = gson.fromJson(json, PostmanCollection.class);
    pmcRetVal.init();
    pmcRetVal.setName("New Collection");
    return pmcRetVal;
}

public static PostmanCollection PMCFactory(String pathToJson) throws FileNotFoundException, IOException {
    PostmanCollection pmcRetVal = null;
    String strChunk = "";
    BufferedReader brItem = null;
    String strRawItem = "";
    com.google.gson.Gson gson = null;
    

    brItem = new BufferedReader(new FileReader(new File(pathToJson)));
        while((strChunk = brItem.readLine()) != null)
            strRawItem = strRawItem + strChunk;
        try {
            brItem.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        gson = new Gson();
        pmcRetVal = gson.fromJson(strRawItem, PostmanCollection.class);
        pmcRetVal.init();
        
        
            


    return pmcRetVal;
}

public void writeToFile(String path) throws IOException {

    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    writer.write(this.toJson(false, null));   
    writer.close();
}


public PostmanItem getItemParent(String key) {
    return this.getItem(key, true);
}

/* public PostmanItem getItem(String key, boolean parent) {
    PostmanItem result = null;
    if (this.item == null)
    {
        return null;
    }
    //recursively traverse items looking for name == key
    for (PostmanItem curItem: item) {
        System.out.println("Parsing: " + this.getName() + " PARENT: " + parent);
        if (item == null)
          return null;
        if (curItem.getName().equals(key))
        {
            if (!parent) {
                result = curItem;
            }
            else
            {
                result = (PostmanItem) this;
            }
            
            break;
        }
        else
        {
            result = curItem.getItem(key, parent);
            if (result != null) {
                break;
            }
        }
        
    }

    return result;
}
 */


@Override
public String getKey() {
    
    return null;
}



}