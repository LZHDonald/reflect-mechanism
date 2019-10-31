package com.lzh.collectionreflect;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * create 2019-10-27-17:15
 */
public class CollectionTest {

    @Test
    public void collectionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //通过反射越过泛型检查
        //有一个String泛型的集合，向这个集合中添加一个Integer类型的值
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        //list.add(100); //报错
        //获得集合Class对象
        Class listClass = list.getClass();
        //获得add()方法
        Method add = listClass.getMethod("add", Object.class);
        //调用add()方法
        add.invoke(list,100);
        for (Object s : list) {
            System.out.println(s);
        }

    }
}
