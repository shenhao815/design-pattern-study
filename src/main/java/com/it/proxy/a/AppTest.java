package com.it.proxy.a;

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
        System.out.println("add");;
        int r =  a + b;
        System.out.println("add end");
        return r;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("sub");;
        int r = a - b;
        System.out.println("sub end");;
        return r;
    }

    @Override
    public int mul(int a, int b) {
        System.out.println("mul");
        int r =  a * b;
        System.out.println("mul end");
        return r;
    }

    @Override
    public int div(int a, int b) {
        System.out.println("div");
        int r = a / b;
        System.out.println("div end");
        return r;
    }
}

public class AppTest {
    public static void main(String[] args) {
        Calc calc = new CalcImp();
        calc.add(1,2);
        calc.sub(1,2);
        calc.mul(1,2);
        calc.div(1,2);
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
