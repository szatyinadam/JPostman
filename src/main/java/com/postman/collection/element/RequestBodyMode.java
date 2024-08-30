package com.postman.collection.element;

/**
 * Enumeration listing the possible values for the name of the property containing body data in a {@link Request}
 *
 * <p>Postman SDK analog: <code><a href="http://www.postmanlabs.com/postman-collection/RequestBody.html#:~:text=(static)-,MODES,-%3Astring">MODES</a></code></p>
 */
public enum RequestBodyMode {
    TEXT, FORMDATA, RAW, FILE, GRAPHQL, URLENCODED
}
