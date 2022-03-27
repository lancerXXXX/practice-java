package com.swithun.javatest1;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import com.swithun.javatest1.bean.MyBean;

public class App {
    public static void main(String[] args) throws Exception {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("当前线程的类加载器:" + currentClassLoader);
        // 当前线程的类加载器:jdk.internal.loader.ClassLoaders$AppClassLoader@277050dc

        URL[] urls = new URL[1];
        urls[0] = new
        URL("file:///home/swithun/Project/vscode/java/javatest1/src/test/testUrlClassLoader/");

        
        URLClassLoader urlClassLoader1 = new URLClassLoader(urls);
        System.out.println("urlClassLoader1的父 类加载器: " + urlClassLoader1.getParent());
        // urlClassLoader1的父 类加载器: jdk.internal.loader.ClassLoaders$AppClassLoader@55054057

        URLClassLoader urlClassLoader2 = new URLClassLoader(urls);
        System.out.println("urlClassLoader2的父 类加载器: " + urlClassLoader2.getParent());
        // urlClassLoader2的父 类加载器: jdk.internal.loader.ClassLoaders$AppClassLoader@55054057

        Object t1 = urlClassLoader1.loadClass("com.swithun.javatest1.bean.MyBean").getDeclaredConstructor().newInstance(); // MyBean.class.getName()
        Object t2 = urlClassLoader2.loadClass("com.swithun.javatest1.bean.MyBean").getDeclaredConstructor().newInstance(); // MyBean.class.getName()

        Class c1 = t1.getClass();
        Class c2 = t2.getClass();

        System.out.println("t1类加载器: " + c1.getClassLoader());
        // t1类加载器: jdk.internal.loader.ClassLoaders$AppClassLoader@55054057
        System.out.println("t2类加载器: " + c2.getClassLoader());
        // t2类加载器: jdk.internal.loader.ClassLoaders$AppClassLoader@55054057

        Arrays.asList(((URLClassLoader)urlClassLoader1.getParent()).getURLs()).forEach(System.out::println);
        

        urlClassLoader1.close();
        urlClassLoader2.close();
    }
}
