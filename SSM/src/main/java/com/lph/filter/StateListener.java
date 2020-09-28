package com.lph.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StateListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("服务器启动了================================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //在这里将所有的状态改成离线0
        //StudentDao dao = new StudentDao();
        //dao.setAllLeave();
        //System.out.println("服务器销毁了=================================================");
    }
}
