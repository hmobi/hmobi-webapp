package com.hmobi.dao.user;

import org.acegisecurity.GrantedAuthority;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserPermissions implements GrantedAuthority
{
    private String authority;

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}
