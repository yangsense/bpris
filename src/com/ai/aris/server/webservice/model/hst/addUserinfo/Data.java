/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.aris.server.webservice.model.hst.addUserinfo;

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
public class Data implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private User _user;


      //----------------/
     //- Constructors -/
    //----------------/

    public Data() {
        super();
    } //-- com.ai.aris.server.webservice.model.hst.addUserinfo.Data()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'user'.
     * 
     * @return the value of field 'user'.
    **/
    public User getUser()
    {
        return this._user;
    } //-- User getUser() 

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
     * Sets the value of field 'user'.
     * 
     * @param user the value of field 'user'.
    **/
    public void setUser(User user)
    {
        this._user = user;
    } //-- void setUser(User) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.addUserinfo.Data unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.addUserinfo.Data) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.addUserinfo.Data.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.addUserinfo.Data unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
