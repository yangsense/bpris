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
import java.util.Enumeration;
import java.util.Vector;
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

    private java.util.Vector _userList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Data() {
        super();
        _userList = new Vector();
    } //-- com.ai.aris.server.webservice.model.hst.Data()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vUser
    **/
    public void addUser(User vUser)
        throws java.lang.IndexOutOfBoundsException
    {
        _userList.addElement(vUser);
    } //-- void addUser(User) 

    /**
     * 
     * 
     * @param index
     * @param vUser
    **/
    public void addUser(int index, User vUser)
        throws java.lang.IndexOutOfBoundsException
    {
        _userList.insertElementAt(vUser, index);
    } //-- void addUser(int, User) 

    /**
    **/
    public java.util.Enumeration enumerateUser()
    {
        return _userList.elements();
    } //-- java.util.Enumeration enumerateUser() 

    /**
     * 
     * 
     * @param index
    **/
    public User getUser(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _userList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (User) _userList.elementAt(index);
    } //-- User getUser(int) 

    /**
    **/
    public User[] getUser()
    {
        int size = _userList.size();
        User[] mArray = new User[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (User) _userList.elementAt(index);
        }
        return mArray;
    } //-- User[] getUser() 

    /**
    **/
    public int getUserCount()
    {
        return _userList.size();
    } //-- int getUserCount() 

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
    **/
    public void removeAllUser()
    {
        _userList.removeAllElements();
    } //-- void removeAllUser() 

    /**
     * 
     * 
     * @param index
    **/
    public User removeUser(int index)
    {
        java.lang.Object obj = _userList.elementAt(index);
        _userList.removeElementAt(index);
        return (User) obj;
    } //-- User removeUser(int) 

    /**
     * 
     * 
     * @param index
     * @param vUser
    **/
    public void setUser(int index, User vUser)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _userList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _userList.setElementAt(vUser, index);
    } //-- void setUser(int, User) 

    /**
     * 
     * 
     * @param userArray
    **/
    public void setUser(User[] userArray)
    {
        //-- copy array
        _userList.removeAllElements();
        for (int i = 0; i < userArray.length; i++) {
            _userList.addElement(userArray[i]);
        }
    } //-- void setUser(User) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.Data unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.Data) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.Data.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.Data unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
