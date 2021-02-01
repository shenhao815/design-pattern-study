package com.it.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2021-2-1
 */

abstract class Parent{
    public void computTime() {
        System.out.println("开始");
        long start = System.currentTimeMillis();

        code();
        long end  = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start));
    }

    protected abstract void code();
}

class A extends Parent {

    @Override
    protected void code() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(0, i);
        }
    }
}

public class TemplateTest {
    public static void main(String[] args) {
        Parent p = new A();
        p.computTime();

    }
}
