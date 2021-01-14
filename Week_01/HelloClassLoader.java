package jvm;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Object helloClazz = new HelloClassLoader().findClass("Hello").newInstance();
            Method helloMethod = helloClazz.getClass().getMethod("hello");
            helloMethod.invoke(helloClazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("D:\\JavaProject\\Test\\src\\jvm\\Hello.xlass");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int num;
            while ((num = fileInputStream.read()) != -1){
                stream.write(255-num);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name,stream.toByteArray(),0,stream.toByteArray().length);
    }
}
