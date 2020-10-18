package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static ru.cofeok.aopdemo.aspect.LoggingAspect.CreateLogOutput;

@Aspect
@Component
@Order(2)
public class ApiAnalysisAspect {

    private Logger myLogger =
            Logger.getLogger(getClass().getName());

    @Pointcut("execution(public * addAccount(boolean))")
    private void forDaoAdding() {
    }

    @Before("forDaoAdding()")
    public void performApiAnalytics(JoinPoint theJoinPoint) {
        myLogger.info(CreateLogOutput(">>> INFO: Performing some API analytics"));

        // display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
        myLogger.info("   >>> MethodSignature: " + methodSig);

        // display the method args
        Object[] args = theJoinPoint.getArgs();
        for (Object obj : args) {
            myLogger.info("   >>> " + obj);
        }

    }
}
