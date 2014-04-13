package com.sdk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sdk.constants.PlatformAPIType;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SDKTypeAPIImpl {
	PlatformAPIType platformAPIType();
}