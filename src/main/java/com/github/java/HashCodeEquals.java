package com.github.java;

import java.util.Map;

/**
 * @author Eric-hu
 * @Description: 重写对象的equals和hashcode方法
 * @create 2017-12-28 18:07
 * @Copyright: 2017 www.banmatrip.com All rights reserved.
 **/
public class HashCodeEquals {
    String name;

    Integer age;

    Map<String,Object> map;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashCodeEquals that = (HashCodeEquals) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return age != null ? age.equals(that.age) : that.age == null;
    }

    @Override
    public int hashCode() {
        /*每一个类都会有hashcode，属性也是基础类型，也有hashcode方法*/
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}