package com.it.singleton.lan_han;


/**
 * 优缺点：
 * 1） 解决了线程不安全的问题
 * 2） 效率太低了，每个线程在想获得类的实例的时候，执行的getInstance()方法都要
 *      进行同步。而其实这个方法只要执行一次实例化代码就够了，后面的想获得该类实例，
 *      直接return就行了。方法进行同步效率太低。
 * 结论：在实际开发中，不推荐这种方式。
 */
public class Singleton04 {
    private Singleton04(){}

    private static Singleton04 singleton04 = null;

    // 提供一个静态公有方法，加入同步处理的代码，用来解决线程安全问题
    public static synchronized Singleton04 getInstance() {
        if (singleton04 == null) {
            singleton04 = new Singleton04();
        }
        return singleton04;
    }

    /**
     * 这种写法是错误的
     * 这种写法的本意是想对上面的实现方法进行改进，因为前面同步方法效率太低。
     * 但是这种同步并不没有起到线程同步的作用。假如一个线程进入了if块中，还未来得及往下执行，
     * 另一个线程也进入到if块中，这时便产生了多个实例。
     * 结论：这是一个错误写法。
     * @return
     */
    // public static  Singleton04 getInstance2() {
    //     if (singleton04 == null) {
    //         synchronized (Singleton04.class) {
    //             singleton04 = new Singleton04();
    //         }
    //     }
    //     return singleton04;
    // }
}
