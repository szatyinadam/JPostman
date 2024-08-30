package com.postman.collection.exception;

/**
 * Thrown when a collection is not found in Postman, or if a 404 is thrown attempting to retrieve
 * a collection from Postman by URI
 */
public class CollectionNotFoundException extends Exception {

    public CollectionNotFoundException(String message) {
        super(message);
    }
}
