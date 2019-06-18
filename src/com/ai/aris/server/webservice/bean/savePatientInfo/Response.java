/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.aris.server.webservice.bean.savePatientInfo;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.*;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * 
 * 
 * @version $Revision$ $Date$
**/
public class Response implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _resultCode;

    private java.lang.String _errMsg;

    private java.lang.String _patientid;

    private java.lang.String _accessNumber;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() {
        super();
    } //-- com.ai.aris.server.webservice.bean.savePatientInfo.Response()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'accessNumber'.
     * 
     * @return the value of field 'accessNumber'.
    **/
    public java.lang.String getAccessNumber()
    {
        return this._accessNumber;
    } //-- java.lang.String getAccessNumber() 

    /**
     * Returns the value of field 'errMsg'.
     * 
     * @return the value of field 'errMsg'.
    **/
    public java.lang.String getErrMsg()
    {
        return this._errMsg;
    } //-- java.lang.String getErrMsg() 

    /**
     * Returns the value of field 'patientid'.
     * 
     * @return the value of field 'patientid'.
    **/
    public java.lang.String getPatientid()
    {
        return this._patientid;
    } //-- java.lang.String getPatientid() 

    /**
     * Returns the value of field 'resultCode'.
     * 
     * @return the value of field 'resultCode'.
    **/
    public java.lang.String getResultCode()
    {
        return this._resultCode;
    } //-- java.lang.String getResultCode() 

    /**
    **/
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * 
     * 
     * @param out
    **/
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * 
     * 
     * @param handler
    **/
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'accessNumber'.
     * 
     * @param accessNumber the value of field 'accessNumber'.
    **/
    public void setAccessNumber(java.lang.String accessNumber)
    {
        this._accessNumber = accessNumber;
    } //-- void setAccessNumber(java.lang.String) 

    /**
     * Sets the value of field 'errMsg'.
     * 
     * @param errMsg the value of field 'errMsg'.
    **/
    public void setErrMsg(java.lang.String errMsg)
    {
        this._errMsg = errMsg;
    } //-- void setErrMsg(java.lang.String) 

    /**
     * Sets the value of field 'patientid'.
     * 
     * @param patientid the value of field 'patientid'.
    **/
    public void setPatientid(java.lang.String patientid)
    {
        this._patientid = patientid;
    } //-- void setPatientid(java.lang.String) 

    /**
     * Sets the value of field 'resultCode'.
     * 
     * @param resultCode the value of field 'resultCode'.
    **/
    public void setResultCode(java.lang.String resultCode)
    {
        this._resultCode = resultCode;
    } //-- void setResultCode(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.bean.savePatientInfo.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.bean.savePatientInfo.Response) Unmarshaller.unmarshal(com.ai.aris.server.webservice.bean.savePatientInfo.Response.class, reader);
    } //-- com.ai.aris.server.webservice.bean.savePatientInfo.Response unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
