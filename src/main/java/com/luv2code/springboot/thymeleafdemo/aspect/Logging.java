package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class Logging {
    private  static final Logger logger=Logger.getLogger(Logging.class.getName());

    @Before("com.luv2code.springboot.thymeleafdemo.aspect.Expressions.find()")
    public void beforeFinding()
    {
        logger.info("\n====> Finding from the service layer ");
    }
    @After("com.luv2code.springboot.thymeleafdemo.aspect.Expressions.find()")
    public void afterFinding()
    {
        logger.info("\n====> Found from the service layer ");
    }
    @Before("com.luv2code.springboot.thymeleafdemo.aspect.Expressions.save()")
    public void beforeSaving()
    {
        logger.info("\n====> Saving to the database  ");
    }
    @Before("com.luv2code.springboot.thymeleafdemo.aspect.Expressions.delete()")
    public void beforeDelete()
    {
        logger.info("\n====> Deleting from the database  ");
    }
}
