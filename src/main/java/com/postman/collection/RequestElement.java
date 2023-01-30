package com.postman.collection;


/**
 * 
 * 
 * Encapsulates the <code>request</code> object property of a ItemElement object
 * 
 * <pre>{@code 
* {
    "name": "URL 7",
    "request": {
        "method": "GET",
        "header": [],
        "url": {
            "raw": "{{baseUrl}}foo.com/bar/:path1/bat.json?foo&#61;1&bar&#61;",
            "host": [
                "{{baseUrl}}foo",
                "com"
            ],
            "path": [
                "bar",
                ":path1",
                "bat.json"
            ],
            "query": [
                {
                    "key": "foo",
                    "value": "1"
                },
                {
                    "key": "bar",
                    "value": ""
                }
            ],
            "variable": [
                {
                    "key": "path1",
                    "value": "path-value"
                }
            ]
        }
    },
    "response": []
}
}</pre> * 
 * 
 */
public class RequestElement extends CollectionElement {
    private enumHTTPRequestMethod method = enumHTTPRequestMethod.GET;
    private UrlElement url;
    private VariableListMap<PostmanVariable> header;
    private String description;
    private BodyElement body;
    private AuthElement auth;
    private String name;

    
    /** 
     * 
     * Returns the value of the <code>name</code> property
     * 
     * @return String
     */
    public String getKey() {
        return this.name;
    }

    /**
     * Construct a RequestElement with the specified HTTP method, host and path.  All URL components are constructed from the host and path strings.
     * 
     * @param method Enumerated value for the HTTP method
     * @param host  String containing the host portion of the URL.
     * @param path String containing the path portion of the URL.
     */
    public RequestElement(enumHTTPRequestMethod method, String host, String path) throws DuplicateVariableKeyException {

        this.setMethod(method);
        
        this.setUrlElement(new UrlElement(host, path));

    }


     /**
     * Construct a RequestElement with the specified HTTP method, and UrlElement. 
     * 
     * @param method Enumerated value for the HTTP method
     * @param url  Pre-constructed UrlElement object
     * 
     */
    public RequestElement(enumHTTPRequestMethod method, UrlElement url) {

        this.setMethod(method);
        this.setUrlElement(url);
    }

    
    /** 
     * 
     * Set the values in the <code>auth</code> array with a pre-populated PostmanAuth object.
     * 
     * @param auth  The auth
     */
    public void setAuth(AuthElement auth) {
        this.auth = auth;
        this.auth.setParent(this);
    }

    
    /** 
     * 
     * Get the PostmanAuth object containing the values of the <code>auth</code> array, or null if it has not been set.
     * 
     * @return PostmanAuth The auth object containing the values.
     */
    public AuthElement getAuth() {
        return this.auth;
    }


    /**
     * 
     * Construct a RequestElement object from a raw URL.  All path, host, URL, auth and other property array elements are parsed out and populated
     * 
     * 
     * @param method  Enumerated value for the HTTP method
     * @param URL  The raw URL 
     */
    public RequestElement(enumHTTPRequestMethod method, String URL) throws DuplicateVariableKeyException {

        this.setUrlElement(new UrlElement(URL));
        this.setMethod(method);

    }

    
    /** 
     * 
     * Return the enumerated value of the <code>method</code> property
     * 
     * @return enumHTTPRequestMethod
     */
    public enumHTTPRequestMethod getMethod() {
        return method;
    }

    
    /** 
     * 
     * Set the value of the <code>method</code> property
     * 
     * @param method
     */
    public void setMethod(enumHTTPRequestMethod method) {
        this.method = method;
    }

    
    /** 
     * 
     * Return the UrlElement containing the values of the <code>url</code> property array
     * 
     * @return UrlElement
     */
    public UrlElement getUrlElement() {
        return url;
    }

    
    /** 
     * @param resolveVariables
     * @return String
     * @throws VariableResolutionException
     */
    public String getUrl(boolean resolveVariables) throws VariableResolutionException {
        String retVal = this.url.getUrl(resolveVariables);
        if(resolveVariables) {
            try {
            retVal = this.getCollection().resolveVariables(retVal);
            
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        return retVal;
    }

    
    /** 
     * 
     * Set the values of the <code>url</code> property 
     * 
     * @param url  UrlElement object containing the values
     */
    public void setUrlElement(UrlElement url) {
        this.url = url;
        this.url.setParent(this);
    }

    
    /** 
     * 
     * Return an ArrayList of PostmanVariable objects containing the key-value pair values for the <code>header</code> property array
     * 
     * 
     * @return ArrayList&#60;{@link com.postman.collection.PostmanVariable PostmanVariable}&#62; The headers, or null if none are present
     */
    public VariableListMap<PostmanVariable> getHeader() {
        return header;
    }

    
    /** 
     * 
     * Set the key-value pair values for the <code>header</code> property array
     * 
     * @param header  Header values
     */
    public void setHeader(VariableListMap<PostmanVariable> header) {
        this.header = header;
    }

    
    /** 
     * 
     * Get the value of the <code>description</code> property, generally the documentation for the request
     * 
     * @return String The description
     */
    public String getDescription() {
        return description;
    }

    
    /** 
     * Set the value of the <code>description</code> property, generally the documentation for the request
     * 
     * 
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /** 
     * 
     * Populate the <code>body</code> array with an empty <code>body</code> property object with the specified <code>mode</code> value.  
     * 
     * @param bodyMode The body mode, eg. RAW
     * @return PostmanBody  The new body
     */
    public BodyElement setBody(enumRequestBodyMode bodyMode) {
        return this.setBody(bodyMode, null);
    }

    
    /** 
     * 
     * 
     * Populate the <code>body</code> array with the specified body content.  The <code>language</code> property of the 
     * request will best set to "text"
     * 
     * 
     * @param bodyMode  Enumerated value for the <code>mode</code> property
     * @param rawContent The raw content for the body
     * @return PostmanBody The new body
     */
    public BodyElement setBody(enumRequestBodyMode bodyMode, String rawContent) {
        this.setBody(new BodyElement(bodyMode, rawContent, enumRawBodyLanguage.TEXT));
        return this.getBody();
    }

    
    /** 
     * Return the PostmanBody object containing the values in the <code>body</code> property object, or null if it has not been set.
     * 
     * @return PostmanBody The body, or null.  
     */
    public BodyElement getBody() {
        return this.body;
    }


    
    /** 
     * 
     * Set the value of the <code>body</code> property object
     * 
     * @param body The new body values
     */
    public void setBody(BodyElement body) {
        this.body = body;
        this.body.setParent(this);
    }

}
