package com.postman.collection.exception;

/**
 * Exception thrown when an invalid action is attempted on a collection, eg. attempting to add an item to a non-existent parent, etc.
 */
public class InvalidCollectionActionException extends Exception {

    public InvalidCollectionActionException(String message) {
        super(message);
    }

    public InvalidCollectionActionException(Throwable cause) {
        super(cause);
    }
}
