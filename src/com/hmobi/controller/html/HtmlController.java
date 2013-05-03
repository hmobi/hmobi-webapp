package com.hmobi.controller.html;

import com.hmobi.dao.user.Address;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserPermissions;
import com.hmobi.dao.user.UserSignUp;
import com.hmobi.httppad.HttpPad;
import com.hmobi.mvc.BaseController;
import com.hmobi.mvc.HMModelAndView;
import com.hmobi.utils.ErrorUtil;
import com.hmobi.utils.PageUtil;
import com.hmobi.utils.UserUtil;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

        if(servletPath.contains(PageUtil.INDEX_HTML))
        {
            HMModelAndView modelAndView = new HMModelAndView(PageUtil.INDEX);
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
                    logger.info(user + "::::" + user.getUsername() + "::::: " + user.getPassword());
                    redirectUserBasedOnRole(user, pad);
                }
            }
            return modelAndView;
        }
        else if(servletPath.contains(PageUtil.ACCESS_DENIED_HTML))
        {
            HMModelAndView modelAndView = new HMModelAndView(PageUtil.ACCESS_DENIED);
            return modelAndView;
        }
        else if(servletPath.contains(PageUtil.SIGN_UP_HTML))
        {
            String email = pad.getParameter("email");
            String username = pad.getParameter("username");
            String password = pad.getParameter("password");
            if(email != null && username != null && password != null)
            {
                logger.info(email + "::::" + username + "::::: " + password);
                UserSignUp usu = new UserSignUp();
                usu.setEmail(email);
                usu.setPassword(password);
                usu.setUsername(username);
                UserLogin user = userService.signUpUser(usu);
                Authentication auth = new UsernamePasswordAuthenticationToken(user,user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                redirectUserBasedOnRole(user,pad);
            }
            else
            {
                HMModelAndView modelAndView = new HMModelAndView(PageUtil.SIGN_UP);
                return modelAndView;
            }

        }
        else if(servletPath.contains(PageUtil.SEARCH_HTML))
        {
            HMModelAndView modelAndView = new HMModelAndView(PageUtil.SEARCH);
            String location = pad.getParameter("location");
            System.out.println("location is -> " + location);
            List<Address> addresses = userService.getAddresses(location);
            String[] addrs = null;
            if(addresses != null)
            {
                addrs = new String[addresses.size()];
                for(int i=0;i<addresses.size();i++)
                {
                    Address address = addresses.get(i);
                    addrs[i] = address.getAddress() + "," + address.getAddress2() + ", " + address.getCity() + ", " + address.getCountry();
                }
            }
            modelAndView.addObject("addresses", addrs);
            return modelAndView;
        }

        return null;

    }
}
