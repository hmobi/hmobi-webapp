package com.hmobi.service.user;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dbmanager.DBManagerFactory;
import com.hmobi.dbmanager.user.UserDBManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService
{
    private UserDBManager userDBManager = DBManagerFactory.getDBManager(UserDBManager.class);

    public UserLogin loadUserByUsername(String userName)
    {
        return userDBManager.loadUserByUsername(userName);
    }

    public List<Address> getAddresses(String location)
    {
        return userDBManager.getDBAddresses(location);
    }
}
