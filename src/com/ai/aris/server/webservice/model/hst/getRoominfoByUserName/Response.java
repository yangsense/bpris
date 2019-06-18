/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.aris.server.webservice.model.hst.getRoominfoByUserName;

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

    private java.lang.String _code;

    private java.lang.String _msg;

    private Data _data;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() {
        super();
    } //-- com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'code'.
     * 
     * @return the value of field 'code'.
    **/
    public java.lang.String getCode()
    {
        return this._code;
    } //-- java.lang.String getCode() 

    /**
     * Returns the value of field 'data'.
     * 
     * @return the value of field 'data'.
    **/
    public Data getData()
    {
        return this._data;
    } //-- Data getData() 

    /**
     * Returns the value of field 'msg'.
     * 
     * @return the value of field 'msg'.
    **/
    public java.lang.String getMsg()
    {
        return this._msg;
    } //-- java.lang.String getMsg() 

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
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
    **/
    public void setCode(java.lang.String code)
    {
        this._code = code;
    } //-- void setCode(java.lang.String) 

    /**
     * Sets the value of field 'data'.
     * 
     * @param data the value of field 'data'.
    **/
    public void setData(Data data)
    {
        this._data = data;
    } //-- void setData(Data) 

    /**
     * Sets the value of field 'msg'.
     * 
     * @param msg the value of field 'msg'.
    **/
    public void setMsg(java.lang.String msg)
    {
        this._msg = msg;
    } //-- void setMsg(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
