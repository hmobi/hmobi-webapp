package com.hmobi.dbmanager;

import com.hmobi.dbmanager.user.UserDBManager;
import com.hmobi.dbmanager.user.UserDBManagerImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBManagerFactory
{
    private static Map<Class<?>,Object> dbManagerMap = new HashMap<Class<?>,Object>();

    static {
        dbManagerMap.put(UserDBManager.class,new UserDBManagerImpl());
    }

    public static <T> T getDBManager(Class<T> cl)
    {
        Object obj = dbManagerMap.get(cl);
        if(obj == null)
            return null;
        return (T) obj;
    }
}
