package com.postman.collection.exception;

import com.postman.collection.element.Property;
import com.postman.collection.element.PropertyList;

/**
 * 
 * 
 * Exception thrown when an attempt is made to add a {@link Property} to a {@link PropertyList} with a duplicate key
 * 
 * 
 * 
 */
public class DuplicateVariableKeyException extends Exception{
    
    /**
     * 
     * 
     * Constructs the exception
     * 
     * @param message The message to send with the exception
     */
    public DuplicateVariableKeyException(String message) {
        super(message);
    }
}
