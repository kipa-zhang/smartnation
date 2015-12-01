package org.example.utils;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public final class ContextHelper { 
    private static FileSystemXmlApplicationContext _ctx; 

    static { 
    	System.out.println("&&&&&&&&&&&&" + System.getProperty("user.dir") + "&&&&&&&&&&&&&");
        _ctx = new FileSystemXmlApplicationContext("/webapps/site/WEB-INF/spring/applicationContext.xml"); 
    } 

    private ContextHelper() { 
    } 

    public static FileSystemXmlApplicationContext getContext() { 
        return _ctx; 
    } 
}