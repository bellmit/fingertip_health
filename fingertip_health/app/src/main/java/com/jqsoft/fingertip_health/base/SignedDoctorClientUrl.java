package com.jqsoft.fingertip_health.base;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by quantan.liu on 2017/3/27.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface SignedDoctorClientUrl {
}
