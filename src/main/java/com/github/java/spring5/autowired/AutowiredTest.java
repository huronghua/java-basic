package com.github.java.spring5.autowired;

import java.lang.reflect.Field;
import java.util.Objects;

public class AutowiredTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        AutowiredController controller = new AutowiredController();

        Class<? extends AutowiredController> aClass = controller.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            MyAutowired annotation = field.getAnnotation(MyAutowired.class);
            if(Objects.nonNull(annotation)){
                Class<? extends MyAutowired> annotationClass = annotation.getClass();

                Class<?> autowiredClass = field.getType();
                Object o = autowiredClass.newInstance();

                field.set(controller, o);
            }
        }

        System.out.println(controller.getAutowiredTest());

    }
}
