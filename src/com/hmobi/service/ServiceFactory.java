package com.hmobi.service;

import com.hmobi.service.user.UserService;
import com.hmobi.service.user.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceFactory
{
    private static Map<Class<?>,Object> serviceMap = new HashMap<Class<?>,Object>();

    static{
        serviceMap.put(UserService.class, new UserServiceImpl());
    }

    public static <T> T getService(Class<?> cl)
    {
        Object obj = serviceMap.get(cl);
        if(obj == null)
            return null;
        return (T)obj;
    }
}
