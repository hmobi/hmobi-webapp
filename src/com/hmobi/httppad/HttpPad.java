package com.hmobi.httppad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 15/04/13
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpPad
{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpPad(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
    }

    public String getParameter(String param)
    {
        return request.getParameter(param);
    }

    public int getIntParameter(String param)
    {
        String value = request.getParameter(param);
        if(value == null)
            return 0;
        return Integer.valueOf(value);
    }

    public void sendRedirect(String url) throws Exception
    {
        sendAbsoluteRedirect(request.getContextPath() + "/" + url);
    }

    public void sendAbsoluteRedirect(String url) throws Exception
    {
        response.sendRedirect(url);
    }

    public String getServletPath()
    {
        return request.getServletPath();
    }

    public String getSessionId()
    {
        return request.getSession().getId();
    }
}
