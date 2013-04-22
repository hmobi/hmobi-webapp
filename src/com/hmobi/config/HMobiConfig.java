package com.hmobi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 22/04/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class HMobiConfig
{
    private static Logger logger = LoggerFactory.getLogger(HMobiConfig.class);
    private static HMobiConfig config = new HMobiConfig();

    private Properties properties;
    private String propertyFileName;

    private String mysqlDbUrl;
    private String mysqlUsername;
    private String mysqlPassword;

    private HMobiConfig()
    {

    }

    public static HMobiConfig getInstance()
    {
        return config;
    }

    public void loadProperties(String propsFile)
    {
        propertyFileName = propsFile;
        properties = new Properties();
        try
        {
            logger.info("Loading properties from " + propertyFileName);
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propsFile);
            if (inputStream != null)
            {
                properties.load(inputStream);
            }
        }
        catch (Throwable e)
        {
            logger.warn("Error loading " + propertyFileName + ", using defaults. Error reason : " + e.getMessage(), e);
        }

        mysqlDbUrl = getStringProperty(properties,"mysql.dburl","jdbc:mysql://127.0.0.1:3306/hMOBI?autoReconnect=true&characterEncoding=UTF-8");
        mysqlUsername = getStringProperty(properties,"mysql.username","root");
        mysqlPassword = getStringProperty(properties,"mysql.password","root");
    }

    protected int getIntProperty(Properties props, String propName, int defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    protected long getLongProperty(Properties props, String propName, long defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    protected float getFloatProperty(Properties props, String propName, float defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            return defaultValue;
        }
        return Float.parseFloat(value);
    }

    protected boolean getBooleanProperty(Properties props, String propName, boolean defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    protected String getStringProperty(Properties props, String propName, String defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            return defaultValue;
        }
        return value;
    }

    protected double getDoubleProperty(Properties props, String propName, double defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            return defaultValue;
        }
        return Double.parseDouble(value);
    }

    protected List<String> getListProperty(Properties props, String propName, String defaultValue)
    {
        String value = props.getProperty(propName);
        if (value == null || value.isEmpty())
        {
            if ((defaultValue == null) || defaultValue.isEmpty())
            {
                return new ArrayList<String>();
            }
            else
            {
                value = defaultValue;
            }
        }
        return Arrays.asList(value.split(","));

    }

    public String getMysqlDbUrl()
    {
        return mysqlDbUrl;
    }

    public String getMysqlUsername()
    {
        return mysqlUsername;
    }

    public String getMysqlPassword()
    {
        return mysqlPassword;
    }
}
