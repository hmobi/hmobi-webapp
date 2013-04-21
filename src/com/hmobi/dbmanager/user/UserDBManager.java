package com.hmobi.dbmanager.user;

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
public interface UserDBManager
{
    public UserLogin loadUserByUsername(String userName);
    public List<Address> getDBAddresses(String location);
    public void signUpUser(UserSignUp usu);
}
