package com.hmobi.datamanager.user;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserSignUp;
import com.hmobi.db.objects.user.DBAddress;
import com.hmobi.db.objects.user.DBUser;
import com.hmobi.datamanager.AbstractDataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDataManagerImpl extends AbstractDataManager implements UserDataManager
{
    public UserLogin loadUserByUsername(String userName)
    {
        DBUser dbUser = getDBManager().getDBUser(userName);
        if(dbUser == null)
        {
            return null;
        }
        UserLogin userLogin = new UserLogin();
        userLogin.fill(dbUser);
        return userLogin;
    }

    public List<Address> getAddresses(String location)
    {
        List<DBAddress> addresses = getDBManager().getDBAddresses(location);

        if(addresses == null)
            return null;
        List<Address> retAddrs = new ArrayList<Address>();
        for(DBAddress address: addresses)
        {
            Address addr = new Address();
            addr.fill(address);
            retAddrs.add(addr);

        }
        return retAddrs;
    }
    public void signUpUser(UserSignUp usu)
    {
    	DBUser dbUser = new DBUser();
    	dbUser.setEmail(usu.getEmail());
    	dbUser.setUsername(usu.getUsername());
    	dbUser.setPassword(usu.getPassword());
    	
    	getDBManager().signUpUser(dbUser);
    }
}
