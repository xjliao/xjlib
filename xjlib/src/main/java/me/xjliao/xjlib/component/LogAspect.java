/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: LogAspect.java
 * ClassName: LogAspect
 * LastModified: 12/17/17 4:52 PM
 */

package me.xjliao.xjlib.component;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by xjliao on 12/17/17.
 */

@Aspect
public class LogAspect {

    public static final String TAG = "log";

    @Pointcut("within(@me.xjliao.xjlib.annotation.Log *)")
    public void logClass() {}

    @Pointcut("execution(* *(..))")
    public void logMethod() {}

    @Pointcut("execution(@me.xjliao.xjlib.annotation.Log * *(..))")
    public void logClassMethod() {}

    @Pointcut("logMethod() && logClass() || logClassMethod()")
    public void log() {}

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }

        Log.i(TAG, ".............................");
    }
}
