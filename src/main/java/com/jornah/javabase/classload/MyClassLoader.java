package com.jornah.javabase.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{
    private String classPath;
    public MyClassLoader(String classPath) {
        this.classPath=classPath;
    }
    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] classData=getClassData(className);
        Class<?> defineClass=null;
        if(classData!=null) {
            defineClass = defineClass(className,classData,0,classData.length);
        }else {
            throw new ClassNotFoundException();
        }
        return defineClass;
    }
//    @Override
//    public Class<?> loadClass(String className) throws ClassNotFoundException{
//        Class<?> clazz=findLoadedClass(className);
//        if(clazz==null) {
//            try {
//                clazz=findClass(className);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        if(clazz==null&&getParent()!=null) {
//            clazz=getParent().loadClass(className);
//        }
////        if(resolve) {
//            
//       
//        return clazz;
//        
//    }
    private byte[] getClassData(String className) {
        
        String path=classPath+File.separator+className.replace(".",File.separator)+".class";
        File file=new File(path);
        byte[] buff=new byte[1024];
        ByteArrayOutputStream baos=null;
        int length=0;
        try {
            FileInputStream fis=new FileInputStream(file);
            baos=new ByteArrayOutputStream();
            while((length=fis.read(buff))!=-1) {
                baos.write(buff, 0, length);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
        
    }
    @Override
    public String toString() {
        return "this is myClassLoader";
    }

}
