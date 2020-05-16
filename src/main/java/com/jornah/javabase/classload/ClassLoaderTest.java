package com.jornah.javabase.classload;

import java.lang.reflect.Method;

//import com.jornah.Fu;

public class ClassLoaderTest {
    public static void main(String args[]) throws Exception{
        ClassLoaderTest test= new ClassLoaderTest();
        test.test6();
    }
    
    public void test6() throws Exception {
        
        MyClassLoader mloader1=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode"); 
        Class<?> fileFuClass = mloader1.findClass("com.bruce.classload.Fu");
        Class<?> eclipFuClass=this.getClass().getClassLoader().loadClass("com.jornah.javabase.classload.Fu");
//        Fu f=new Fu();
//        f.show();
        
        
//        Object obj = fileFuClass.newInstance();
//        Method method = fileFuClass.getDeclaredMethod("show");
//        method.invoke(obj);
        
        
//        Class<?> fileZiClass = mloader1.findClass("com.bruce.classload.Zi");
//        Object obj = fileZiClass.newInstance();
//        Method method = fileZiClass.getDeclaredMethod("show2");
//        method.invoke(obj);
        
//        Object obj = fileFuClass.newInstance();
//        Method method = fileFuClass.getDeclaredMethod("show");
//        method.invoke(obj);
//        Class<?> c = mloader1.findClass("com.bruce.classload.Fu");
//        Class<?> fileZiClass = mloader1.loadClass("com.bruce.classload.Zi");  
//        Object obj=fileZiClass.newInstance();
//        Method method = fileZiClass.getDeclaredMethod("show");
//        method.invoke(obj);
        
        
//        System.out.println("------new Fu------");
//        Fu f=new Fu();
//        f.show();
        
        
//        Fu fileZi = (Fu)fileZiClass.newInstance();
//        fileZi.show();
    }
    public void test5() throws Exception {
//        URL[] url=new URL[1];
//        url[0]=new URL("file:\\C:\\Users\\P1323153\\Desktop\\javacode");
//        URLClassLoader urlClassLoader=new URLClassLoader(url);
//        Class<?> fileZiClass=urlClassLoader.loadClass("com.bruce.classload.Zi");
        MyClassLoader mloader1=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode"); 
        Class<?> fileZiClass = mloader1.loadClass("com.jornah.javabase.classload.Zi");
        Fu fileZi = (Fu)fileZiClass.newInstance();
        ClassLoader appClassLoader=this.getClass().getClassLoader();
        Class<?> eclipseZiClass = appClassLoader.loadClass("com.jornah.javabase.classload.Zi");
        Fu ecliZi = (Fu)eclipseZiClass.newInstance();
        fileZi.showShowShow();
        System.out.println("fileZi:"+fileZi.getClass().getClassLoader());
        ecliZi.showShowShow();
        System.out.println("ecliZi:"+fileZi.getClass().getClassLoader());
//        MyClassLoader mloader1=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode"); 
//        Class<?> class1 = mloader1.loadClass("com.bruce.classload.Zi");
//        Fu instance = (Fu)class1.newInstance();
//        Fu f=new Zi();
//        ClassLoader appClassLoader=f.getClass().getClassLoader();
//        Class<?> fSonClass = appClassLoader.loadClass("com.bruce.classload.FuSon");
//        Fu fuSon=(Fu)fSonClass.newInstance();
//        instance.showShowShow();
//        fuSon.showShowShow();
    }
    public void test4() throws Exception {
//        Fu f=new Fu();     
//        f.show2();
//        System.out.println("f in eclipse"+f.getClass().getClassLoader());
        MyClassLoader mloader1=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode"); 
        Class<?> class1 = mloader1.loadClass("com.jornah.javabase.classload.Zi");
        Fu instance = (Fu)class1.newInstance();
//        System.out.println("Fu instance in eclipse"+f.getClass().getClassLoader());
//        for(Method m:class1.getDeclaredMethods()) {
//            if("show".equals(m.getName())) {
//                
//            }
//        }
//        f.staticMethod();
        Method method = class1.getDeclaredMethod("show");
//        method.setAccessible(true);
        instance.show2();
        method.invoke(instance); 
        
        
    }
    public void test3() throws Exception {
        MyClassLoader mloader1=new MyClassLoader("D:\\myWorkplace\\ParentImpl\\target\\classes"); 
        Class<?> class1 = mloader1.loadClass("com.jornah.Zi");
        Fu instance = (Fu)class1.newInstance();
        Method method = class1.getDeclaredMethod("show");
//        method.setAccessible(true);
        instance.show();
        method.invoke(instance); 
        
        
    }
    
    public void test1() throws Exception {
//        D:\myWorkplace\MessTest
        MyClassLoader mloader1=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode");
        MyClassLoader mloader2=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode");
        Class<?> class1 = mloader1.loadClass("Fu");
        Object instance = class1.newInstance();
        Class<?> class2 = mloader2.loadClass("Fu");
//        if(instance instanceof class1) {
//            
//        }
        System.out.println(class1==class2);
        
        
    }
    public void test2()  throws Exception{
        MyClassLoader mloader=new MyClassLoader("C:\\Users\\P1323153\\Desktop\\javacode");
        Class<?> loadClass = mloader.loadClass("Fu");
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        appClassLoader.loadClass("Fu");
        
        Object instance = loadClass.newInstance();
        Method method = loadClass.getDeclaredMethod("show");
//        method.setAccessible(true);
        method.invoke(instance);     
    }

}
