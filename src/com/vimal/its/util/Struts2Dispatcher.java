package com.vimal.its.util;

import javax.servlet.*;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.HibernateException;

public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        super.init(filterConfig);
        try
        {
            HibernateUtil.createSessionFactory();
            System.out.println("*** Application initializing successfully ***");
        }
        catch (HibernateException e)
        {
            throw new ServletException(e);
        }
    }
}

