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
public class Room implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    private java.lang.String _curUserCount;

    private java.lang.String _hopeEndTime;

    private java.lang.String _hopeStartTime;

    private java.lang.String _maxUserCount;

    private java.lang.String _roomId;

    private java.lang.String _roomName;

    private java.lang.String _roomType;

    private java.lang.String _verifyMode;


      //----------------/
     //- Constructors -/
    //----------------/

    public Room() {
        super();
    } //-- com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'curUserCount'.
     * 
     * @return the value of field 'curUserCount'.
    **/
    public java.lang.String getCurUserCount()
    {
        return this._curUserCount;
    } //-- java.lang.String getCurUserCount() 

    /**
     * Returns the value of field 'hopeEndTime'.
     * 
     * @return the value of field 'hopeEndTime'.
    **/
    public java.lang.String getHopeEndTime()
    {
        return this._hopeEndTime;
    } //-- java.lang.String getHopeEndTime() 

    /**
     * Returns the value of field 'hopeStartTime'.
     * 
     * @return the value of field 'hopeStartTime'.
    **/
    public java.lang.String getHopeStartTime()
    {
        return this._hopeStartTime;
    } //-- java.lang.String getHopeStartTime() 

    /**
     * Returns the value of field 'maxUserCount'.
     * 
     * @return the value of field 'maxUserCount'.
    **/
    public java.lang.String getMaxUserCount()
    {
        return this._maxUserCount;
    } //-- java.lang.String getMaxUserCount() 

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
     * Returns the value of field 'roomName'.
     * 
     * @return the value of field 'roomName'.
    **/
    public java.lang.String getRoomName()
    {
        return this._roomName;
    } //-- java.lang.String getRoomName() 

    /**
     * Returns the value of field 'roomType'.
     * 
     * @return the value of field 'roomType'.
    **/
    public java.lang.String getRoomType()
    {
        return this._roomType;
    } //-- java.lang.String getRoomType() 

    /**
     * Returns the value of field 'verifyMode'.
     * 
     * @return the value of field 'verifyMode'.
    **/
    public java.lang.String getVerifyMode()
    {
        return this._verifyMode;
    } //-- java.lang.String getVerifyMode() 

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
     * Sets the value of field 'curUserCount'.
     * 
     * @param curUserCount the value of field 'curUserCount'.
    **/
    public void setCurUserCount(java.lang.String curUserCount)
    {
        this._curUserCount = curUserCount;
    } //-- void setCurUserCount(java.lang.String) 

    /**
     * Sets the value of field 'hopeEndTime'.
     * 
     * @param hopeEndTime the value of field 'hopeEndTime'.
    **/
    public void setHopeEndTime(java.lang.String hopeEndTime)
    {
        this._hopeEndTime = hopeEndTime;
    } //-- void setHopeEndTime(java.lang.String) 

    /**
     * Sets the value of field 'hopeStartTime'.
     * 
     * @param hopeStartTime the value of field 'hopeStartTime'.
    **/
    public void setHopeStartTime(java.lang.String hopeStartTime)
    {
        this._hopeStartTime = hopeStartTime;
    } //-- void setHopeStartTime(java.lang.String) 

    /**
     * Sets the value of field 'maxUserCount'.
     * 
     * @param maxUserCount the value of field 'maxUserCount'.
    **/
    public void setMaxUserCount(java.lang.String maxUserCount)
    {
        this._maxUserCount = maxUserCount;
    } //-- void setMaxUserCount(java.lang.String) 

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
     * Sets the value of field 'roomName'.
     * 
     * @param roomName the value of field 'roomName'.
    **/
    public void setRoomName(java.lang.String roomName)
    {
        this._roomName = roomName;
    } //-- void setRoomName(java.lang.String) 

    /**
     * Sets the value of field 'roomType'.
     * 
     * @param roomType the value of field 'roomType'.
    **/
    public void setRoomType(java.lang.String roomType)
    {
        this._roomType = roomType;
    } //-- void setRoomType(java.lang.String) 

    /**
     * Sets the value of field 'verifyMode'.
     * 
     * @param verifyMode the value of field 'verifyMode'.
    **/
    public void setVerifyMode(java.lang.String verifyMode)
    {
        this._verifyMode = verifyMode;
    } //-- void setVerifyMode(java.lang.String) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
