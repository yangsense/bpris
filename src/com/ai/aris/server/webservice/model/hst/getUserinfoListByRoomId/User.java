/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId;

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
public class User implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _displayName;

    private java.lang.String _userName;

    private java.lang.String _userRight;

    private java.lang.String _userid;


      //----------------/
     //- Constructors -/
    //----------------/

    public User() {
        super();
    } //-- com.ai.aris.server.webservice.model.hst.User()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'displayName'.
     * 
     * @return the value of field 'displayName'.
    **/
    public java.lang.String getDisplayName()
    {
        return this._displayName;
    } //-- java.lang.String getDisplayName() 

    /**
     * Returns the value of field 'userName'.
     * 
     * @return the value of field 'userName'.
    **/
    public java.lang.String getUserName()
    {
        return this._userName;
    } //-- java.lang.String getUserName() 

    /**
     * Returns the value of field 'userRight'.
     * 
     * @return the value of field 'userRight'.
    **/
    public java.lang.String getUserRight()
    {
        return this._userRight;
    } //-- java.lang.String getUserRight() 

    /**
     * Returns the value of field 'userid'.
     * 
     * @return the value of field 'userid'.
    **/
    public java.lang.String getUserid()
    {
        return this._userid;
    } //-- java.lang.String getUserid() 

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
     * Sets the value of field 'displayName'.
     * 
     * @param displayName the value of field 'displayName'.
    **/
    public void setDisplayName(java.lang.String displayName)
    {
        this._displayName = displayName;
    } //-- void setDisplayName(java.lang.String) 

    /**
     * Sets the value of field 'userName'.
     * 
     * @param userName the value of field 'userName'.
    **/
    public void setUserName(java.lang.String userName)
    {
        this._userName = userName;
    } //-- void setUserName(java.lang.String) 

    /**
     * Sets the value of field 'userRight'.
     * 
     * @param userRight the value of field 'userRight'.
    **/
    public void setUserRight(java.lang.String userRight)
    {
        this._userRight = userRight;
    } //-- void setUserRight(java.lang.String) 

    /**
     * Sets the value of field 'userid'.
     * 
     * @param userid the value of field 'userid'.
    **/
    public void setUserid(java.lang.String userid)
    {
        this._userid = userid;
    } //-- void setUserid(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.User unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.User) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.User.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.User unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
