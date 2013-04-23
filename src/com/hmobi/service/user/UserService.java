package com.hmobi.service.user;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserSignUp;

import org.acegisecurity.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService extends UserDetailsService
{
    public List<Address> getAddresses(String location);
    public UserLogin signUpUser(UserSignUp usu);
}
