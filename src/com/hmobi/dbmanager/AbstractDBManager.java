package com.hmobi.dbmanager;

import com.hmobi.db.DBHandler;
import com.hmobi.db.mysql.MySqlDBHandler;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 17/04/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractDBManager
{
    protected DBHandler getDbHandler()
    {
        return MySqlDBHandler.getInstance();
    }
}
