package com.hmobi.dao.user;

import com.hmobi.db.objects.user.DBUser;
import org.acegisecurity.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 10/04/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserLogin implements UserDetails
{
    private String username;
    private String password;
    private UserPermissions[] permissions;

    public UserPermissions[] getAuthorities()
    {
        return permissions;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean isAccountNonExpired()
    {
        return true;
    }

    public boolean isAccountNonLocked()
    {
        return true;
    }

    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public boolean isEnabled()
    {
        return true;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPermissions(UserPermissions[] permissions)
    {
        this.permissions = permissions;
    }

    public void fillUser(DBUser dbUser)
    {
        this.username = dbUser.getUsername();
        this.password = dbUser.getPassword();
        UserPermissions p = new UserPermissions();
        p.setAuthority("ROLE_USER");
        this.permissions = new UserPermissions[1];
        this.permissions[0] = p;
    }
}
