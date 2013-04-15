package com.hmobi.db.mysql;

import com.hmobi.db.DBHandler;
import com.hmobi.db.objects.user.DBUser;
import com.hmobi.db.util.QueryUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 08/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class MySqlDBHandler implements DBHandler
{
    private static Logger logger = LoggerFactory.getLogger(MySqlDBHandler.class.getCanonicalName());

    private static MySqlDBHandler mySqlDBHandler;

    private ComboPooledDataSource connPool;

    private MySqlDBHandler(int maxActive)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connPool = new ComboPooledDataSource();
            connPool.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/hMOBI" + "?autoReconnect=true&characterEncoding=UTF-8");
            connPool.setUser("root");
            connPool.setPassword("root");
            connPool.setMaxStatementsPerConnection(100);
            connPool.setMaxPoolSize(maxActive);
            connPool.setAutoCommitOnClose(true);
            logger.info("Connection pool is established. pool:"+connPool);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MySqlDBHandler getInstance()
    {
        if(mySqlDBHandler == null)
        {
            synchronized (MySqlDBHandler.class)
            {
                if(mySqlDBHandler == null)
                {
                    mySqlDBHandler = new MySqlDBHandler(100);
                }
            }
        }

        return mySqlDBHandler;
    }

    public DBUser getDBUser(String username)
    {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = (Connection)connPool.getConnection();
            st = conn.prepareStatement(QueryUtil.GET_USER);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                String uname = rs.getString("username");
                String password = rs.getString("password");
                DBUser dbUser = new DBUser();
                dbUser.setUsername(uname);
                dbUser.setPassword(password);
                return dbUser;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try
            {
                conn.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
