/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: ForApplication.java
 * ClassName: ForApplication
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {

}