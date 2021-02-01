package com.it.n_template;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    需求：
    分别测试ArrayList和LinkedList的增加和查询效率
    采用 模板方法模式
 */
abstract class Template{
    public void template(){
        System.out.println("开始：");
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("结束：" + (end - start));
    }
    abstract void code();
}

class A extends Template{
    @Override
    void code() {
    List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(0, 1);
        }
    }
}

class B extends Template{

    @Override
    void code() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(0, 1);
        }
    }
}

class C{

}
class D extends C{

}

// ===========================================
public class AppTest {
    public static void main(String[] args) {
        // 此处发生向上转型 向上转型时,父类只能调用父类方法或者子类覆写后的方法,而子类中的单独方法则是无法调用的.
        Template t = new B();
        t.template();

        C c = new C();
        D d = (D) c; // 向下转型
    }
}
/*
    向下转型则是为了,通过父类强制转换为子类,从而来调用子类独有的方法(向下转型,在工程中很少用到).
 */