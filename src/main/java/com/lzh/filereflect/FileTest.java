package com.lzh.filereflect;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author lzh
 * create 2019-10-27-16:30
 */
public class FileTest {

    @Test
    public void fileTest() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.通过反射获取Class对象
        Class<?> employeeclass = Class.forName(getValue("className"));
//        String className = getValue("className");
//        System.out.println(className);
        //2.获取show()方法
        Method method = employeeclass.getMethod(getValue("methodName"));
        //3.调用show()方法
        method.invoke(employeeclass.getConstructor().newInstance());

        //当我们升级这个系统时，不要Student类，而需要新写一个Student2的类时，这时只需要更改pro.txt的文件内容就可以了。代码就一点不用改动。
    }

    public String getValue(String key) throws IOException {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = new FileInputStream("F:\\idea2018WorkSpace\\reflect-mechanism\\src\\main\\java\\com\\lzh\\filereflect\\pro.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        String property = properties.getProperty(key);

        return property;
    }



}
