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
public class User implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _adminRole;

    private java.lang.String _departID;

    private java.lang.String _displayName;

    private java.lang.String _email;

    private java.lang.String _mobile;

    private java.lang.String _password;

    private java.lang.String _sex;

    private java.lang.String _telePhone;

    private java.lang.String _userName;


      //----------------/
     //- Constructors -/
    //----------------/

    public User() {
        super();
    } //-- com.ai.aris.server.webservice.model.hst.addUserinfo.User()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'adminRole'.
     * 
     * @return the value of field 'adminRole'.
    **/
    public java.lang.String getAdminRole()
    {
        return this._adminRole;
    } //-- java.lang.String getAdminRole() 

    /**
     * Returns the value of field 'departID'.
     * 
     * @return the value of field 'departID'.
    **/
    public java.lang.String getDepartID()
    {
        return this._departID;
    } //-- java.lang.String getDepartID() 

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
     * Returns the value of field 'email'.
     * 
     * @return the value of field 'email'.
    **/
    public java.lang.String getEmail()
    {
        return this._email;
    } //-- java.lang.String getEmail() 

    /**
     * Returns the value of field 'mobile'.
     * 
     * @return the value of field 'mobile'.
    **/
    public java.lang.String getMobile()
    {
        return this._mobile;
    } //-- java.lang.String getMobile() 

    /**
     * Returns the value of field 'password'.
     * 
     * @return the value of field 'password'.
    **/
    public java.lang.String getPassword()
    {
        return this._password;
    } //-- java.lang.String getPassword() 

    /**
     * Returns the value of field 'sex'.
     * 
     * @return the value of field 'sex'.
    **/
    public java.lang.String getSex()
    {
        return this._sex;
    } //-- java.lang.String getSex() 

    /**
     * Returns the value of field 'telePhone'.
     * 
     * @return the value of field 'telePhone'.
    **/
    public java.lang.String getTelePhone()
    {
        return this._telePhone;
    } //-- java.lang.String getTelePhone() 

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
     * Sets the value of field 'adminRole'.
     * 
     * @param adminRole the value of field 'adminRole'.
    **/
    public void setAdminRole(java.lang.String adminRole)
    {
        this._adminRole = adminRole;
    } //-- void setAdminRole(java.lang.String) 

    /**
     * Sets the value of field 'departID'.
     * 
     * @param departID the value of field 'departID'.
    **/
    public void setDepartID(java.lang.String departID)
    {
        this._departID = departID;
    } //-- void setDepartID(java.lang.String) 

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
     * Sets the value of field 'email'.
     * 
     * @param email the value of field 'email'.
    **/
    public void setEmail(java.lang.String email)
    {
        this._email = email;
    } //-- void setEmail(java.lang.String) 

    /**
     * Sets the value of field 'mobile'.
     * 
     * @param mobile the value of field 'mobile'.
    **/
    public void setMobile(java.lang.String mobile)
    {
        this._mobile = mobile;
    } //-- void setMobile(java.lang.String) 

    /**
     * Sets the value of field 'password'.
     * 
     * @param password the value of field 'password'.
    **/
    public void setPassword(java.lang.String password)
    {
        this._password = password;
    } //-- void setPassword(java.lang.String) 

    /**
     * Sets the value of field 'sex'.
     * 
     * @param sex the value of field 'sex'.
    **/
    public void setSex(java.lang.String sex)
    {
        this._sex = sex;
    } //-- void setSex(java.lang.String) 

    /**
     * Sets the value of field 'telePhone'.
     * 
     * @param telePhone the value of field 'telePhone'.
    **/
    public void setTelePhone(java.lang.String telePhone)
    {
        this._telePhone = telePhone;
    } //-- void setTelePhone(java.lang.String) 

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
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.addUserinfo.User unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.addUserinfo.User) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.addUserinfo.User.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.addUserinfo.User unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
