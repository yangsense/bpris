/*
 * This class was automatically generated with 
 * <a href="http://castor.exolab.org">Castor 0.9.4</a>, using an
 * XML Schema.
 * $Id$
 */

package com.ai.aris.server.webservice.model.hst.doRoomRightByUserName;

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
public class Roomuser implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _online;

    private java.lang.String _roomId;

    private java.lang.String _seatList;

    private java.lang.String _userId;

    private java.lang.String _userRight;


      //----------------/
     //- Constructors -/
    //----------------/

    public Roomuser() {
        super();
    } //-- com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Roomuser()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'online'.
     * 
     * @return the value of field 'online'.
    **/
    public java.lang.String getOnline()
    {
        return this._online;
    } //-- java.lang.String getOnline() 

    /**
     * Returns the value of field 'roomId'.
     * 
     * @return the value of field 'roomId'.
    **/
    public java.lang.String getRoomId()
    {
        return this._roomId;
    } //-- java.lang.String getRoomId() 

    /**
     * Returns the value of field 'seatList'.
     * 
     * @return the value of field 'seatList'.
    **/
    public java.lang.String getSeatList()
    {
        return this._seatList;
    } //-- java.lang.String getSeatList() 

    /**
     * Returns the value of field 'userId'.
     * 
     * @return the value of field 'userId'.
    **/
    public java.lang.String getUserId()
    {
        return this._userId;
    } //-- java.lang.String getUserId() 

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
     * Sets the value of field 'online'.
     * 
     * @param online the value of field 'online'.
    **/
    public void setOnline(java.lang.String online)
    {
        this._online = online;
    } //-- void setOnline(java.lang.String) 

    /**
     * Sets the value of field 'roomId'.
     * 
     * @param roomId the value of field 'roomId'.
    **/
    public void setRoomId(java.lang.String roomId)
    {
        this._roomId = roomId;
    } //-- void setRoomId(java.lang.String) 

    /**
     * Sets the value of field 'seatList'.
     * 
     * @param seatList the value of field 'seatList'.
    **/
    public void setSeatList(java.lang.String seatList)
    {
        this._seatList = seatList;
    } //-- void setSeatList(java.lang.String) 

    /**
     * Sets the value of field 'userId'.
     * 
     * @param userId the value of field 'userId'.
    **/
    public void setUserId(java.lang.String userId)
    {
        this._userId = userId;
    } //-- void setUserId(java.lang.String) 

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
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Roomuser unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Roomuser) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Roomuser.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Roomuser unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
