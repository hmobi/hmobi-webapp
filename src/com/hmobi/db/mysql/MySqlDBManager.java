package com.hmobi.db.mysql;

import com.hmobi.config.HMobiConfig;
import com.hmobi.db.DBManager;
import com.hmobi.db.objects.user.DBAddress;
import com.hmobi.db.objects.user.DBUser;
import com.hmobi.db.util.QueryUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 08/04/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class MySqlDBManager implements DBManager
{
    private static Logger logger = LoggerFactory.getLogger(MySqlDBManager.class.getCanonicalName());

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DBUser getDBUser(String username)
    {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
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

    public List<DBAddress> getDBAddresses(String location)
    {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
            st = conn.prepareStatement(QueryUtil.GET_ADDRESSES);
            st.setString(1, location);
            ResultSet rs = st.executeQuery();
            List<DBAddress> addresses = new ArrayList<DBAddress>();
            while(rs.next())
            {
                String address = rs.getString("address");
                String address2 = rs.getString("address2");
                String state = rs.getString("state");
                String city = rs.getString("city");
                String country = rs.getString("country");
                DBAddress dbAddress = new DBAddress();
                dbAddress.setAddress(address);
                dbAddress.setAddress2(address2);
                dbAddress.setState(state);
                dbAddress.setCity(city);
                dbAddress.setCountry(country);
                addresses.add(dbAddress);
            }
            return addresses;

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

    @Transactional
    public void signUpUser(DBUser usu)
    {
        this.jdbcTemplate.update(QueryUtil.ADD_USER, usu.getEmail(), usu.getUsername(),usu.getPassword());
    }
}
