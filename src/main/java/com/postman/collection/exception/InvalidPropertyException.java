package com.postman.collection.exception;

import com.postman.collection.element.Event;

/**
 * Exception thrown when an invalid action is taken against an object property, eg., an attempt to see an {@link Event}'s <code>exec</code> property to null, etc.
 */
public class InvalidPropertyException extends Exception {

    public InvalidPropertyException(String msg) {
        super(msg);
    }
}

