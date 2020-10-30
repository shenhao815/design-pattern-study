package com.it.singleton.lan_han;

/**
 * 懒汉模式（双重检查）
 *
 * 1） Double-Check概念是多线程开发中常使用到的。
 * 2） 实例化代码中用执行一次，后面再次访问时，判断if时直接return实例化的对象，
 *      也避免了反复进行方法同步。
 * 3） 线程安全，延迟加载，效率较高
 * 结论：在实际开发中，推荐使用这种单例模式
 *
 */
public class SingletonDCL {

    private SingletonDCL(){}

    private static volatile SingletonDCL singletonDCL;

    // 加入双重检查代码，用来提高效率。同时该方式也解决了线程安全问题
    public static SingletonDCL getInstance() {
        if (singletonDCL == null) {
            synchronized (SingletonDCL.class) {
                if (singletonDCL == null) {
                    singletonDCL = new SingletonDCL();
                }
            }
        }
        return singletonDCL;
    }
}
