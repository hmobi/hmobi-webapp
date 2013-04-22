package com.hmobi.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 22/04/13
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContextManager
{
    private static ContextManager contextManager;

    private ApplicationContext context;

    private ContextManager(String fileName)
    {
        this.context = new ClassPathXmlApplicationContext(fileName);
    }

    public <T> T getBean(Class<T> type)
    {
        return (T)context.getBean(type);
    }

    public static ContextManager getInstance()
    {
        if(contextManager == null)
        {
            synchronized (ContextManager.class)
            {
                if(contextManager == null)
                {
                    contextManager = new ContextManager("application-context.xml");
                }
            }
        }
        return contextManager;
    }


}
