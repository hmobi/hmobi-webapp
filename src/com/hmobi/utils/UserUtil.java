package com.hmobi.utils;

import com.hmobi.dao.user.UserLogin;
import org.acegisecurity.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 15/04/13
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserUtil
{
    public static UserLogin getLoggedInUser()
    {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof UserLogin)
        {
            return (UserLogin)obj;
        }
        return null;
    }
}
