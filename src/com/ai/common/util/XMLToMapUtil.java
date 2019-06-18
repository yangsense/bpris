package com.ai.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLToMapUtil {

     //String转Map
	 public static Map<String, Object> Dom2Map(String xml){ 
		 
		    Document doc = null;
			try {			 
				 doc = DocumentHelper.parseText(xml); 
			} catch (Exception e) {
				// TODO: handle exception
			}			
	        Map<String, Object> map = new HashMap<String, Object>();   
	        if(doc == null)   
	            return map;   
	        Element root = doc.getRootElement();   
	        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {   
	            Element e = (Element) iterator.next();   
	            //System.out.println(e.getName());   
	            List list = e.elements();   
	            if(list.size() > 0){   
	                map.put(e.getName(), Dom2Map(e));   
	            }else  
	                map.put(e.getName(), e.getText());   
	        }   
	        return map;   
	    }   
	 //File转Map   
	 public static Map<String, Object> xmlToMap(File file){ 
		 	SAXReader reader = new SAXReader();
		    Document doc = null;
			try {			 
				 doc = reader.read(file);
			} catch (Exception e) {
				// TODO: handle exception
			}			
	        Map<String, Object> map = new HashMap<String, Object>();   
	        if(doc == null)   
	            return map;   
	        Element root = doc.getRootElement();   
	        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {   
	            Element e = (Element) iterator.next();   
	            //System.out.println(e.getName());   
	            List list = e.elements();   
	            if(list.size() > 0){   
	                map.put(e.getName(), Dom2Map(e));   
	            }else  
	                map.put(e.getName(), e.getText());   
	        }   
	        return map;   
	    }   
	  
	    public static Map Dom2Map(Element e){   
	        Map map = new HashMap();   
	        List list = e.elements();   
	        if(list.size() > 0){   
	            for (int i = 0;i < list.size(); i++) {   
	                Element iter = (Element) list.get(i);   
	                List mapList = new ArrayList();   
	                   
	                if(iter.elements().size() > 0){   
	                    Map m = Dom2Map(iter);   
	                    if(map.get(iter.getName()) != null){   
	                        Object obj = map.get(iter.getName());   
	                        if(!obj.getClass().getName().equals("java.util.ArrayList")){   
	                            mapList = new ArrayList();   
	                            mapList.add(obj);   
	                            mapList.add(m);   
	                        }   
	                        if(obj.getClass().getName().equals("java.util.ArrayList")){   
	                            mapList = (List) obj;   
	                            mapList.add(m);   
	                        }   
	                        map.put(iter.getName(), mapList);   
	                    }else  
	                        map.put(iter.getName(), m);   
	                }   
	                else{   
	                    if(map.get(iter.getName()) != null){   
	                        Object obj = map.get(iter.getName());   
	                        if(!obj.getClass().getName().equals("java.util.ArrayList")){   
	                            mapList = new ArrayList();   
	                            mapList.add(obj);   
	                            mapList.add(iter.getText());   
	                        }   
	                        if(obj.getClass().getName().equals("java.util.ArrayList")){   
	                            mapList = (List) obj;   
	                            mapList.add(iter.getText());   
	                        }   
	                        map.put(iter.getName(), mapList);   
	                    }else  
	                        map.put(iter.getName(), iter.getText());   
	                }   
	            }   
	        }else  
	            map.put(e.getName(), e.getText());   
	        return map;   
	    }   
	     
}
