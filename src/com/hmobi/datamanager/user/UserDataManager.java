package com.hmobi.datamanager.user;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserSignUp;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDataManager
{
    public UserLogin loadUserByUsername(String userName);
    public List<Address> getAddresses(String location);
    public void signUpUser(UserSignUp usu);
}
