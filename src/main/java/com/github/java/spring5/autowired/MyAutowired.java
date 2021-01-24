package com.github.java.spring5.autowired;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAutowired {


}
