package com.postman.collection.exception;

/**
 * Exception thrown if there is an attempt to add an item as a child to itself.
 */


public class RecursiveItemAddException extends Exception {

    public RecursiveItemAddException(String message) {
        super(message);
    }
}
