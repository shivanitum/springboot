package com.luv2code.springboot.thymeleafdemo.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Expressions {
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.find*(..))")
    public void find() throws UnsupportedOperationException
    {
        // abstract methods
         // no implementation
    }
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.save*(..))")
    public void save() throws UnsupportedOperationException
    {
        // abstract methods
        // no implementation
    }
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.delete*(..))")
    public void delete() throws UnsupportedOperationException
    {
        // abstract methods
        // no implementation
    }
}
