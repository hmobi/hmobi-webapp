package com.hmobi.dao.user;

import com.hmobi.db.objects.user.DBAddress;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 17/04/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Address
{
    private String address;
    private String address2;
    private String state;
    private String city;
    private String country;

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void fill(DBAddress dbAddress)
    {
        this.address = dbAddress.getAddress();
        this.address2 = dbAddress.getAddress2();
        this.state = dbAddress.getState();
        this.city = dbAddress.getCity();
        this.country = dbAddress.getCountry();
    }
}
