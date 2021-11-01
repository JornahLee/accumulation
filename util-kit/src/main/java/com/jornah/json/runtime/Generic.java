package com.jornah.json.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时泛型
 * 泛型擦除只存在于方法内部？ 实例变量的不会擦除
 * @author licong
 * @date 2021/10/31 19:38
 */
public class Generic {
    public static void main(String[] arg) throws NoSuchFieldException, SecurityException{
        test2();
    }

    private static void test2() {
        List l=new ArrayList<String>(){};		//创建一个ArrayList内部类 注意这里后面加了{}是内部类而不是简单的arraylist
        System.out.println(l.getClass().getGenericSuperclass());	//输出泛型父类
        Type[] t2=((ParameterizedType)l.getClass().getGenericSuperclass()).getActualTypeArguments();	//获得泛型的所有泛型参数
        for(Type t:t2){
            System.out.println(t);				//遍历输出各个参数
        }
    }

    private static void test1() throws NoSuchFieldException {
        Field f=Pojo.class.getField("list");		//反射获得泛型成员变量
        System.out.println(f.getGenericType());		//输出成员变量类型
        Type[] type=((ParameterizedType)f.getGenericType()).getActualTypeArguments();	//获得泛型的所有泛型参数
        for(Type t:type){
            System.out.println(t);					//遍历输出各个参数
        }
    }

    public static class Pojo{
        public List<String> list;	//泛型成员变量
    }
}
