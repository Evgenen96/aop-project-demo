package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static ru.cofeok.aopdemo.aspect.LoggingAspect.CreateLogOutput;

@Aspect
@Component
@Order(0)
public class SecurityAspect {

    private Logger myLogger =
            Logger.getLogger(getClass().getName());

    // next two are for combining
    @Pointcut("execution(public * ru.cofeok.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(public * ru.cofeok.aopdemo.dao.*.update*(..))")
    private void forDaoUpdate() {
    }

    // combining pointcuts
    @Before("forDaoPackage() && !forDaoUpdate()")
    public void securityCheck() {
        myLogger.info(CreateLogOutput(">>> SEC: Making some security checks"));
    }
}
