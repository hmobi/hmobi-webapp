package com.hmobi.datamanager;

import com.hmobi.datamanager.user.UserDataManager;
import com.hmobi.datamanager.user.UserDataManagerImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataManagerFactory
{
    private static Map<Class<?>,Object> dataManagerMap = new HashMap<Class<?>,Object>();

    static {
        dataManagerMap.put(UserDataManager.class, new UserDataManagerImpl());
    }

    public static <T> T getDataManager(Class<T> cl)
    {
        Object obj = dataManagerMap.get(cl);
        if(obj == null)
            return null;
        return (T) obj;
    }
}
