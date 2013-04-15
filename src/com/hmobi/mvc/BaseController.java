package com.hmobi.mvc;

import com.hmobi.httppad.HttpPad;
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
    @Override
    protected HMModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception
    {
        HttpPad pad = new HttpPad(request, response);
        return handleRequest(pad);
    }

    protected abstract HMModelAndView handleRequest(HttpPad pad) throws Exception;

}
