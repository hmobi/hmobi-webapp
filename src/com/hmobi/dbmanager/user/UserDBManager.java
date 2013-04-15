package com.hmobi.dbmanager.user;

import com.hmobi.dao.user.UserLogin;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDBManager
{
    public UserLogin loadUserByUsername(String userName);
}
