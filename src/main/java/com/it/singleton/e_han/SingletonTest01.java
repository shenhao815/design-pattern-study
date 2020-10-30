package com.it.singleton.e_han;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ch
 * @date 2020-10-30
 *
 * 饿汉模式 (静态变量)
 */
//@Slf4j
/**
 * 优缺点
 * 1） 优点:这种写法比较简单，就是在 类装载 的时候就完成实例化。避免了线程同步问题。
 * 2） 缺点：在类装载的时候就完成了实例化，没有达到Lazy Loading的效果。如果从从始至终
 *      从未使用过这个实例，则会造成内存的浪费。
 * 3） 这种方式基于classloader机制避免了多线程的同步问题。不过，instance在类装载时就实例化，
 *      在单例模式中大多数都是调用getInstance方法，但是导致类装载的原因有很多种，因此不能确定
 *      有其他的方式（或是其他的静态方法）导致类装载，这时候初始化instance就没有达到Lazy Loading的效果
 * 4） 结论：这种单例模式可用，但是可能会造成内存浪费
 */
public class SingletonTest01 {

    // 1. 构造器私有化，不让外部new
    private SingletonTest01(){}

    // 2. 本类内部创建对象实例
    private final static SingletonTest01 instance = new SingletonTest01();

    // 3.提供一个公有的静态方法，返回实例对象
    public static SingletonTest01 getInstance() {
        return instance;
    }
}
