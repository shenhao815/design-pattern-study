package com.it.singleton.static_innerclass;


/**
 * 静态内部类方式
 *
 * 静态内部类有两个特点：
 *  1） 当外部类被加载时，静态内部类是不会被加载的
 *  2） 当调用外部类的getInstance()方法时，此时访问到静态内部类的属性，
 *      这时才会加载静态内部类，并且只会加载一次，并且线程安全。
 *
 * 优缺点：
 * 1） 这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
 * 2） 静态内部类方式在Singleton类被加载时并不会立即实例化，而是在需要实例化
 *      时，调用getInstance()方法，才会加载SingletonInstance类，从而完成
 *      Singleton的实例化。
 * 3） 类的静态属性只会在第一次加载类的时候被初始化，所以在这里，JVM帮助我们保证了
 *      线程的安全性，在类进行初始化时，别的线程是无法进入的。
 * 4） 优点：利用静态内部类的特点实现了延迟加载和线程安全，效率高
 * 结论：推荐使用。
 */
public class Singleton06 {

    private Singleton06(){}

    private static class SingletonInstance{
        private static final Singleton06 SINGLETON_06 = new Singleton06();
    }

    public static Singleton06 getInstance() {
        return SingletonInstance.SINGLETON_06;
    }
}
