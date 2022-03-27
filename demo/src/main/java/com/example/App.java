package com.example;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.bean.MyBean;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. 检查当前线程的类加载器
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("当前线程的类加载器:" + currentClassLoader);
        // 当前线程的类加载器:sun.misc.Launcher$AppClassLoader@75b84c92

        // 2. 准备两个URLClassLoader
        URL[] urls = new URL[1];
        urls[0] = new URL("file:/home/swithun/Project/vscode/java/demo/src/test/testUrlClassLoader/");

        URLClassLoader urlClassLoader1 = new URLClassLoader(urls);
        URLClassLoader urlClassLoader2 = new URLClassLoader(urls);

        System.out.println("urlClassLoader1的父 类加载器: " + urlClassLoader1.getParent());
        // urlClassLoader1的父 类加载器: sun.misc.Launcher$AppClassLoader@75b84c92
        System.out.println("urlClassLoader2的父 类加载器: " + urlClassLoader2.getParent());
        // urlClassLoader2的父 类加载器: sun.misc.Launcher$AppClassLoader@75b84c92

        // 3. 使用两个URLClassLoader分别加载两个Bean
        Object t1 = urlClassLoader1.loadClass("com.example.demo.bean.MyBean").newInstance(); 
        Object t2 = urlClassLoader2.loadClass("com.example.demo.bean.MyBean").newInstance(); 

        // 4. 查看两个Bean使用的类加载器
        System.out.println("t1类加载器: " + t1.getClass().getClassLoader());
        // t1类加载器: sun.misc.Launcher$AppClassLoader@75b84c92
        System.out.println("t2类加载器: " + t2.getClass().getClassLoader());
        // t2类加载器: sun.misc.Launcher$AppClassLoader@75b84c92

        Arrays.asList(((URLClassLoader)urlClassLoader1.getParent()).getURLs()).forEach(System.out::println);
        // file:/home/swithun/Project/vscode/java/demo/target/classes/

        urlClassLoader1.close();
        urlClassLoader2.close();
    }
}
