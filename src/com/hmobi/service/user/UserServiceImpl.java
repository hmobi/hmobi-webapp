package com.hmobi.service.user;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserSignUp;
import com.hmobi.datamanager.DataManagerFactory;
import com.hmobi.datamanager.user.UserDataManager;

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
    private UserDataManager userDataManager = DataManagerFactory.getDataManager(UserDataManager.class);

    public UserLogin loadUserByUsername(String userName)
    {
        return userDataManager.loadUserByUsername(userName);
    }

    public List<Address> getAddresses(String location)
    {
        return userDataManager.getAddresses(location);
    }
    public void signUpUser(UserSignUp usu)
    {
    	userDataManager.signUpUser(usu);
    }
}
