/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: RequestProgress.java
 * ClassName: RequestProgress
 * LastModified: 12/17/17 4:21 PM
 */

package me.xjliao.xjlib.annotation

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by xjliao on 12/17/17.
 */

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
annotation class Progress(val isDeployAble: Boolean = true, val isDialogAble: Boolean = true, val beforeProgressMsg: String = "即将处理", val progressMsg: String = "处理中...", val afterProgressMsg: String = "处理完毕", val errorProgressMsg: String = "处理错误")
