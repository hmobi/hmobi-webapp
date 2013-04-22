package com.hmobi.db;

import com.hmobi.dao.user.UserSignUp;
import com.hmobi.db.objects.user.DBAddress;
import com.hmobi.db.objects.user.DBUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 08/04/13
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DBManager
{
    public DBUser getDBUser(String username);
    public List<DBAddress> getDBAddresses(String location);
    public void signUpUser(DBUser usu);

}
