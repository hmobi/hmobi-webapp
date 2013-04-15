package com.hmobi.dbmanager.user;

import com.hmobi.dao.user.UserLogin;
import com.hmobi.db.DBHandler;
import com.hmobi.db.mysql.MySqlDBHandler;
import com.hmobi.db.objects.user.DBUser;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDBManagerImpl implements UserDBManager
{
    private DBHandler dbHandler = MySqlDBHandler.getInstance();

    public UserLogin loadUserByUsername(String userName)
    {
        DBUser dbUser = dbHandler.getDBUser(userName);
        if(dbUser == null)
        {
            return null;

        }
        UserLogin userLogin = new UserLogin();
        userLogin.fillUser(dbUser);
        return userLogin;
    }
}
