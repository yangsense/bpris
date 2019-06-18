/**
 * FileServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.8  Built on : May 19, 2018 (07:06:11 BST)
 */
package com.ai.aris.server.webservice.imageservice;


/**
 *  FileServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class FileServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public FileServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public FileServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for retrivePatientImage method
     * override this method for handling normal response from retrivePatientImage operation
     */
    public void receiveResultretrivePatientImage(
        com.ai.aris.server.webservice.imageservice.FileServiceStub.UpFileResult result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from retrivePatientImage operation
     */
    public void receiveErrorretrivePatientImage(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for upLoadPatientImage method
     * override this method for handling normal response from upLoadPatientImage operation
     */
    public void receiveResultupLoadPatientImage(
        com.ai.aris.server.webservice.imageservice.FileServiceStub.UpFileResult result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from upLoadPatientImage operation
     */
    public void receiveErrorupLoadPatientImage(java.lang.Exception e) {
    }
}
