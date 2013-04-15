package com.hmobi.controller.user;

import com.hmobi.dao.user.UserLogin;
import com.hmobi.httppad.HttpPad;
import com.hmobi.mvc.BaseController;
import com.hmobi.mvc.HMModelAndView;
import com.hmobi.utils.ErrorUtil;
import com.hmobi.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLoginController extends BaseController
{
    private static Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Override
    protected HMModelAndView handleRequest(HttpPad pad) throws Exception {

        HMModelAndView modelAndView = new HMModelAndView("login");
        UserLogin user = UserUtil.getLoggedInUser();

        if(user == null)
        {
            //not logged in
            pad.sendRedirect("index.html");
        }
        else
        {
            //logged in
            modelAndView.addObject("user", user.getUsername());
        }

        return modelAndView;
    }
}