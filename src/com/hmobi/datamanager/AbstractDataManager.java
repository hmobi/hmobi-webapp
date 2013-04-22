package com.hmobi.datamanager;

import com.hmobi.context.ContextManager;
import com.hmobi.db.DBManager;
import com.hmobi.db.mysql.MySqlDBManager;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 17/04/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractDataManager
{
    protected DBManager getDBManager()
    {
        return ContextManager.getInstance().getBean(DBManager.class);
    }
}
