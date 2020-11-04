package com.it.factoryMethod.pizza;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京的胡椒披萨");
        System.out.println(" 北京的胡椒pizza 准备原材料 ");
    }
}
