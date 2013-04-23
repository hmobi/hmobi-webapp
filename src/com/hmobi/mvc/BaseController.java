package com.hmobi.mvc;

import com.hmobi.dao.user.UserLogin;
import com.hmobi.httppad.HttpPad;
import com.hmobi.service.ServiceFactory;
import com.hmobi.service.user.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 15/04/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController extends AbstractController
{
    protected UserService userService = ServiceFactory.getService(UserService.class);

    @Override
    protected HMModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception
    {
        HttpPad pad = new HttpPad(request, response);
        return handleRequest(pad);
    }

    protected void redirectUserBasedOnRole(UserLogin user, HttpPad pad) throws Exception
    {
        if("ROLE_USER".equals(user.getAuthorities()[0].getAuthority()))
            pad.sendRedirect("user/login");
    }

    protected abstract HMModelAndView handleRequest(HttpPad pad) throws Exception;

}
