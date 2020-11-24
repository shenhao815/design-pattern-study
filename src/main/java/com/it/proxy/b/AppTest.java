package com.it.proxy.b;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
    需求现有一个计算器类，要给计算器类加入日志，做法为在每个方法中添加打印
 */
interface Calc {
    int add(int a, int b);

    int sub(int a, int b);

    int mul(int a, int b);

    int div(int a, int b);
}

class CalcImp implements Calc {

    @Override
    public int add(int a, int b) {
        int r =  a + b;
        return r;
    }

    @Override
    public int sub(int a, int b) {
        int r = a - b;
        return r;
    }

    @Override
    public int mul(int a, int b) {
        int r =  a * b;
        return r;
    }

    @Override
    public int div(int a, int b) {
        int r = a / b;
        return r;
    }
}

class MyHandle implements InvocationHandler{

    private Calc target;

    // 传进来的才是目标对象
    public MyHandle(Calc calc) {
        this.target = calc;
    }

    /**
     * proxy.add(1, 2);
     * proxy.sub(1, 2);
     * 第一个参数对应proxy
     * 第二个参数对应方法（add,sub 。。。）
     * 第三个参数对应上面的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "开始，参数是： "+ Arrays.toString(args));
        Object r = method.invoke(target, args);
        System.out.println(method.getName()+"结束，结果是："+r);
        return r;
    }
}

public class AppTest {
    public static void main(String[] args) {




        // 创建代理对象

        // 第1个参数，ClassLoader
        // 我们都知道，要实例化一个对象，是通过new的方式，在程序运行期间第一次new一个类时，就会引起类的加载
        // 加载类的时候，就是jvm拿着ClassLoader去加载类的字节码，只在字节码被加载到了内存中，才能进一步去实例化出来类的对象，
        // 简单来说，就是只要涉及实例化类的对象，就一定要加载类的字节码，而加载字节码就必须使用类加载器！
        // 下面我们使用的是动态代理的api来创建一个类的对象，这是一种不常用的实例化类对象的方式，尽管不常用，但也毕竟涉及实例化类的对象
        // 那就一定也需要加载类的字节码，也就一定需要类加载器，所以我们手动把类加载器传入
        ClassLoader cl = Calc.class.getClassLoader();


        // 第二个参数：Class[]
        // 我们已经知道，下面的代码，是用来实例化一个对象的，实例化对象，就一定是实例化某一个类的对象，问题是，到底是哪个类呢？
        // 类在哪里？字节码又在哪里？这个类，其实并不在硬盘上，而是在内存中！是由动态代理在内存中“动态”生成的！
        // 要知道，这个在内存中直接生成的字节码，会去实现下面方法中的第2个参数中所指定的接口！
        // 所以，利用动态代理生成的代理对象，就能转成Calc接口类型！那么这个代理对象就拥有add sub mul div方法！

        // 第三个参数：InvocationHandler
        // 我们已经知道，下面的代理对象proxy所属的类，实现了Calc接口，所以，这个代理对象就拥有add sub mul div方法！
        // 我们就可以通过代理对象调用add sub mul div方法！
        // 注意：每次对代理对象任何方法的调用，都不会进入真正的实现方法中！而是统统进入第3个参数的invoke方法中！
        Calc proxy = (Calc) Proxy.newProxyInstance(cl, new Class[]{Calc.class}, new MyHandle(new CalcImp()));

        proxy.add(1, 2);

    }
}

/**
 * 这样处理也可以，但是缺点很多：
 * 1，代码重复率高
 * 2，如果需求再次变化，需要改多个地方
 * 3，代码急剧膨胀，核心业务代码，与非核心业务代码，耦合在一起！
 * 4，需求要求加入 求余，开方  立方。。。还必须在每个加入的新方法中，都手动进行打印！
 *
 * 针对上述问题，现采用动态代码模式，见b包
 */
