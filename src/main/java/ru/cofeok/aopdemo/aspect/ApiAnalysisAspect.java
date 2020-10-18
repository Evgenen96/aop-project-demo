package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static ru.cofeok.aopdemo.aspect.LoggingAspect.CreateLogOutput;

@Aspect
@Component
@Order(2)
public class ApiAnalysisAspect {

    @Pointcut("execution(public * addAccount(..))")
    private void forDaoAdding() {
    }

    @Before("forDaoAdding()")
    public void performApiAnalytics(JoinPoint theJoinPoint) {
        System.out.println(CreateLogOutput(">>> INFO: Performing some API analytics"));

        // display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("   >>> MethodSignature: " + methodSig);

        // display the method args
        Object[] args = theJoinPoint.getArgs();
        for (Object obj : args) {
            System.out.println("   >>> " + obj);
        }

    }
}
