package com.hmobi.controller.html;

import com.hmobi.dao.user.UserLogin;
import com.hmobi.httppad.HttpPad;
import com.hmobi.mvc.BaseController;
import com.hmobi.mvc.HMModelAndView;
import com.hmobi.utils.ErrorUtil;
import com.hmobi.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 15/04/13
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlController extends BaseController
{
    private static Logger logger = LoggerFactory.getLogger(HtmlController.class);

    @Override
    protected HMModelAndView handleRequest(HttpPad pad) throws Exception {

        //change view according to type of user. For now we only have one view in login.jsp
        String servletPath = pad.getServletPath();

        if(servletPath.contains("/signup.html"))
        {
            HMModelAndView modelAndView = new HMModelAndView("signup");
            return modelAndView;
        }
        else
        {
            HMModelAndView modelAndView = new HMModelAndView("login");
            int errorCode = pad.getIntParameter("errorCode");
            logger.info("errorCode=" + errorCode);
            if(errorCode == ErrorUtil.ERROR_ACCESS_DENIED)
                modelAndView.addObject("errorMsg",ErrorUtil.ACCESS_DENIED_MSG);
            else if(errorCode == ErrorUtil.ERROR_INVALID_CREDENTIALS)
                modelAndView.addObject("errorMsg",ErrorUtil.INVALID_CREDENTIALS_MSG);
            else
            {
                UserLogin user = UserUtil.getLoggedInUser();
                if(user != null)
                {
                    if("ROLE_USER".equals(user.getAuthorities()[0].getAuthority()))
                        pad.sendRedirect("user/login");
                }
            }
            return modelAndView;
        }
    }
}
