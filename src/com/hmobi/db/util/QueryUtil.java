package com.hmobi.db.util;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryUtil
{
    public static final String GET_USER = "select * from user where username=?";

    public static final String GET_ADDRESSES = "select * from address where city=?";
    
    public static final String ADD_USER = "insert into user (email, username, password) values (?, ?, ?)";
}
