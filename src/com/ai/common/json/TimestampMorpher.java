package com.ai.common.json;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class TimestampMorpher extends  AbstractObjectMorpher {  
	  
	/*** 日期字符串格式*/  
    private String[] formats = null;  
  
    public TimestampMorpher(String[] formats) {  
        this.formats = formats;  
    }  
  
    public Object morph(Object value) {  
        if( value == null){  
            return null;  
        }  
        if( Timestamp.class.isAssignableFrom(value.getClass()) ){  
            return (Timestamp) value;  
        }  
        if( !supports( value.getClass()) ){  
            throw new MorphException( value.getClass() + " 是不支持的类型");  
        }  
        if(value instanceof  Long){  
            Long time = (Long)value;  
            return new Timestamp(time);  
        }else if(value instanceof String) {  
            String strValue = (String) value;  
            SimpleDateFormat dateParser = null;  
            for (int i = 0; i < formats.length; i++) {  
                if (null == dateParser) {  
                    dateParser = new SimpleDateFormat(formats[i]);  
                } else {  
                    dateParser.applyPattern(formats[i]);  
                }  
                try {  
                    return new Timestamp(dateParser.parse(strValue.toLowerCase()).getTime());  
                } catch (ParseException e) {  
                    //e.printStackTrace();  
                }  
            }  
        }  
        return null;  
    }  
    @Override  
    public Class morphsTo() {  
        return Timestamp.class;  
    }  
    public boolean supports( Class clazz ){  
        // Long to Timestamp  
        // String  to Timestamp  
        return (Long.class.isAssignableFrom( clazz ) | String.class.isAssignableFrom( clazz ));  
    }   
  
} 
