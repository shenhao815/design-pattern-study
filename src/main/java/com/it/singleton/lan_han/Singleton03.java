package com.it.singleton.lan_han;

/**
 * 懒汉式（线程不安全）
 *
 * 优缺点：
 * 1） 起到了Lazy Loading的效果，但是只能在单线程下使用。
 * 2） 如果在多线程下，一个线程进入了if(singlton == null)判断语句块，但还未来得及
 *      往下执行，这时另一个线程也进入了if语句块，这时便会产生多个实例。所以在多线程
 *      环境下不可使用这种方式。
 * 结论：在实际开发中，不能使用这种方式。
 *
 */
public class Singleton03 {

    private Singleton03(){}

    private static Singleton03 instance = null;

    // 提供一个静态的公有方法，当使用到该方法时，才去创建instance
    public static Singleton03 getInstance() {
        if (instance == null) {
            instance = new Singleton03();
        }
        return instance;
    }
}
