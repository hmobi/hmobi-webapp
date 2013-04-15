package com.hmobi.servlet;

import com.hmobi.dao.user.UserLogin;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 28/03/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HMMainServlet extends HttpServlet
{

    public void init(ServletConfig config) throws javax.servlet.ServletException
    {
        super.init(config);
        System.out.println("Initialized HMMainServlet...");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String requestUri = request.getRequestURI();
        System.out.println("URI : " + requestUri);

        UserLogin user = (UserLogin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(requestUri.contains("/secure"))
        {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/default.jsp?user=" + user.getUsername());
            rd.forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String requestUri = request.getRequestURI();
        System.out.println("URI : " + requestUri);
        User user = (org.acegisecurity.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(requestUri.contains("/secure/login"))
        {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/default.jsp?user=" + user.getUsername());
            rd.forward(request, response);
        }
    }
}
