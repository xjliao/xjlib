/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: ForApplication.java
 * ClassName: ForApplication
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {

}