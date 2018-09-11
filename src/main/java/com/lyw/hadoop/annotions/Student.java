package com.lyw.hadoop.annotions;

import com.lyw.hadoop.arithmetic.KMPArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 类描述：注解
 *
 * @author liangyuwu
 * @Time 2018/9/11 11:20
 */
@AnnotationTest(name = "lyw")
public class Student {

    @Autowired
     KMPArithmetic kmpArithmetic;

    //测试注解、解析
    public static void main2(String[] args) {
        Student s = new Student();
        Class klass = Student.class;

        Annotation[] annotationss = klass.getAnnotations();
        Stream.of(annotationss).forEachOrdered(x -> System.err.println("+"+x));

        AnnotationTest a = (AnnotationTest) annotationss[0];
        System.err.println("++"+a.toString());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

//        System.err.println("@@"+kmpArithmetic.next(20));
    }
}
