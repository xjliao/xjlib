/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: RequestProgress.java
 * ClassName: RequestProgress
 * LastModified: 12/17/17 4:21 PM
 */

package me.xjliao.xjlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xjliao on 12/17/17.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Progress {

    boolean isDeployAble() default true;

    boolean isDialogAble() default false;

    String beforeProgressMsg() default "即将处理";

    String progressMsg() default "处理中...";

    String afterProgressMsg() default "处理完毕";

    String errorProgressMsg() default "处理错误";

}
