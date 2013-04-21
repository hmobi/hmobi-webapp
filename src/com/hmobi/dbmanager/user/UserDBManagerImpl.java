package com.hmobi.dbmanager.user;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserSignUp;
import com.hmobi.db.DBHandler;
import com.hmobi.db.mysql.MySqlDBHandler;
import com.hmobi.db.objects.user.DBAddress;
import com.hmobi.db.objects.user.DBUser;
import com.hmobi.dbmanager.AbstractDBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDBManagerImpl extends AbstractDBManager implements UserDBManager
{
    public UserLogin loadUserByUsername(String userName)
    {
        DBUser dbUser = getDbHandler().getDBUser(userName);
        if(dbUser == null)
        {
            return null;

        }
        UserLogin userLogin = new UserLogin();
        userLogin.fill(dbUser);
        return userLogin;
    }

    public List<Address> getDBAddresses(String location)
    {
        List<DBAddress> addresses = getDbHandler().getDBAddresses(location);

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
    	
    	getDbHandler().signUpUser(dbUser);
    }
}
