package com.it.singleton.e_han;

/**
 * 优缺点：
 * 这种方式和SingletonTest01的方式是类似的，只是将类实例化的过程放在了静态代码块中，
 * 也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。优缺点和SingletonTest01是一样的。
 *
 * 结论：这种单例模式可用，但是可能会造成内存浪费。
 */
public class SingletonTest02 {

    private SingletonTest02(){}

    private static SingletonTest02 instance ;

    static {
        instance = new SingletonTest02();
    }

    public static SingletonTest02 getInstance() {
        return instance;
    }

}
