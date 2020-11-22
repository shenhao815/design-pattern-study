package com.it.adapter.a;

/*
    定义：一个类的接口转换成客户端希望的另一个接口。适配器模式让那些接口不兼容的类可以一起工作。
    通俗一点的解释：根据已有的类，生成想要的接口！

    本例中，作者的接口是接收两个参数的，而客户端需要3个参数的接口
 */

class Calc{
    public int add(int a, int b) {
        return a + b;
    }
}

// ===================================
// 变化来了，客户端希望计算3个数的和，而Calc的add方法只能接受2个参数！

class CalcAdapter{
    private Calc calc;
    public CalcAdapter(Calc calc) {
        this.calc = calc;
    }

    public int add(int a, int b, int c) {
        return calc.add(a, calc.add(b, c));
    }
}
public class AppTest {
    public static void main(String[] args) {
        Calc calc = new Calc();
        CalcAdapter adapter = new CalcAdapter(calc);
        int a = adapter.add(1, 2, 3);
        System.out.println(a);
    }
}
