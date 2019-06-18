package com.ai.aris.web.listener;

import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.bean.UserSessionAdd;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Walter on 2017/8/7.\
 * 登录监听器
 */
public class LoginListener implements HttpSessionAttributeListener{

    Map<String, HttpSession> map = new HashMap<String, HttpSession>();

    public void attributeAdded(HttpSessionBindingEvent event) {

        String name = event.getName();

        UserSessionAdd usa = (UserSessionAdd)event.getValue();
        if(map.get(usa.getAdd())!=null){
            HttpSession sess = map.get(usa.getAdd());
            UserSessionAdd usa1 = (UserSessionAdd)sess.getAttribute("usa");
            sess.removeAttribute("usa");
            sess.invalidate();
        }

        map.put(usa.getAdd(), event.getSession());



    }

    public void attributeRemoved(HttpSessionBindingEvent event) {

        String name = event.getName();
        if(name.equals("usa")){
            UserSessionAdd usa = (UserSessionAdd)event.getValue();
            map.remove(usa.getAdd());

        }

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
