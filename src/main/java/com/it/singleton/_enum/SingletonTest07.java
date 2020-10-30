package com.it.singleton._enum;

public class SingletonTest07 {

    public static void main(String[] args) {
        Singleton singleton = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;
        System.out.println(singleton == singleton2);

        System.out.println(singleton.hashCode());
        System.out.println(singleton2.hashCode());

        singleton.testMethod();
        singleton2.testMethod();
    }
}

/**
 * 优缺点：
 * 1） 借助枚举来实现单例模式。不仅能避免多线程同步问题，
 *      而且还能防止反序列化重新创建新的对象。
 * 2） 这种方式是Effective Java作者 Josh Bloch提倡的方式
 * 结论：推荐使用。
 */
enum Singleton{
    INSTANCE;

    public void testMethod(){
        System.out.println("ok~");
    }
}
