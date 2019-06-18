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

    private java.util.Vector _roomList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Data() {
        super();
        _roomList = new Vector();
    } //-- com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Data()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vRoom
    **/
    public void addRoom(Room vRoom)
        throws java.lang.IndexOutOfBoundsException
    {
        _roomList.addElement(vRoom);
    } //-- void addRoom(Room) 

    /**
     * 
     * 
     * @param index
     * @param vRoom
    **/
    public void addRoom(int index, Room vRoom)
        throws java.lang.IndexOutOfBoundsException
    {
        _roomList.insertElementAt(vRoom, index);
    } //-- void addRoom(int, Room) 

    /**
    **/
    public java.util.Enumeration enumerateRoom()
    {
        return _roomList.elements();
    } //-- java.util.Enumeration enumerateRoom() 

    /**
     * 
     * 
     * @param index
    **/
    public Room getRoom(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _roomList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (Room) _roomList.elementAt(index);
    } //-- Room getRoom(int) 

    /**
    **/
    public Room[] getRoom()
    {
        int size = _roomList.size();
        Room[] mArray = new Room[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (Room) _roomList.elementAt(index);
        }
        return mArray;
    } //-- Room[] getRoom() 

    /**
    **/
    public int getRoomCount()
    {
        return _roomList.size();
    } //-- int getRoomCount() 

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
    public void removeAllRoom()
    {
        _roomList.removeAllElements();
    } //-- void removeAllRoom() 

    /**
     * 
     * 
     * @param index
    **/
    public Room removeRoom(int index)
    {
        java.lang.Object obj = _roomList.elementAt(index);
        _roomList.removeElementAt(index);
        return (Room) obj;
    } //-- Room removeRoom(int) 

    /**
     * 
     * 
     * @param index
     * @param vRoom
    **/
    public void setRoom(int index, Room vRoom)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _roomList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _roomList.setElementAt(vRoom, index);
    } //-- void setRoom(int, Room) 

    /**
     * 
     * 
     * @param roomArray
    **/
    public void setRoom(Room[] roomArray)
    {
        //-- copy array
        _roomList.removeAllElements();
        for (int i = 0; i < roomArray.length; i++) {
            _roomList.addElement(roomArray[i]);
        }
    } //-- void setRoom(Room) 

    /**
     * 
     * 
     * @param reader
    **/
    public static com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Data unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Data) Unmarshaller.unmarshal(com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Data.class, reader);
    } //-- com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Data unmarshal(java.io.Reader) 

    /**
    **/
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
