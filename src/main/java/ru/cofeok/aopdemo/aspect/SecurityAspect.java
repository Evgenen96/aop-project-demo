package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static ru.cofeok.aopdemo.aspect.LoggingAspect.CreateLogOutput;

@Aspect
@Component
@Order(0)
public class SecurityAspect {

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
        System.out.println(CreateLogOutput(">>> SEC: Making some security checks"));
    }
}
