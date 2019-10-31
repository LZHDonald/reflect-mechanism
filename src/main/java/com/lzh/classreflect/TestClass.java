package com.lzh.classreflect;

import com.lzh.model.Dog;
import com.lzh.model.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lzh
 * create 2019-10-27-10:39
 */
public class TestClass {

    @Test
    public void getClassTest() {

        //1、获得Class：主要有三种方法：

        //第一种方式获取Class对象
        Student student = new Student();
        Class stuClass1 = student.getClass();
        System.out.println(stuClass1.getName());


        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        System.out.println(stuClass2.getName());
        System.out.println(stuClass1 == stuClass2);


        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("com.lzh.model.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isInstanceTest(){

        Student student = new Student();

        //判断是否为某个类
        System.out.println(student instanceof Student);

    }

    @Test
    public void newObjectTest() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //创建实例：通过反射来生成对象主要有两种方法

        //1.使用Class对象的newInstance()方法来创建Class对象对应类的实例。
        Class c = Student.class;
        Object stu1 = c.newInstance();

        //2.先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建对象，这种方法可以用指定的构造器构造类的实例。
        Class<?> stu2 = Student.class;
        //通过Class对象获取指定的Constructor构造器对象

        Constructor<?> constructor = stu2.getConstructor(String.class, Integer.class);
        Object raicho = constructor.newInstance("raicho", 21);
        System.out.println(raicho);

    }

    @Test
    public void getConstructorTest() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Class<?> stu = Student.class;

        //所有"公有的"构造方法
        Constructor<?> constructor = stu.getConstructor(String.class, Integer.class);
        Object stu1 = constructor.newInstance("raicho_one", 21);
        System.out.println(stu1);
        System.out.println("----------------------------------");

        //获取所有的构造方法(包括私有、受保护、默认、公有)
        Constructor<?>[] constructors=stu.getConstructors();
        for (Constructor<?> constructor1 : constructors) {
            System.out.println(constructor1);
        }
        //根据构造器创建实例：
        Object obj = constructors[0].newInstance("小王",21);
        System.out.println(obj);
        System.out.println("----------------------------------");

        //获取"某个构造方法"可以是私有的，或受保护、默认、公有；
        Constructor<?> declaredConstructor = stu.getDeclaredConstructor(String.class);
        //System.out.println("declaredConstructor="+declaredConstructor);
        //设置越过安全检查
        declaredConstructor.setAccessible(true);
        Object stu2 = declaredConstructor.newInstance("raicho");
        System.out.println(stu2);
        System.out.println("获取所有构造器，包括私有");
        Constructor<?>[] declaredConstructors = stu.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor1 : declaredConstructors) {
            System.out.println(declaredConstructor1);
        }
        System.out.println("----------------------------------");
    }

    @Test
    public void getsetPropertyTest() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<?> dogClass = Class.forName("com.lzh.model.Dog");

        System.out.println("************获取所有公有的字段********************");
        Field[] fields = dogClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] declaredFields = dogClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("*************设置私有属性值***********************************");
        Constructor<?> declaredConstructor = dogClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Dog dog1 = (Dog) declaredConstructor.newInstance();
        System.out.println(dog1);
        //dog1.setName("阿拉斯");
        Field field = Dog.class.getDeclaredField("name");
        field.setAccessible(true);
        field.set(dog1,"阿拉斯");
        System.out.println(dog1);

    }

    @Test
    public void method() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> teacherClass = Class.forName("com.lzh.model.Teacher");

        System.out.println("***************获取所有的”公有“方法*******************");
        Method[] methods = teacherClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("***************获取所有的方法，包括私有的*******************");
        Method[] declaredMethods = teacherClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println("***************获取私有的show4()方法******************");
        Method show4 = teacherClass.getDeclaredMethod("show4", int.class);
        System.out.println(show4);
        Object obj = teacherClass.getConstructor().newInstance();
        //越过安全检查
        show4.setAccessible(true);
        Object result = show4.invoke(obj, 21);
        System.out.println("返回值："+result);
    }
}

