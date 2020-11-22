package com.it.factoryMethod.order;

import com.it.factoryMethod.pizza.LDCheesePizza;
import com.it.factoryMethod.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDCheesePizza();
        } else {
            pizza = null;
        }
        return pizza;
    }
}
