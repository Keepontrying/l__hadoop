package com.lyw.hadoop.annotions;

import java.lang.annotation.*;

/**
 * 类描述：注解学习
 *
 * @author liangyuwu
 * @Time 2018/9/10 9:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnotationTest {

    public String name() default "test" ;

    public int id() default -1;
}
